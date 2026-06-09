package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.*;

public class ProjectPage extends BasePage {

    public SelenideElement getProjectTitleElement() {
        return $(REPOSITORY_TITLE);
    }

    @Override
    public ProjectPage isPageOpened() {
        $(byText(IMPORT_BUTTON)).shouldBe(Condition.visible);
        return this;
    }
}
