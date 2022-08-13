package me.bekzod.telegrambotstarter.service;

import me.bekzod.telegrambotstarter.model.enums.BotState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static me.bekzod.telegrambotstarter.model.enums.BotState.*;
import static me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText.*;
import static me.bekzod.telegrambotstarter.utils.bot_constants.MessageText.COMMAND_START;

@Service
public class StateService {

    public BotState getState(Update update, BotState state) {
        if (update.hasMessage()) {
            return getStateByMessage(update.getMessage(), state);
        } else if (update.hasCallbackQuery()) {
            return getStateByQuery(update.getCallbackQuery(), state);
        }


        return STATE_WARNING;
    }

    private BotState getStateByQuery(CallbackQuery query, BotState state) {
        switch (query.getData()) {
            case LANGUAGE:
            case LANGUAGE_KRILL:
            case LANGUAGE_RU:
                return STATE_GET_PHONE_NUMBER;
            default:
                return STATE_WARNING;
        }
    }

    private BotState getStateByMessage(Message message, BotState state) {
        if (message.hasText()) {
            return getStateByMessageText(message.getText(), state);
        } else if (message.hasContact()) {
            return getStateByContact(state);
        }


        return STATE_WARNING;
    }

    private BotState getStateByContact(BotState state) {
        if (state.equals(STATE_GET_PHONE_NUMBER))
            return STATE_MAIN_MENU_SEND;
        return STATE_WARNING;
    }

    private BotState getStateByMessageText(String text, BotState state) {
        if (text.equals(COMMAND_START)) {
            if (state.equals(STATE_GET_LANGUAGE)) {
                return state;
            } else if (!state.equals(STATE_GET_PHONE_NUMBER))
                return STATE_MAIN_MENU_SEND;
        }
        return STATE_WARNING;
    }

}
