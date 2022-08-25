package me.bekzod.telegrambotstarter.service;

import me.bekzod.telegrambotstarter.model.enums.Language;
import me.bekzod.telegrambotstarter.repository.UserRepository;
import me.bekzod.telegrambotstarter.utils.bot_constants.ButtonText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeAll
    static void beforeAll() {
        MockitoAnnotations.openMocks(UserServiceTest.class);
    }


    @ParameterizedTest
    @MethodSource(value = "saveLanguageParams")
    void saveLanguage(Language outcome, String income) {
        Language language = userService.saveLanguage(income, 1234567L);
        assertEquals(outcome, language);
    }


    public static Stream<Arguments> saveLanguageParams() {
        return Stream.of(Arguments.of(
                Language.UZ, ButtonText.LANGUAGE,
                Language.RU, ButtonText.LANGUAGE_RU,
                Language.KRILL, ButtonText.LANGUAGE_KRILL
        ));
    }
}
