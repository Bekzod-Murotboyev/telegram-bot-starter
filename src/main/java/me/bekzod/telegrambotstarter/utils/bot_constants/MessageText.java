package me.bekzod.telegrambotstarter.utils.bot_constants;

/*
IT IS IMPORTANT TO WRITE WORDS WITH SUFFIX:
  UZBEK   ->  NO PREFIX
  KRILL   ->  '_KRILL'
  RUSSIAN ->  '_RU'
BECAUSE LANGUAGE OF APP IS MANAGED BY THIS RULE
 */

public interface MessageText {
    String COMMAND_START = "/start";
    String GET_LANGUAGE_TEXT = "Assalomu alaykum:\t%s\nTilni tanlang!\n\n" +
            "Здравствуйте:\t%s\nвыберите язык!";

    // ------------------
    String GET_PHONE_TEXT = "☎️ Telefon raqamingizni yuboring:";
    String GET_PHONE_TEXT_KRILL = "☎️ Телефон рақамингизни юборинг:";
    String GET_PHONE_TEXT_RU = "☎️ Отправьте свой номер телефона:";

    String CHOOSE_ACTION = "👇 Kerakli bo'limni tanlang...";
    String CHOOSE_ACTION_KRILL = "👇 Керакли бўлимни танланг...";
    String CHOOSE_ACTION_RU = "👇 Выберите нужный раздел...";

    String BACK_ARROW = " ⬅️⬅️⬅️";
}
