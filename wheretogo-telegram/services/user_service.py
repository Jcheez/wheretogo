import requests

from utils.constants import BASE_API_URL

USERS_URL = f"{BASE_API_URL}/users"

def get_user(chat_id: int) -> requests.Response:
    res = requests.get(f"{USERS_URL}/{chat_id}")
    return res

def add_user(json_body: dict) -> requests.Response:
    res = requests.post(f"{USERS_URL}", json=json_body)
    return res

def get_locations(query_params:dict, chat_id:int) -> dict:
    res = requests.get(f"{USERS_URL}/locations/{chat_id}", params=query_params)
    return res.json()

def sync_locations(chat_id:int) -> requests.Response:
    res = requests.put(f"{USERS_URL}/{chat_id}")
    return res