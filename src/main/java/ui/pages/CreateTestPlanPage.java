package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import ui.wrappers.Input;
import ui.wrappers.RichTextEditor;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ui.dict.Elements.*;

@Log4j2
public class CreateTestPlanPage extends BasePage{
    public static final By CREATE_TEST_PLAN_TITLE = By.cssSelector("h1");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_TEST_PLAN_CREATINO = ("//span[contains(text(), 'Test plan was created successfully!')]");

    private final Input titleField = new Input("Title");
    private final RichTextEditor descriptionField = new RichTextEditor("Description");

    @Step("Ввести название тест-плана: '{title}'")
    public CreateTestPlanPage fillTitle(String title) {
        log.info("Entering test plan title: '{}'", title);
        titleField.write(title);
        return this;
    }

    @Step("Ввести описание тест-плана: '{description}'")
    public CreateTestPlanPage fillDescription(String description) {
        log.info("Entering test plan description: '{}'", description);
        descriptionField.write(description);
        return this;
    }

    @Step("Нажать кнопку 'Add cases'")
    public CreateTestPlanPage clickAddCasesButton() {
        log.info("Clicking the 'Add cases' button");
        $(byText(ADD_CASES_BUTTON)).click();
        return this;
    }

    @Step("Нажать кнопку 'Select all' для выбора всех тест-кейсов")
    public CreateTestPlanPage clickSelectAllButton() {
        log.info("Clicking the 'Select all' button to select all available test cases");
        $(byText(SELECT_ALL_BUTTON_ON_CREATE_TEST_PLAN_PAGE)).click();
        return this;
    }

    @Step("Нажать кнопку 'Done' для подтверждения выбора кейсов")
    public CreateTestPlanPage clickDoneButton() {
        log.info("Clicking 'Done' button to confirm case selection");
        $(byText(DONE_BUTTON)).click();
        return this;
    }

    @Step("Нажать кнопку 'Create plan' для отправки формы")
    public TestPlansPage clickCreatePlanButton() {
        log.info("Clicking 'Create plan' button to submit the form");
        $(byText(CREATE_PLAN_BUTTON)).click();
        return new TestPlansPage();
    }


    public SelenideElement getNotificationForSuccessfulTestPlanCreation() {
        log.debug("Getting the success notification element for test plan creation");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_TEST_PLAN_CREATINO);
    }

    @Override
    @Step("Проверить, что страница 'Create Test Plan' успешно открыта")
    public CreateTestPlanPage isPageOpened() {
        log.debug("Verifying if 'Create Test Plan' page is opened");
        $(CREATE_TEST_PLAN_TITLE).shouldBe(Condition.visible);
        log.info("'Create Test Plan' page is successfully opened and verified");
        return this;
    }
}
