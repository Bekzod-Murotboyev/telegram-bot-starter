package me.bekzod.telegrambotstarter.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.bekzod.telegrambotstarter.model.entity.base.BaseEntity;
import me.bekzod.telegrambotstarter.model.enums.BotState;
import me.bekzod.telegrambotstarter.model.enums.Language;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    String firstname;

    String username;

    @Column(unique = true)
    String phone;

    @Column(nullable = false)
    String chatId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    BotState state;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Language language;
}
