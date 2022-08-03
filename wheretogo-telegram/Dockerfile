FROM python:3.10.5-alpine3.15

WORKDIR /wheretogo-telegram

COPY requirements.txt .

RUN pip install -r requirements.txt

COPY ./convos ./convos

COPY ./services ./services

COPY ./utils ./utils

COPY main.py .

CMD ["python", "./main.py"]