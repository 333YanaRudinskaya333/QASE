package wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {

    private final String label;

    public Dropdown(String label) {
        this.label = label;
    }

    public void selectOption(String option) {

        SelenideElement dropdown = $x(
                "//label[contains(normalize-space(.),'" + label + "')]" +
                        "/following-sibling::div//*[@role='combobox']");

        dropdown.shouldBe(Condition.visible).click();

        $x("//div[@role='option' and normalize-space(.)='" + option + "']")
                .shouldBe(Condition.visible)
                .click();
    }
}
