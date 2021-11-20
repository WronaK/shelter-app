import random

from . import db, metadata, cnn_features_extractor, pca
from tensorflow.keras.preprocessing import image
import pandas as pd
import numpy as np
import os
import base64
from sklearn.neighbors import NearestNeighbors


ROOT_DIR = os.getcwd()


def read_blob(pet_id):
    """ read BLOB data from a table """
    image_path = os.path.join(ROOT_DIR, "ML_service", "tmp_images", str(pet_id)+".png")
    sql = f"SELECT image " \
          f"FROM pet inner join photo " \
          f"ON pet.photo_id = photo.id" \
          f"WHERE id = {pet_id};"
    result = db.engine.execute(sql)
    ablob = result.fetchone()
    with open(image_path, "wb") as output_file:
        output_file.write(base64.decodebytes(ablob[0]))
    return image_path


def get_features(photo_path):
    img = image.load_img(photo_path, target_size=(224, 224))
    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0).astype('float32')/255
    cnn_features = cnn_features_extractor.predict([x])
    features = pca.transform(cnn_features)
    return features


def get_n_closest_matches(pet_id, n=2):
    sql_pet = f"SELECT features " \
              f"FROM pet inner join photo " \
              f"ON pet.photo_id = photo.id" \
              f"WHERE pet.id = {pet_id};"
    sql_all_pets = f"SELECT pet.id, features " \
                   f"FROM pet inner join photo " \
                   f"ON pet.photo_id = photo.id" \
                   f"WHERE pet.id != {pet_id};"
    # pet_features = db.engine.execute(sql_pet).fetchone()
    # all_pets_features = db.engine.execute(sql_all_pets).fetchall()
    all_pets = pd.read_sql(sql_all_pets, con=db.engine)
    if pet_id == -1 or random.random() < 0.1:
        ids = random.sample(range(0, len(all_pets)), n)
        return all_pets.loc[ids, ['pet.id']]
    all_pets_features = all_pets['features'].to_numpy()
    pet_features = pd.read_sql(sql_pet, con=db.engine).to_numpy()
    neigh = NearestNeighbors(n_neighbors=n)
    neigh.fit(all_pets_features)
    result = neigh.kneighbors(pet_features, return_distance=False).squeeze()
    nearest_ids = [all_pets.loc[neigh_id, ['pet.id']].values[0] for neigh_id in result]
    return nearest_ids
