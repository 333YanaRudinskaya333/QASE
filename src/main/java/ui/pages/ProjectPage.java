package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import ui.wrappers.Input;
import ui.wrappers.RichTextEditor;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ui.dict.Elements.*;

@Log4j2
public class ProjectPage extends BasePage {
    private final Input suiteNameInput = new Input("Suite name");
    private final RichTextEditor descriptionField = new RichTextEditor("Description");
    private final RichTextEditor preConditionsField = new RichTextEditor("Preconditions");

    public static final By REPOSITORY_TITLE = By.cssSelector("h2");
    public static final By CREATE_SUITE_ON_POP_UP_TITLE = By.cssSelector("h3");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_SUIT_CREATE = "//span[contains(text(), 'Suite was successfully created.')]";
    public static final By DELETE_SUITE_BUTTON = By.xpath("//div[@role='option' and contains(@id, 'option-delete')] " +
            "| //div[@role='option']//*[text()='Delete']");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_SUIT_DELETE = "//span[contains(text(), 'Suite was successfully deleted.')]";
    public static final String NOTIFICATION_FOR_SUCCESSFUL_CASE_CREATION = "//span[contains(text(), 'Test case was created successfully!')]";
    public static final By DELETE_TEST_CASE_BUTTON = By.cssSelector("button[aria-label='Delete']");
    public static final By DELETE_TEST_CASE_TITLE = By.cssSelector("#modals h2");
    public static final By CONFIRM_DELETE_INPUT = By.cssSelector("input[name='confirm']");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_DELETE_TEST_CASE = "//span[contains(text(), 'Deletion of 1 test case started')]";
    public static final String CLOSE_SIDEBAR_MENU_BUTTON = "button[aria-label='Close']";
    public static final By DELETE_TEST_CASE_BUTTON_ON_POP_OP_AFTER_CONFIRM = By.cssSelector("#modals button[type='submit']");
    public static final By QUICK_TEST_CASE_TITLE_INPUT = By.cssSelector("input[placeholder='Test case title']");
    public static final By PROJECT_ACTIONS_BUTTON = By.cssSelector("button[aria-haspopup='true'][id*='actions']");
    private static final By LOADING_SPINNER = By.xpath("//div[text()='Loading...']");
    private static final By CREATE_NEW_SUIT_BUTTON = com.codeborne.selenide.Selectors.withText("Create new suite");
    private static final String SUITE_IN_LIST_XPATH_TEMPLATE = "//a[contains(@class, 'suite')]//span[text()='%s'] | //span[text()='%s']";
    private static final String SUITE_ACTIONS_MENU_XPATH_TEMPLATE = "//button[@aria-label='suite %s actions']";

    public SelenideElement getProjectTitleElement() {
        log.debug("Getting project title element");
        return $(REPOSITORY_TITLE);
    }

    @Step("Нажать кнопку 'Create new suite'")
    public ProjectPage clickCreateNewSuit() {
        log.info("Clicking the 'Create new suite' button");
        $(CREATE_NEW_SUIT_BUTTON).shouldBe(clickable).click();
        return this;
    }

    public SelenideElement getPopUpTitleElement() {
        log.debug("Getting suite pop-up title element");
        return $(CREATE_SUITE_ON_POP_UP_TITLE);
    }

    @Step("Заполнить форму тест-сюиты. Название: '{name}', Описание: '{description}'")
    public ProjectPage fillSuiteForm(String name, String description, String preConditions) {
        log.info("Filling Suite form. Name: '{}', Description: '{}'", name, description);
        suiteNameInput.write(name);
        descriptionField.write(description);
        preConditionsField.write(preConditions);
        return this;
    }

