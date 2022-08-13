package me.bekzod.telegrambotstarter.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.bekzod.telegrambotstarter.model.enums.BotState;
import me.bekzod.telegrambotstarter.model.payload.user.UserDTO;
import me.bekzod.telegrambotstarter.service.BotService;
import me.bekzod.telegrambotstarter.service.StateService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static me.bekzod.telegrambotstarter.utils.bot_constants.Secure.TOKEN;
import static me.bekzod.telegrambotstarter.utils.bot_constants.Secure.USERNAME;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final BotService botService;
    private final StateService stateService;

    @Override
    public void onUpdateReceived(Update update) {
        UserDTO userDTO = botService.checkAndGet(update);
        userDTO.setState(stateService.getState(update, userDTO.getState()));
        if (!userDTO.getState().equals(BotState.STATE_WARNING))
            botService.saveChanges(update, userDTO.getState());
        execute(update, userDTO);
    }

    @SneakyThrows
    private void execute(Update update, UserDTO userDTO) {
        switch (userDTO.getState()) {
            case STATE_WARNING:
                execute(botService.deleteMessage(update));
                break;
            case STATE_GET_LANGUAGE:
                execute(botService.getLanguageMenu(update));
                break;
            case STATE_GET_PHONE_NUMBER:
                execute(botService.getPhoneNumberMenu(update));
                break;
            case STATE_MAIN_MENU_SEND:
                execute(botService.getMainMenuSend(update, userDTO.getLanguage()));
                break;
        }
    }


    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }


}
