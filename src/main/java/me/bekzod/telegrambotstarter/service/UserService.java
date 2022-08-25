package me.bekzod.telegrambotstarter.service;

import lombok.RequiredArgsConstructor;
import me.bekzod.telegrambotstarter.model.entity.UserEntity;
import me.bekzod.telegrambotstarter.model.enums.BotState;
import me.bekzod.telegrambotstarter.model.enums.Language;
import me.bekzod.telegrambotstarter.model.payload.user.UserDTO;
import me.bekzod.telegrambotstarter.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;

import static me.bekzod.telegrambotstarter.model.enums.Language.*;
import static me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText.LANGUAGE;
import static me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText.LANGUAGE_RU;
import static me.bekzod.telegrambotstarter.utils.bot_constants.Secure.USERNAME;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO checkAndGet(User from, Long chatId) {
        UserEntity userEntity = userRepository
                .findByChatIdAndActiveTrue(chatId.toString())
                .orElseGet(() -> {
                    UserEntity user = UserEntity
                            .builder()
                            .chatId(chatId.toString())
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


    public Language saveLanguage(String lang, Long chatId) {
        Language lan = lang.equals(LANGUAGE) ? UZ : lang.equals(LANGUAGE_RU) ? RU : KRILL;
        userRepository.saveLanguage(chatId.toString(), lan.name());
        return lan;
    }

    public void saveState(String chatId, BotState state) {
        userRepository.saveState(chatId, state.name());
    }

    public void savePhone(Long chatId, Contact contact) {
        userRepository.savePhone(chatId.toString(), contact.getPhoneNumber());
    }
}
