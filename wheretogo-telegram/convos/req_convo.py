from telegram import InlineKeyboardMarkup, Update, InlineKeyboardButton
from telegram.ext import ContextTypes

from utils.constants import REQ_END, REQ_INTRO

from services.mock_attraction_service import post_mock_attraction

'''
TECHDEBT
cost validation (make sure its a integer)
Validation of attraction type
'''

async def request_start(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
    await update.message.reply_text(REQ_INTRO)
    return 1

async def request_cost(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
    location_name = update.message.text
    user_data = context.user_data
    user_data["location"] = location_name
    await update.message.reply_text(f"How about the rough cost of {location_name}?")
    return 2

async def request_type(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
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
    cost = update.message.text
    user_data = context.user_data
    user_data["cost"] = int(cost)
    await update.message.reply_text(f"Which of the following best describes {user_data['location']}?", reply_markup=InlineKeyboardMarkup(keyboard))
    return 3

async def request_end(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
    query = update.callback_query
    await query.answer()
    user_data = context.user_data
    post_mock_attraction({
        "name": user_data["location"],
        "cost": user_data["cost"],
        "type": query.data
    })
    await query.edit_message_text(REQ_END)
    return -1