package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
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
        open("https://www.wildberries.ru/");
    }

    @ValueSource(strings = {
            "Sony", "Hyundai"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
    void successfulSearchTest(String searchQuery) {
        $("#searchInput").setValue(searchQuery).pressEnter();
        // Получаем коллекцию элементов, представляющих продуктовые карточки
        ElementsCollection productCards = $$(".product-card");

        // Проверяем, что количество карточек больше нуля
        productCards.shouldHave(sizeGreaterThan(0));

    }

    @CsvSource(value = {
            "Sony , Продавайте на Wildberries",
            "Hyundai , Работа в Wildberries"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в хедере должен быть текст {1}")
    void successfulSearchResultHeadersWildBerriesTest(String searchQuery, String expectedLink) {
        $("#searchInput").setValue(searchQuery).pressEnter();
        // Проверка наличия текста в хедере
        $$(".header__container").shouldHave(CollectionCondition.texts(expectedLink));
    }
}