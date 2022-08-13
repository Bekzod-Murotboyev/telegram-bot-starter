package me.bekzod.telegrambotstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TelegramBotStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotStarterApplication.class, args);
    }

}
