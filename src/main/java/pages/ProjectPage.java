package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.*;

public class ProjectPage extends BasePage {
    public static final By REPOSITORY_TITLE = By.cssSelector("h2");

    public SelenideElement getProjectTitleElement() {
        return $(REPOSITORY_TITLE);
    }

    @Override
    public ProjectPage isPageOpened() {
        $(byText(IMPORT_BUTTON)).shouldBe(Condition.visible);
        return this;
    }
}
