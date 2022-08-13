package me.bekzod.telegrambotstarter.utils.helper;

import lombok.RequiredArgsConstructor;
import me.bekzod.telegrambotstarter.model.enums.Language;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText.BACK;
import static me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText.MY_PHONE_NUMBER;


@Component
@RequiredArgsConstructor
public class Keyboard {

    private final Dict dict;

    public InlineKeyboardMarkup createInlineMarkup(List<List<String>> rows, Language lan) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (List<String> row : rows) {
            List<InlineKeyboardButton> dRow = new ArrayList<>();
            for (String word : row) {
                InlineKeyboardButton button;
                if (!word.contains("BACK"))
                    button = new InlineKeyboardButton(word);
                else
                    button = new InlineKeyboardButton(dict.get(word, lan));
                button.setCallbackData(word);
                dRow.add(button);
            }
            rowList.add(dRow);
        }
        return new InlineKeyboardMarkup(rowList);
    }
    public InlineKeyboardMarkup createInlineMarkup(List<List<InlineKeyboardButton>> rows) {
        return new InlineKeyboardMarkup(rows);
    }


    public InlineKeyboardButton getInlineButton(String text) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(text);
        return button;
    }
    public InlineKeyboardButton getInlineButton(String data, String text) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(data);
        return button;
    }

    public InlineKeyboardButton getInlineButton(String data, String text, Language lan) {
        InlineKeyboardButton button = new InlineKeyboardButton(dict.get(text, lan));
        button.setCallbackData(data);
        return button;
    }

    public InlineKeyboardButton getInlineButton(String data, String text, String url, Language lan) {
        InlineKeyboardButton button = new InlineKeyboardButton(dict.get(text, lan));
        button.setCallbackData(data);
        button.setUrl(url);
        return button;
    }

    public ReplyKeyboardMarkup createContactMarkup(boolean hasBackSide, Language lan) {
        KeyboardButton contactBtn = new KeyboardButton(dict.get(MY_PHONE_NUMBER, lan));
        contactBtn.setRequestContact(true);
        return hasBackSide ? new ReplyKeyboardMarkup(List.of(
                new KeyboardRow(List.of(contactBtn)),
                new KeyboardRow(List.of(new KeyboardButton(dict.get(BACK, lan))))), true, false, true, "next")
                : new ReplyKeyboardMarkup(List.of(new KeyboardRow(List.of(contactBtn))), true, false, true, "next");
    }

    // ------------------------------------------------------------------------------------------
    public InlineKeyboardMarkup getMainMenu(Language lan){
        return createInlineMarkup(List.of(
           List.of(BACK)
          ),lan);
    }
}
