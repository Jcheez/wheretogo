from telegram import Bot, Update
from telegram.ext import (
    ContextTypes, 
    ConversationHandler, 
    CommandHandler,
    CallbackQueryHandler,
    MessageHandler,
    filters
)

from utils.constants import INTRO, HELP, UNKNOWN_COMMAND
from services.user_service import *
from convos.reco_convo import *
from convos.req_convo import *

async def kill_conversation_handler(update:Update, context:ContextTypes.DEFAULT_TYPE) -> int:
    await update.message.reply_text("Ending all ongoing conversations you might have with me...")
    return -1

async def start_handler(update:Update, context:ContextTypes.DEFAULT_TYPE) -> None:
    await update.message.reply_chat_action("typing")
    chat_id = update.message.chat_id
    res = get_user(chat_id)
    if res.status_code == 500:
        add_user({
            "chatId": chat_id,
            "userName": update.effective_user.username,
            "firstName": update.effective_user.first_name,
        })
        await update.message.reply_text("NEW USER DETECTED...")
    else:
        await update.message.reply_text("WELCOME BACK... Restoring previous settings")
    await update.message.reply_text(INTRO)
    
recommendation_handler = ConversationHandler(
    entry_points=[CommandHandler("recommend", recommendation_start)],
    states={
        1: [
            CallbackQueryHandler(recommendation_quantity)
        ],
        2: [
            CallbackQueryHandler(recommendation_end)
        ]
    },
    fallbacks=[CommandHandler("cancel", kill_conversation_handler)]
)

request_handler = ConversationHandler(
    entry_points=[CommandHandler("request", request_start)],
    states={
        1: [
            MessageHandler(~filters.COMMAND, request_cost)
        ],
        2: [
            MessageHandler(~filters.COMMAND, request_type)
        ],
        3: [
            CallbackQueryHandler(request_end)
        ]
    },
    fallbacks=[CommandHandler("cancel", kill_conversation_handler)]
)

async def sync_locations_handler(update:Update, context:ContextTypes.DEFAULT_TYPE) -> None:
    await update.message.reply_chat_action("typing")
    await update.message.reply_text("Syncing new locations to you...")
    sync_locations(update.message.chat_id)
    await update.message.reply_text("Locations have been successfully synced")
    

async def help_handler(update:Update, context:ContextTypes.DEFAULT_TYPE) -> None:
    await update.message.reply_chat_action("typing")
    await update.message.reply_text(HELP)

async def unknown_command_handler(update:Update, context:ContextTypes.DEFAULT_TYPE) -> None:
    await update.message.reply_chat_action("typing")
    await update.message.reply_text(UNKNOWN_COMMAND)