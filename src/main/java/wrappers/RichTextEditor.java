package wrappers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class RichTextEditor {
    private final By labelLocator;
    private final String labelText;

    // Конструктор принимает текст лейбла (например, "Description" или "Pre-conditions")
    public RichTextEditor(String labelText) {
        this.labelText = labelText;
        this.labelLocator = By.xpath("//label[text()='" + labelText + "']"); // Находим лейбл по точному совпадению текста
    }

    public void write(String text) {
        $(labelLocator).click();// Кликаем по лейблу, чтобы перевести фокус ввода на нужное поле рядом
        actions().sendKeys(text).perform();  // Вводим текст через эмуляцию клавиатуры
    }
}
