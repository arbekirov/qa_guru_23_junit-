package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;



import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;


@DisplayName("Параметризованные тесты на поиск значений")
@Tag("smoke")
public class WildberriesWebTest extends TestBase {
    @BeforeEach
    void setUp() {
        open("https://guru.wildberries.ru/");
    }
    @CsvSource(value = {
            "Sony , Sony",
            "Hyundai , Hyundai"
    })
    @ParameterizedTest
    void successfulSearchRes(String searchQuery, String expectedLink) {
        $(".Searchbar_Input__Y4eI3").setValue(searchQuery).pressEnter();
        $(".search-view_title__1bJ_M").should(Condition.visible);
        // Проверка наличия текста в поисковом запросе
        $$(".search-view_title__1bJ_M").shouldHave(CollectionCondition.texts(expectedLink));
    }
    @ValueSource(strings = {
            "Праздник", "Семья"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список запросов")
    void successfulSearchTest(String searchQuery) {
        $(".Searchbar_Input__Y4eI3").setValue(searchQuery).pressEnter();
        // Получаем коллекцию элементов, представляющих количество найденных запросов
        ElementsCollection searchviewСounts = $$(".search-view_count__G3F9W");

        // Проверяем, что количество запросов больше нуля
        searchviewСounts.shouldHave(sizeGreaterThan(0));
    }
}