import requests

from utils.constants import BASE_API_URL

def post_mock_attraction(mock_attraction:dict) -> requests.Response:
    res = requests.post(f"{BASE_API_URL}/mock/attractions", json=mock_attraction)
    return res
