package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import wrappers.Input;
import wrappers.RichTextEditor;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.*;

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
    public static final String NOTIFICATION_FOR_SUCCESSFUL_DELETE_TEST_CASE ="//span[contains(text(), 'Deletion of 1 test case started')]";
    public static final String CLOSE_SIDEBAR_MENU_BUTTON = "button[aria-label='Close']";
    public static final By DELETE_TEST_CASE_BUTTON_ON_POP_OP_AFTER_CONFIRM = By.cssSelector("#modals button[type='submit']");
    public static final By QUICK_TEST_CASE_TITLE_INPUT = By.cssSelector("input[placeholder='Test case title']");
    public static final By PROJECT_ACTIONS_BUTTON = By.cssSelector("button[aria-haspopup='true'][id*='actions']");
    private static final By LOADING_SPINNER = By.xpath("//div[text()='Loading...']");
    private static final By CREATE_NEW_SUIT_BUTTON = com.codeborne.selenide.Selectors.withText("Create new suite");

    public SelenideElement getProjectTitleElement() {
        return $(REPOSITORY_TITLE);
    }

    public ProjectPage clickCreateNewSuit() {
        $(CREATE_NEW_SUIT_BUTTON).shouldBe(clickable).click();
        return this;
    }
    public SelenideElement getPopUpTitleElement() {
        return $(CREATE_SUITE_ON_POP_UP_TITLE);
    }

    public ProjectPage fillSuiteForm(String name, String description, String preConditions) {
        suiteNameInput.write(name);
        descriptionField.write(description);
        preConditionsField.write(preConditions);
        return this;
    }

    public ProjectPage clickCreateButton() {
        $(byText(CREATE_BUTTON_ON_CREATE_SUIT_POP_UP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessSuitCreate() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_SUIT_CREATE);
    }

    public ProjectPage clickCancelButtonOnCreatePopUpSuit() {
        $(byText(CANCEL_BUTTON_ON_CREATE_NEW_SUIT_POP_UP)).click();
        return this;
    }

    public ProjectPage verifyCreateNewSuiteButtonIsVisible() {
        $(CREATE_NEW_SUIT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public ProjectPage clickOnSuiteInList(String suiteName) {
        By suiteLocator = By.xpath("//a[contains(@class, 'suite')]//span[text()='" + suiteName + "'] " +
                "| //span[text()='" + suiteName + "']");
        $(suiteLocator).shouldBe(Condition.visible).click();
        return this;
    }

    public ProjectPage deleteSuit(String suiteName) {
        By ellipssMenuLocator = By.xpath("//button[@aria-label='suite " + suiteName + " actions']");
        $(ellipssMenuLocator).click();
        $(DELETE_SUITE_BUTTON).click();
        $(byText(DELETE_BUTTON_ON_DELETE_CONFIRMATION_POP_UP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessSuitDelete() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_SUIT_DELETE);
    }

    public ProjectPage clickManualTestButton() {
        $(byText(MANUAL_TEST_BUTTON)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessCaseCreation() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_CASE_CREATION);
    }

    public ProjectPage clickCloseSideBarMenuButton() {
        $(CLOSE_SIDEBAR_MENU_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }

    public ProjectPage clickSelectAllButton() {
        $(byText(SELECT_ALL_BUTTON_ON_PROJECT_PAGE)).click();
        return this;
    }

    public ProjectPage clickDeleteTestCaseButton() {
        $(DELETE_TEST_CASE_BUTTON).click();
        return this;
    }

    public ProjectPage enterConfirmIntoField(String wordForConfirmation) {
        $(CONFIRM_DELETE_INPUT).setValue(wordForConfirmation);
        return this;
    }

    public ProjectPage clickDeleteButtonAfterConfirmOnPopUp() {
        $(DELETE_TEST_CASE_BUTTON_ON_POP_OP_AFTER_CONFIRM).click();
        return this;
    }

    public SelenideElement getGetDeleteTestCaseTitle() {
        return $(DELETE_TEST_CASE_TITLE);
    }

    public SelenideElement getNotificationForSuccessDeleteTestCase() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_DELETE_TEST_CASE).shouldBe(visible);
    }

    public ProjectPage createQuickTest(String value) {
        $(byText(QUICK_TEST_BUTTON)).click();
        $(QUICK_TEST_CASE_TITLE_INPUT).setValue(value).pressEnter();
        return this;
    }

    public ProjectPage goToTrashBin() {
        $(PROJECT_ACTIONS_BUTTON).click();
        $(byText(TRASH_BIN_BUTTON)).click();
        return this;
    }

    public SelenideElement getSelectAllButton() {
        return $(byText(SELECT_ALL_BUTTON_ON_PROJECT_PAGE));
    }

    @Override
    public ProjectPage isPageOpened() {
        $(LOADING_SPINNER).shouldBe(Condition.hidden);
        $(byText("Import")).shouldBe(Condition.visible);
        return this;
    }
}
