package me.bekzod.telegrambotstarter.model.payload.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.bekzod.telegrambotstarter.model.entity.UserEntity;
import me.bekzod.telegrambotstarter.model.enums.BotState;
import me.bekzod.telegrambotstarter.model.enums.Language;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {

    BotState state;
    Language language;


    @Builder(builderMethodName = "builderByEntity")
    public UserDTO(UserEntity userEntity) {
        this.state = userEntity.getState();
        this.language = userEntity.getLanguage();
    }

}
