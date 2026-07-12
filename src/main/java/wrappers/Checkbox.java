package wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Checkbox {
    private final String labelText;

    public Checkbox(String labelText) {
        this.labelText = labelText;
    }

    public void setStatus(boolean expectChecked) {
        System.out.println("LOG: Устанавливаем чекбоксу '" + labelText + "' состояние: " + expectChecked);
// Находим родительский контейнер (в Qase это может быть label или div/ui-reset)
        SelenideElement container = $x("//label[contains(., '" + labelText + "')] " +
                "| //*[contains(text(), '" + labelText + "')]/ancestor::label " +
                "| //*[contains(text(), '" + labelText + "')]/..");

// Находим скрытый input внутри этого блока, чтобы узнать статус
        SelenideElement hiddenInput = container.$x(".//input[@type='checkbox']");
// Ждем, пока контейнер станет видимым
        container.shouldBe(Condition.visible);
// Исправлено: используем exists() вместо exist()
        boolean isCurrentlyChecked = hiddenInput.exists() ? hiddenInput.isSelected() : false;


// Если текущее состояние не совпадает с ожидаемым — кликаем
        if (isCurrentlyChecked != expectChecked) {
            container.click();
        }
    }
}

