package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {
    private final SelenideElement table = $(".table-responsive");

    public ResultTableComponent checkIfTableIsVisible() {
        table.shouldBe(visible);
        return this;
    }

    public ResultTableComponent checkIfTableIsNotVisible() {
        table.shouldNotBe(visible);
        return this;
    }

    public ResultTableComponent checkResult(String key, String value) {
        table.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}
