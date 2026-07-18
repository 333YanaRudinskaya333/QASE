package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Checkbox {
    private final String labelText;

    public Checkbox(String labelText) {
        this.labelText = labelText;
    }

    public void setStatus(boolean expectChecked) {
        log.info("Установка чекбокса '{}' в состояние: {}", labelText, expectChecked);
        log.debug("Поиск родительского контейнера для элемента с текстом: '{}'", labelText);
        SelenideElement container = $x("//label[contains(., '" + labelText + "')] " +
                "| //*[contains(text(), '" + labelText + "')]/ancestor::label " +
                "| //*[contains(text(), '" + labelText + "')]/..");
        log.debug("Поиск скрытого элемента input внутри контейнера чекбокса");
        SelenideElement hiddenInput = container.$x(".//input[@type='checkbox']");
        log.debug("Ожидание отображения контейнера на странице");
        container.shouldBe(Condition.visible);
        boolean isCurrentlyChecked = hiddenInput.exists() ? hiddenInput.isSelected() : false;
        log.info("Текущий внутренний статус чекбокса '{}' (выбран: {})", labelText, isCurrentlyChecked);
        if (isCurrentlyChecked != expectChecked) {
            log.info("Клик по контейнеру чекбокса для изменения состояния на {}", expectChecked);
            container.click();
        } else {
            log.info("Чекбокс '{}' уже находится в ожидаемом состоянии: {}", labelText, expectChecked);
        }
    }
}

