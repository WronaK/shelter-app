import os


class Config:
    SECRET_KEY = '123'  # os.environ.get('SECRET_KEY') or 'hard to guess string'
    SQLALCHEMY_TRACK_MODIFICATIONS = False

    @staticmethod
    def init_app(app):
        pass


class DevelopmentConfig(Config):
    TESTING = True
    DIALECT = 'postgresql'
    USERNAME = 'small_dog'
    PASSWORD = '123'
    HOST = '172.18.0.1'  # hardcoded cause wasn't working other way
    PORT = 7432
    SERVICE = 'small_dog'
    SQLALCHEMY_DATABASE_URI = DIALECT + '://' + USERNAME + ':' + PASSWORD + '@' + HOST + ':' + \
                              str(PORT) + "/" + SERVICE


config = {
    'development': DevelopmentConfig,
    'default': DevelopmentConfig
}