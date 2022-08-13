package me.bekzod.telegrambotstarter.feign;


import me.bekzod.telegrambotstarter.model.payload.telegramFeign.Result;
import me.bekzod.telegrambotstarter.model.payload.telegramFeign.ResultDelete;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import static me.bekzod.telegrambotstarter.utils.bot_constants.Secure.*;


@FeignClient(url = TELEGRAM_BASE, name = "TelegramFeign")
public interface TelegramFeign {

    @PostMapping(SEND_MESSAGE)
    Result sendMessage(@RequestBody SendMessage sendMessage);

    @PostMapping(ANSWER_CALLBACK_QUERY)
    Object answerCallbackQuery(@RequestBody AnswerCallbackQuery answerCallbackQuery);

    @PostMapping(EDIT_MESSAGE_TEXT)
    Result editMessageText(@RequestBody EditMessageText editMessageText);

    @PostMapping(DELETE_MESSAGE)
    ResultDelete deleteMessage(@RequestBody DeleteMessage deleteMessage);

    @PostMapping(EDIT_MESSAGE_CAPTION)
    Result editMessageCaption(@RequestBody EditMessageCaption editMessageCaption);
}
