package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
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

    @Step("Открыть главную страницу проектов")
    public ProjectsPage openPage() {
        open(PROJECTS_PAGE);
        return this;
    }

    @Step("Создать новый проект")
    public ProjectPage createProject() {
        log.info("Creating a new project with Name: '222tt' and Code: '111dd'");
        $(byText(CREATE_NEW_PROJECT_BUTTON)).click();
        $(PROJECT_NAME_FIELD).setValue("222tt");
        $(PROJECT_CODE_FIELD).setValue("111dd");
        $(byText(CREATE_PROJECT_BUTTON)).click();
        log.info("Project '222tt' successfully submitted for creation");
        return new ProjectPage();
    }

    public SelenideElement getProjectTitle() {
        log.debug("Getting projects title element");
        return $(PROJECTS_TITLE);
    }

    @Step("Удалить проект с именем: '{projectName}'")
    public ProjectsPage deleteProject(String projectName) {
        log.info("Deleting project: '{}'", projectName);
        $(byText(projectName)).shouldBe(visible);
        log.debug("Opening action menu for project '{}'", projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find(ACTION_MENU_BUTTON)
                .click();
        log.debug("Clicking on 'Remove' option");
        $(REMOVE_OPTION).shouldBe(visible).click();
        log.info("Confirming deletion of project '{}'", projectName);
        $x(CONFIRM_DELETE_BUTTON).shouldBe(visible).click();
        return this;
    }

    @Step("Выйти из учетной записи (Sign Out)")
    public LoginPage signOut() {
        log.info("Signing out from the application");
        $(USER_AVATAR_BUTTON).click();
        $(byText(SIGN_OUT_BUTTON)).click();
        return new LoginPage();
    }

    @Step("Кликнуть по кнопке сортировки проектов по имени")
    public ProjectsPage clickSortByProjectsNameButton() {
        log.info("Sorting projects by name");
        $(byText(PROJECTS_NAME_SORT_BUTTON)).click();
        return this;
    }

    public ElementsCollection getProjectNames() {
        log.debug("Retrieving all non-empty project names from the table");
        return $$("table tbody tr td a").filter(Condition.not(Condition.empty));
    }

    @Step("Изменить количество отображаемых строк на страницу на: {count}")
    public ProjectsPage selectRowsPerPage(int count) {
        log.info("Changing rows per page display limit to: {}", count);
        $(ROWS_PER_PAGE_SELECT).shouldBe(visible).click();
        $(byText(String.valueOf(count))).shouldBe(visible).click();
        return this;
    }
    public SelenideElement getRowsPerPageDropdown() {
        log.debug("Getting rows per page dropdown element");
        return rowsPerPageDropdown;
    }

    @Step("Инициировать создание проекта и отменить действие")
    public ProjectsPage cancelCreateProject() {
        log.info("Initiating project creation flow and then cancelling. Fill values - Name: '222tt', Code: '111dd'");
        $(byText(CREATE_NEW_PROJECT_BUTTON)).click();
        $(PROJECT_NAME_FIELD).setValue("222tt");
        $(PROJECT_CODE_FIELD).setValue("111dd");
        log.info("Clicking cancel button on create project pop-up");
        $(byText(CANCEL_BUTTON_ON_CREATE_NEW_PROJECT_POP_UP)).click();
        return this;
    }

    @Override
    @Step("Проверить, что страница со списком проектов успешно открыта")
    public ProjectsPage isPageOpened() {
        log.debug("Checking if Projects page is opened");
        $(PROJECTS_TITLE).shouldBe(visible);
        log.info("Projects page is successfully opened");
        return this;
    }
}
