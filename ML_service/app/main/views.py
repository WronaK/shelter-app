from . import main
from ..ml_core import read_blob, get_features, get_n_closest_matches
from .. import db
from sqlalchemy import inspect


@main.route("/ping", methods=['GET', 'POST'])
def liveness_probe():
    return "shelter app is up and running."


@main.route("/db", methods=['GET'])
def get_table_names():
    return str(inspect(db.engine).get_table_names())


@main.route("/new_dog/<pet_id>", methods=['GET', 'POST'])
def get_features_for_new_dog(pet_id):
    image_path = read_blob(pet_id)
    pet_features = get_features(image_path)
    return pet_features


@main.route("/matches/<pet_id>", methods=['GET', 'POST'])
def get_pets_matches(pet_id):
    return get_n_closest_matches(pet_id)
