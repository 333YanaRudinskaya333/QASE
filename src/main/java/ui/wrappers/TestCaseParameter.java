package ui.wrappers;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCaseParameter {
    private final int index;

    public TestCaseParameter(int index) {
        this.index = index;
    }

    public void fill(String parameterName, String parameterValue) {
        log.info("Заполнение тест-кейс параметра №{} (Имя: '{}', Значение: '{}')", index, parameterName, parameterValue);
        log.debug("Инициализация локаторов для строки параметров №{}", index);
        By parameterNameInput = By.xpath("(//input[@placeholder='Parameter' or contains(@id, 'parameter')])[" + index + "]");
        By parameterValueInput = By.xpath("(//input[@placeholder='Value' or contains(@id, 'value')])[" + index + "]");

        if (parameterName != null) {
            log.debug("Ожидание отображения и ввод имени параметра №{}: '{}'", index, parameterName);
            $(parameterNameInput).shouldBe(Condition.visible).setValue(parameterName);
        }

        if (parameterValue != null) {
            log.debug("Ожидание отображения и ввод значения параметра №{}: '{}'", index, parameterValue);
            $(parameterValueInput).shouldBe(Condition.visible).setValue(parameterValue);
        }
        log.info("Параметр №{} успешно заполнен", index);
    }
}
