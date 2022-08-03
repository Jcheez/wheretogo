import base64

from services.photo_service import get_photo

def recommendation_location_formatter(locations: list[dict]):
    res = ""
    for location in locations:
        link = f"📍 [{location['address']}](https://maps.google.com/maps?q={location['latitude']},{location['longitude']})\n🚆 {location['nearestMRT']}\n"
        res += link
    return res

def bson_to_image(photoId:str):
    photo = get_photo(photoId)
    f = photo["image"]["data"]
    return base64.decodebytes(bytes(f, "utf-8"))

def recommendation_response(recommendation: dict):
    response = f"""
{recommendation['name']}

{recommendation['description']}

💰 ~  ${recommendation['cost']}

Outlets:
{(recommendation_location_formatter(recommendation['locations']))}
👉 [Read More]({recommendation['readMoreLink']})
"""
    return response

