package wrappers;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Input {
    private final By inputLocator;
     private final String label;

    public Input(String label) {
        this.label = label;
        this.inputLocator = By.xpath("//label[text()='" + label + "']/following-sibling::div//input | //label[text()='" + label + "']/..//input");
    }

    public void write(String text) {
        $(inputLocator).shouldBe(Condition.visible).clear();
        $(inputLocator).setValue(text);
    }
}
