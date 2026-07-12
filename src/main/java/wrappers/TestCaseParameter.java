package wrappers;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TestCaseParameter {
    private final int index;

    // Конструктор принимает порядковый номер строки параметров (начиная с 1)
    public TestCaseParameter(int index) {
        this.index = index;
    }

    public void fill(String parameterName, String parameterValue) {
        System.out.println("LOG: Заполняем параметр №" + index + ": " + parameterName + " = " + parameterValue);
        // XPath находит нужный инпут по его порядковому номеру на странице
        By parameterNameInput = By.xpath("(//input[@placeholder='Parameter' or contains(@id, 'parameter')])[" + index + "]");
        By parameterValueInput = By.xpath("(//input[@placeholder='Value' or contains(@id, 'value')])[" + index + "]");

        if (parameterName != null) {
            $(parameterNameInput).shouldBe(Condition.visible).setValue(parameterName);
        }

        if (parameterValue != null) {
            $(parameterValueInput).shouldBe(Condition.visible).setValue(parameterValue);
        }
    }
}
