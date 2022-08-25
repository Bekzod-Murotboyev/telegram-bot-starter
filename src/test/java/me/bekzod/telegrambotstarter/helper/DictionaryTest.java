package me.bekzod.telegrambotstarter.helper;

import me.bekzod.telegrambotstarter.model.enums.Language;
import me.bekzod.telegrambotstarter.utils.helper.Dict;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DictionaryTest {


    @Autowired
    Dict dict;

    @ParameterizedTest
    @DisplayName("TEST FOR MULTIPLE LANGUAGE")
    @CsvFileSource(resources = {"language/language-button.csv", "language/language-message-text.csv"})
    public void testWords(String base, String word, Language lan) {
        assertEquals(word, dict.get(base, lan), base + " is not written in " + lan);
    }
}
