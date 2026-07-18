package ui.wrappers;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {
    private final By inputLocator;
    private final String label;

    public Input(String label) {
        this.label = label;
        this.inputLocator = By.xpath("//label[text()='" + label + "']/following-sibling::div//input | //label[text()='" + label + "']/..//input");
    }

    public void write(String text) {
        log.info("Ввод текста в поле '{}'", label);
        log.debug("Ожидание отображения поля '{}' и его очистка", label);
        $(inputLocator).shouldBe(Condition.visible).clear();
        log.debug("Заполнение поля '{}' значением: '{}'", label, text);
        $(inputLocator).setValue(text);
        log.info("Текст успешно введен в поле '{}'", label);
    }
}
