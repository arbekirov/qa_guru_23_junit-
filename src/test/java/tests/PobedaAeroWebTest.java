package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tests.data.Language;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Параметризованные тесты на поиск значений")
@Tag("smoke")

public class PobedaAeroWebTest extends TestBase {


    public static void main(String[] args) {
        System.out.println(Language.Русcкий.description);
        System.out.println(Language.English.description);
    }
@EnumSource(Language.class)
    @ParameterizedTest(name = "Проверка текста для поискового запроса {0}" )
    void    pobedaAeroShouldDisplayCorrectText(Language language) {
    open("https://pobeda.aero/");
    $(".dp-xihlw1-root-root").click();
    $$(".dp-14vias7-root").find(text(language.name())).click();
    $(".dp-1wc4xl-root-root").shouldHave(text(language.description));



    }
}

