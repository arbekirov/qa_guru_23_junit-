package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tests.data.Language;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Параметризованные тесты на поиск значений")
@Tag("smoke")

public class PobedaAeroWebTest extends TestBase {


    public static void main(String[] args) {
        System.out.println(Language.EN.description);
        System.out.println(Language.РУ.description);
    }
@EnumSource(Language.class)
    @ParameterizedTest(name = "Проверка текста для поискового запроса {0}" )
    void    pobedaAeroShouldDisplayCorrectText(Language language) {
    open("https://pobeda.aero/");
    $(".dp-1c04wlv-root-root").click();
    $$(".dp-14vias7-root").find(text(language.name())).click();
    $(".dp-1hb6ons-root").shouldHave(text(language.description));
    }
}

