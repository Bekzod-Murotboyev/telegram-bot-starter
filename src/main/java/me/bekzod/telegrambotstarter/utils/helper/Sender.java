package me.bekzod.telegrambotstarter.utils.helper;

import lombok.RequiredArgsConstructor;
import me.bekzod.telegrambotstarter.model.enums.Language;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import java.util.List;

import static me.bekzod.telegrambotstarter.utils.bot_constants.MessageText.*;

@Component
@RequiredArgsConstructor
public class Sender {

    private final Keyboard keyboard;

    private final Dict lang;

    public SendMessage sendMessage(Long chatId, String text, InlineKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), text);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setParseMode(ParseMode.HTML);
        return sendMessage;
    }

    public SendMessage sendMessage(Long chatId, String text, List<List<String>> rows, Language lan) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), lang.get(text, lan));
        sendMessage.setReplyMarkup(keyboard.createInlineMarkup(rows, lan));
        sendMessage.setParseMode(ParseMode.HTML);
        return sendMessage;
    }

    public SendMessage sendMessage(String chatId, String text, List<List<String>> rows, Language lan) {
        SendMessage sendMessage = new SendMessage(chatId, lang.get(text, lan));
        sendMessage.setReplyMarkup(keyboard.createInlineMarkup(rows, lan));
        return sendMessage;
    }


    public SendMessage sendMessage(Long chatId, String text, ReplyKeyboardMarkup markup, Language lan) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), lang.get(text, lan));
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public SendMessage sendMessage(Long chatId, String text, InlineKeyboardMarkup markup, Language lan) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), lang.get(text, lan));
        sendMessage.setReplyMarkup(markup);
        sendMessage.setParseMode(ParseMode.HTML);
        return sendMessage;
    }

    public SendMessage sendMessage(Long chatId, String text, Language lan) {
        return new SendMessage(chatId.toString(), lang.get(text, lan));
    }

    public SendMessage sendMessage(String chatId, String text, Language lan) {
        return new SendMessage(chatId, lang.get(text, lan));
    }


    public SendMessage sendMessageRemoveKeyboard(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), text);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        return sendMessage;
    }

    public EditMessageText editMessageText(Message message, String text, Language lan) {
        EditMessageText editMessageText = new EditMessageText(lang.get(text, lan));
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setParseMode(ParseMode.HTML);
        return editMessageText;
    }

    public EditMessageText editMessageText(Message message, String text, List<List<String>> rows, Language lan) {
        EditMessageText editMessageText = editMessageText(message, text, lan);
        editMessageText.setReplyMarkup(keyboard.createInlineMarkup(rows, lan));
        editMessageText.setParseMode(ParseMode.HTML);
        return editMessageText;
    }

    public EditMessageText editMessageText(Message message, String text, InlineKeyboardMarkup markup, Language lan) {
        EditMessageText editMessageText = editMessageText(message, text, lan);
        editMessageText.setReplyMarkup(markup);
        editMessageText.setParseMode(ParseMode.HTML);
        return editMessageText;
    }

    public EditMessageCaption editMessageCaption(Long chatId, Integer messageId, String caption, InlineKeyboardMarkup markup, Language lan) {
        EditMessageCaption editMessageCaption = new EditMessageCaption();
        editMessageCaption.setChatId(chatId.toString());
        editMessageCaption.setMessageId(messageId);
        editMessageCaption.setCaption(caption);
        editMessageCaption.setReplyMarkup(markup);
        editMessageCaption.setParseMode(ParseMode.HTML);
        return editMessageCaption;
    }

    public SendMessage sendContactMessage(Message message, Language lan, boolean hasBackSide) {
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), lang.get(GET_PHONE_TEXT, lan));
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setReplyMarkup(keyboard.createContactMarkup(hasBackSide, lan));
        return sendMessage;
    }


}
