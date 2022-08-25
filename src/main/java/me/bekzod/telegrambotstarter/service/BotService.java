package me.bekzod.telegrambotstarter.service;

import lombok.RequiredArgsConstructor;
import me.bekzod.telegrambotstarter.feign.TelegramFeign;
import me.bekzod.telegrambotstarter.model.enums.BotState;
import me.bekzod.telegrambotstarter.model.enums.Language;
import me.bekzod.telegrambotstarter.model.payload.user.UserDTO;
import me.bekzod.telegrambotstarter.utils.helper.Keyboard;
import me.bekzod.telegrambotstarter.utils.helper.Sender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText.*;
import static me.bekzod.telegrambotstarter.utils.bot_constants.MessageText.*;

@Service
@RequiredArgsConstructor
public class BotService {

    private final TelegramFeign feign;
    private final UserService userService;
    private final Sender sender;

    private final Keyboard keyboard;

    public UserDTO checkAndGet(Update update) {
        Message message = getMessage(update);
        return userService.checkAndGet(message.getFrom(),message.getChatId());
    }

    public void saveChanges(Update update, BotState state) {
        userService.saveState(getMessage(update).getChatId().toString(), state);
    }

    private Message getMessage(Update update) {
        return update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
    }

    private DeleteMessage deleteMessage(Message message) {
        return new DeleteMessage(message.getChatId().toString(), message.getMessageId());
    }

    public DeleteMessage deleteMessage(Update update) {
        return deleteMessage(getMessage(update));
    }

    public SendMessage getLanguageMenu(Update update) {
        Message message = getMessage(update);
        String name = message.getFrom().getFirstName();
        return sender.sendMessage(
                message.getChatId(),
                String.format(GET_LANGUAGE_TEXT, name, name),
                keyboard.createInlineMarkup(List.of(List.of(keyboard.getInlineButton(LANGUAGE)),
                        List.of(keyboard.getInlineButton(LANGUAGE_KRILL)),
                        List.of(keyboard.getInlineButton(LANGUAGE_RU)))));
    }

    public SendMessage getPhoneNumberMenu(Update update) {
        CallbackQuery query = update.getCallbackQuery();
        new Thread(() -> feign.deleteMessage(deleteMessage(query.getMessage()))).start();
        return sender.sendContactMessage(query.getMessage(),
                userService.saveLanguage(query.getData(), query.getMessage().getChatId()), false);
    }

    public SendMessage getMainMenuSend(Update update, Language lan) {
        Message message = getMessage(update);
        feign.sendMessage(sender.sendMessageRemoveKeyboard(message.getChatId(), BACK_ARROW));
        if (message.hasContact())
            userService.savePhone(message.getChatId(), message.getContact());
        return sender.sendMessage(
                message.getChatId(),
                CHOOSE_ACTION,
                keyboard.getMainMenu(lan),
                lan);
    }
}
