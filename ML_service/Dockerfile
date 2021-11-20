FROM python:3.8

WORKDIR /ml-service-folder

COPY . /ml-service-folder
RUN mkdir -p endpoint_locks

RUN apt-get update
RUN apt-get install -y libpq-dev

RUN pip install --no-cache-dir -r requirements.txt

ENV FLASK_APP=run.py
ENV FLASK_CONFIG=development

CMD ["gunicorn", "-w", "4", "-b", "0.0.0.0:5000", "-t", "100000", "run:get_app()"]