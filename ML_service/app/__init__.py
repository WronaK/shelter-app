from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from config import config
from definitions import ROOT_DIR
from sqlalchemy import MetaData
from tensorflow.keras.models import load_model
from joblib import load
import os

db = SQLAlchemy()
metadata = MetaData()
cnn_features_extractor = load_model(os.path.join(ROOT_DIR, "models", "features_extractor"))
pca = load(os.path.join(ROOT_DIR, "models", "pca.joblib"))


def create_app(config_name):
    app = Flask(__name__)
    app.config.from_object(config[config_name])
    config[config_name].init_app(app)
    db.init_app(app)
    from .main import main as main_blueprint
    app.register_blueprint(main_blueprint)

    return app