    @Step("Нажать кнопку 'Create' в модальном окне тест-сюиты")
    public ProjectPage clickCreateButton() {
        log.info("Clicking 'Create' button on suite pop-up");
        $(byText(CREATE_BUTTON_ON_CREATE_SUIT_POP_UP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessSuitCreate() {
        log.debug("Getting success notification element for suite creation");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_SUIT_CREATE);
    }

    @Step("Нажать кнопку 'Cancel' в модальном окне тест-сюиты")
    public ProjectPage clickCancelButtonOnCreatePopUpSuit() {
        log.info("Clicking 'Cancel' button on suite pop-up");
        $(byText(CANCEL_BUTTON_ON_CREATE_NEW_SUIT_POP_UP)).click();
        return this;
    }

    @Step("Проверить, что кнопка 'Create new suite' отображается на странице")
    public ProjectPage verifyCreateNewSuiteButtonIsVisible() {
        log.debug("Verifying that 'Create new suite' button is visible");
        $(CREATE_NEW_SUIT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    @Step("Кликнуть по тест-сюите '{suiteName}' в списке репозитория")
    public ProjectPage clickOnSuiteInList(String suiteName) {
        log.info("Clicking on suite: '{}' in the list", suiteName);
        String formattedXpath = String.format(SUITE_IN_LIST_XPATH_TEMPLATE, suiteName, suiteName);
        $(By.xpath(formattedXpath)).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Удалить тест-сюиту: '{suiteName}'")
    public ProjectPage deleteSuit(String suiteName) {
        log.info("Deleting suite: '{}'", suiteName);
        String formattedXpath = String.format(SUITE_ACTIONS_MENU_XPATH_TEMPLATE, suiteName);
        By ellipsMenuLocator = By.xpath(formattedXpath);
        log.debug("Opening actions menu for suite '{}'", suiteName);
        $(ellipsMenuLocator).click();
        log.debug("Clicking suite delete option");
        $(DELETE_SUITE_BUTTON).click();
        log.info("Confirming suite deletion");
        $(byText(DELETE_BUTTON_ON_DELETE_CONFIRMATION_POP_UP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessSuitDelete() {
        log.debug("Getting success notification element for suite deletion");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_SUIT_DELETE);
    }

    @Step("Нажать кнопку создания тест-кейса вручную ('Create case')")
    public ProjectPage clickManualTestButton() {
        log.info("Clicking 'Create case' (manual test) button");
        $(byText(MANUAL_TEST_BUTTON)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessCaseCreation() {
        log.debug("Getting success notification element for test case creation");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_CASE_CREATION);
    }

    @Step("Закрыть боковое меню")
    public ProjectPage clickCloseSideBarMenuButton() {
        log.info("Closing the sidebar menu");
        $(CLOSE_SIDEBAR_MENU_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Нажать кнопку 'Select all' для выбора тест-кейсов")
    public ProjectPage clickSelectAllButton() {
        log.info("Clicking 'Select all' button to select test cases");
        $(byText(SELECT_ALL_BUTTON_ON_PROJECT_PAGE)).click();
        return this;
    }

    @Step("Нажать кнопку удаления ('Delete') для выбранных тест-кейсов")
    public ProjectPage clickDeleteTestCaseButton() {
        log.info("Clicking general 'Delete' button for test cases");
        $(DELETE_TEST_CASE_BUTTON).click();
        return this;
    }

    @Step("Ввести текст подтверждения удаления: '{wordForConfirmation}'")
    public ProjectPage enterConfirmIntoField(String wordForConfirmation) {
        log.info("Entering confirmation word: '{}' into delete confirmation field", wordForConfirmation);
        $(CONFIRM_DELETE_INPUT).setValue(wordForConfirmation);
        return this;
    }

    @Step("Подтвердить удаление тест-кейса в модальном окне")
    public ProjectPage clickDeleteButtonAfterConfirmOnPopUp() {
        log.info("Clicking 'Delete' button in the confirmation pop-up to trigger case deletion");
        $(DELETE_TEST_CASE_BUTTON_ON_POP_OP_AFTER_CONFIRM).click();
        return this;
    }

    public SelenideElement getGetDeleteTestCaseTitle() {
        log.debug("Getting delete test case pop-up title element");
        return $(DELETE_TEST_CASE_TITLE);
    }

    public SelenideElement getNotificationForSuccessDeleteTestCase() {
        log.debug("Waiting for success notification of test case deletion to be visible");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_DELETE_TEST_CASE).shouldBe(visible);
    }

    @Step("Создать быстрый тест-кейс с названием: '{value}'")
    public ProjectPage createQuickTest(String value) {
        log.info("Creating a quick test case with title: '{}'", value);
        $(byText(QUICK_TEST_BUTTON)).click();
        $(QUICK_TEST_CASE_TITLE_INPUT).setValue(value).pressEnter();
        return this;
    }

    @Step("Перейти в корзину проекта (Trash Bin)")
    public ProjectPage goToTrashBin() {
        log.info("Navigating to Project Trash Bin");
        $(PROJECT_ACTIONS_BUTTON).click();
        $(byText(TRASH_BIN_BUTTON)).click();
        return this;
    }

    public SelenideElement getSelectAllButton() {
        log.debug("Getting select all button element");
        return $(byText(SELECT_ALL_BUTTON_ON_PROJECT_PAGE));
    }

    @Override
    @Step("Проверить, что страница проекта (Repository) полностью загружена")
    public ProjectPage isPageOpened() {
        log.debug("Waiting for loading spinner to disappear");
        $(LOADING_SPINNER).shouldBe(Condition.hidden);
        log.debug("Checking if 'Import' button is visible to confirm Project Page is loaded");
        $(byText("Import")).shouldBe(Condition.visible);
        log.info("Project Page is fully loaded and ready for interaction");
        return this;
    }
}
