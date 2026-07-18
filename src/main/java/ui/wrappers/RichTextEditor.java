package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class RichTextEditor {
    private final String labelText;

    public RichTextEditor(String labelText) {
        this.labelText = labelText;
    }

    public void write(String text) {
        log.info("Ввод текста в текстовый редактор '{}'", labelText);
        SelenideElement editorField = $x("//label[text()='" + labelText + "']/following-sibling::div//div[@contenteditable='true']" +
                " | //label[text()='" + labelText + "']/..//div[@contenteditable='true']");
        editorField.shouldBe(Condition.visible).click();
        editorField.clear();
        editorField.setValue(text);
        log.info("Текст успешно введен в редактор '{}'", labelText);
    }
}