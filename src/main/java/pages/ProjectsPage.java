package pages;

import com.codeborne.selenide.Condition;
import dict.Elements;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static dict.Elements.*;

public class ProjectsPage extends BasePage {

    private static final String PROJECT_NAME_FIELD = "#project-name";
    private static final String PROJECT_CODE_FIELD = "#project-code";
    private static final By PROJECTS_TITLE = By.xpath("//h1[text()='Projects']");

    public ProjectsPage openPage() {
        open("/projects");
        return this;
    }

    public ProjectPage createProject() {
        $(byText(Elements.CREATE_NEW_PROJECT_BUTTON)).click();
        $(PROJECT_NAME_FIELD).setValue("222tt");
        $(PROJECT_CODE_FIELD).setValue("111dd");
        $(byText(Elements.CREATE_PROJECT_BUTTON)).click();
        return new ProjectPage();
    }

    @Override
    public ProjectsPage isPageOpened() {
        $(PROJECTS_TITLE).shouldBe(Condition.visible);
        return this;
    }
}
