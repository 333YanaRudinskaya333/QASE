package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import wrappers.Input;
import wrappers.RichTextEditor;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.*;

public class CreateTestPlanPage extends BasePage{
    public static final By CREATE_TEST_PLAN_TITLE = By.cssSelector("h1");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_TEST_PLAN_CREATINO = ("//span[contains(text(), 'Test plan was created successfully!')]");

    private final Input titleField = new Input("Title");
    private final RichTextEditor descriptionField = new RichTextEditor("Description");

    public CreateTestPlanPage fillTitle(String title) {
        titleField.write(title);
        return this;
    }

    public CreateTestPlanPage fillDescription(String description) {
        descriptionField.write(description);
        return this;
    }

    public CreateTestPlanPage clickAddCasesButton() {
        $(byText(ADD_CASES_BUTTON)).click();
        return this;
    }

    public CreateTestPlanPage clickSelectAllButton() {
        $(byText(SELECT_ALL_BUTTON_ON_CREATE_TEST_PLAN_PAGE)).click();
        return this;
    }

    public CreateTestPlanPage clickDoneButton() {
        $(byText(DONE_BUTTON)).click();
        return this;
    }

    public TestPlansPage clickCreatePlanButton() {
        $(byText(CREATE_PLAN_BUTTON)).click();
        return new TestPlansPage();
    }

    public SelenideElement getNotificationForSuccessfulTestPlanCreation() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_TEST_PLAN_CREATINO);
    }

    @Override
    public CreateTestPlanPage isPageOpened() {
        $(CREATE_TEST_PLAN_TITLE).shouldBe(Condition.visible);
        return this;
    }
}
