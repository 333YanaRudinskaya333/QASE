package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dict.Elements;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.*;

public class ProjectsPage extends BasePage {

    private static final String PROJECT_NAME_FIELD = "#project-name";
    private static final String PROJECT_CODE_FIELD = "#project-code";
    private static final By PROJECTS_TITLE = By.xpath("//h1[contains(text(),'Projects')]");
    private static final String ACTION_MENU_BUTTON = "button[aria-label='Open action menu']";
    private static final String REMOVE_OPTION = "[data-testid=remove]";
    private static final String CONFIRM_DELETE_BUTTON = "//span[text()='Delete project']";
    private static final String USER_AVATAR_BUTTON = "img[aria-label='User avatar']";
    private static final String ROWS_PER_PAGE_SELECT = "div[id^='paginator-per-page']";
    private static final SelenideElement rowsPerPageDropdown = $(ROWS_PER_PAGE_SELECT);

    public ProjectsPage openPage() {
        open("/projects");
        return this;
    }

    public ProjectPage createProject() {
        $(byText(CREATE_NEW_PROJECT_BUTTON)).click();
        $(PROJECT_NAME_FIELD).setValue("222tt");
        $(PROJECT_CODE_FIELD).setValue("111dd");
        $(byText(CREATE_PROJECT_BUTTON)).click();
        return new ProjectPage();
    }

    public SelenideElement getProjectTitle() {
        return $(PROJECTS_TITLE);
    }

    public ProjectsPage deleteProject(String projectName) {
        $(byText(projectName)).shouldBe(visible);
        $(byText(projectName))
                .ancestor("tr")
                .find(ACTION_MENU_BUTTON)
                .click();
        $(REMOVE_OPTION).shouldBe(visible).click();
        $x(CONFIRM_DELETE_BUTTON).shouldBe(visible).click();
        return this;
    }

    public LoginPage signOut() {
        $(USER_AVATAR_BUTTON).click();
        $(byText(SIGN_OUT_BUTTON)).click();
        return new LoginPage();
    }

    public ProjectsPage clickSortByProjectsNameButton() {
        $(byText(PROJECTS_NAME_SORT_BUTTON)).click();
        return this;
    }

    public ElementsCollection getProjectNames() {
        return $$("table tbody tr td a").filter(Condition.not(Condition.empty));
    }

    public ProjectsPage selectRowsPerPage(int count) {
        $(ROWS_PER_PAGE_SELECT).shouldBe(visible).click();
        $(byText(String.valueOf(count))).shouldBe(visible).click();
        return this;
    }
    public SelenideElement getRowsPerPageDropdown() {
        return rowsPerPageDropdown;
    }

    @Override
    public ProjectsPage isPageOpened() {
        $(PROJECTS_TITLE).shouldBe(visible);
        return this;
    }
}
