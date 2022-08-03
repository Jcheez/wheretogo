from telegram.ext import (
    Application,
    CommandHandler,
    filters
)
import os
from dotenv import load_dotenv

from utils.handlers import *
'''
WebHook URL
https://api.telegram.org/bot<TELEGRAM_TOKEN>/setWebhook?url=<URL>
'''

def main() -> None:
    load_dotenv()
    
    app = Application.builder().token(os.getenv("TOKEN")).build()
    app.add_handler(CommandHandler("start", start_handler))
    app.add_handler(CommandHandler("help", help_handler))
    app.add_handler(CommandHandler("sync", sync_locations_handler))
    app.add_handler(recommendation_handler)
    app.add_handler(request_handler)
    app.add_handler(CommandHandler("cancel", kill_conversation_handler))
    app.add_handler(MessageHandler(filters.COMMAND, unknown_command_handler))
    app.run_polling()

if __name__ == "__main__":
    main()
     