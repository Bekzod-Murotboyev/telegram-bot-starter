package me.bekzod.telegrambotstarter.utils.helper;

import lombok.SneakyThrows;
import me.bekzod.telegrambotstarter.model.enums.Language;
import me.bekzod.telegrambotstarter.model.payload.LanDTO;
import me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText;
import me.bekzod.telegrambotstarter.utils.bot_constants.MessageText;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static me.bekzod.telegrambotstarter.model.enums.Language.KRILL;
import static me.bekzod.telegrambotstarter.model.enums.Language.RU;

@Component
public class Dict {

    @SneakyThrows
    public String get(String word, Language lan) {
        CompletableFuture<LanDTO> btnField = CompletableFuture.supplyAsync(() -> Arrays.stream(ButtonText.class.getDeclaredFields())
                .filter(field -> filterWord(field, word, ButtonText.class))
                .findFirst()
                .map(field -> new LanDTO(ButtonText.class, field.getName()))
                .orElse(null));
        CompletableFuture<LanDTO> messageField = CompletableFuture.supplyAsync(() -> Arrays.stream(MessageText.class.getDeclaredFields())
                .filter(field -> filterWord(field, word, MessageText.class))
                .findFirst()
                .map(field -> new LanDTO(MessageText.class, field.getName()))
                .orElse(null));
        CompletableFuture.allOf(btnField, messageField).get();
        LanDTO dto = Objects.requireNonNullElse(btnField.get(), messageField.get());
        return dto.getClazz()
                .getDeclaredField(dto.getName() + (lan.equals(RU) ? "_RU" : lan.equals(KRILL) ? "_KRILL" : ""))
                .get(dto.getClazz()).toString();
    }


    @SneakyThrows
    private boolean filterWord(Field field, String word, Class<?> clazz) {
        return field.get(clazz).equals(word);
    }

}
