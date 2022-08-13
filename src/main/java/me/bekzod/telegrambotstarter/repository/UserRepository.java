package me.bekzod.telegrambotstarter.repository;


import me.bekzod.telegrambotstarter.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByChatIdAndActiveTrue(String chatId);


    @Transactional
    @Modifying
    @Query(value = "update users set language=:lan where chat_id=:chatId", nativeQuery = true)
    void saveLanguage(String chatId, String lan);

    @Modifying(flushAutomatically = true)
    @Transactional
    @Query(value = "update users set state=:state where chat_id=:chatId", nativeQuery = true)
    void saveState(@Param("chatId") String chatId, @Param("state") String state);


    @Modifying(flushAutomatically = true)
    @Transactional
    @Query(value = "update users set phone=:phone where chat_id=:chatId", nativeQuery = true)
    void savePhone(@Param("chatId") String chatId, @Param("phone") String phone);

}
