package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Dropdown {

    private final String label;

    public Dropdown(String label) {
        this.label = label;
    }

    public void selectOption(String option) {
        log.info("Выбор опции '{}' в выпадающем списке '{}'", option, label);
        log.debug("Поиск элемента выпадающего списка с меткой: '{}'", label);
        SelenideElement dropdown = $x(
                "//label[contains(normalize-space(.),'" + label + "')]" +
                        "/following-sibling::div//*[@role='combobox']");
        log.debug("Ожидание отображения и клик по выпадающему списку для его раскрытия");
        dropdown.shouldBe(Condition.visible).click();
        log.debug("Поиск и выбор опции '{}' в раскрывшемся списке", option);
        $x("//div[@role='option' and normalize-space(.)='" + option + "']")
                .shouldBe(Condition.visible)
                .click();
        log.info("Опция '{}' успешно выбрана в выпадающем списке '{}'", option, label);
    }
}
