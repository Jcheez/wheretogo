import requests

from utils.constants import BASE_API_URL

PHOTOS_URL = f"{BASE_API_URL}/photos"

def get_photo(photoId:str) -> dict:
    res = requests.get(f"{PHOTOS_URL}/{photoId}")
    return res.json()