from telegram import Update, InlineKeyboardButton, InlineKeyboardMarkup
from telegram.constants import ParseMode
from telegram.ext import ContextTypes

from utils.constants import RECO_INTRO
from services.user_service import get_locations
from services.attraction_service import get_attraction
from utils.middleware import bson_to_image, recommendation_response

async def recommendation_start(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
    keyboard = [
        [
            InlineKeyboardButton("Restaurant", callback_data="RESTAURANT"),
            InlineKeyboardButton("Cafe", callback_data="CAFE"),
            InlineKeyboardButton("Dessert", callback_data="DESSERT")
        ],
        [
            InlineKeyboardButton("Exercise", callback_data="EXERCISE"),
            InlineKeyboardButton("Sightseeing", callback_data="SIGHTS"),
            InlineKeyboardButton("Others", callback_data="OTHERS")
        ]
    ]
    await update.message.reply_text(RECO_INTRO, reply_markup=InlineKeyboardMarkup(keyboard))
    return 1

async def recommendation_quantity(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
    query = update.callback_query
    await query.answer()

    user_data = context.user_data
    user_data["Type"] = query.data

    keyboard = [
        [
            InlineKeyboardButton("1", callback_data="1"),
            InlineKeyboardButton("2", callback_data="2"),
            InlineKeyboardButton("3", callback_data="3")
        ]
    ]
    await query.edit_message_text("How many places should I recommend", reply_markup=InlineKeyboardMarkup(keyboard))
    return 2

async def recommendation_end(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
    query = update.callback_query
    await query.answer()
    user_data = context.user_data
    response = get_locations({
        "type": user_data['Type'],
        "num": int(query.data)
    }, query.message.chat_id)
    if "locations" not in response:
        await query.edit_message_text(f"You have finished all the recommendations. Use /sync to import more recommendations from us!")
        return -1
    for id in response["locations"]:
        attraction = get_attraction(id)
        await query.delete_message()
        await query.message.reply_photo(photo=bson_to_image(attraction['photoId']), caption=recommendation_response(attraction), parse_mode=ParseMode.MARKDOWN)
    return -1