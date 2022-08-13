package me.bekzod.telegrambotstarter.service;

import lombok.RequiredArgsConstructor;
import me.bekzod.telegrambotstarter.model.entity.UserEntity;
import me.bekzod.telegrambotstarter.model.enums.BotState;
import me.bekzod.telegrambotstarter.model.enums.Language;
import me.bekzod.telegrambotstarter.model.payload.user.UserDTO;
import me.bekzod.telegrambotstarter.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;

import static me.bekzod.telegrambotstarter.model.enums.Language.*;
import static me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText.*;
import static me.bekzod.telegrambotstarter.utils.bot_constants.Secure.USERNAME;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO checkAndGet(Message message) {
        UserEntity userEntity = userRepository
                .findByChatIdAndActiveTrue(message.getChatId().toString())
                .orElseGet(() -> {
                    User from = message.getFrom();
                    UserEntity user = UserEntity.builder()
                            .chatId(message.getChatId().toString())
                            .state(BotState.STATE_GET_LANGUAGE)
                            .firstname(from.getFirstName())
                            .username(from.getUserName())
                            .language(UZ)
                            .build();
                    return Objects.nonNull(from.getUserName()) &&
                            from.getUserName().equals(USERNAME) ?
                            user : userRepository.save(user);
                });
        return UserDTO
                .builder()
                .userEntity(userEntity)
                .build();
    }


    public Language saveLanguage(CallbackQuery query) {
        Language lan = query.getData().equals(LANGUAGE) ? UZ : query.getData().equals(LANGUAGE_RU) ? RU : KRILL;
        userRepository.saveLanguage(query.getMessage().getChatId().toString(), lan.name());
        return lan;
    }

    public void saveState(String chatId, BotState state) {
        userRepository.saveState(chatId, state.name());
    }

    public void savePhone(Long chatId, Contact contact) {
        userRepository.savePhone(chatId.toString(), contact.getPhoneNumber());
    }
}
