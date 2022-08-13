package me.bekzod.telegrambotstarter.utils.bot_constants;

public interface Secure {

    String USERNAME = "Maktab_Uz_Bot";
    String TOKEN = "5579803946:AAExqp_dBGu4LB_fgiuFGSJd8KA6YmPBt0g";


    String BOT_TOKEN = "bot" + TOKEN;

    String TELEGRAM_BASE = "https://api.telegram.org/";
    String TELEGRAM_GET_FILE_PATH = BOT_TOKEN + "/getFile";
    String TELEGRAM_GET_FILE = "file/" + BOT_TOKEN + "/";

    String SEND_MESSAGE = BOT_TOKEN + "/sendMessage";
    String ANSWER_CALLBACK_QUERY = BOT_TOKEN + "/answerCallbackQuery";
    String SEND_PHOTO = BOT_TOKEN + "/sendPhoto";
    String EDIT_MESSAGE_TEXT = BOT_TOKEN + "/editMessageText";
    String DELETE_MESSAGE = BOT_TOKEN + "/deleteMessage";
    String EDIT_MESSAGE_CAPTION = BOT_TOKEN + "/editMessageCaption";

}
