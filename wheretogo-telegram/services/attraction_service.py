import requests

from utils.constants import BASE_API_URL

ATT_URL = f"{BASE_API_URL}/attractions"

def get_attraction(attraction_id:str) -> dict:
    res = requests.get(f"{ATT_URL}/{attraction_id}")
    return res.json()