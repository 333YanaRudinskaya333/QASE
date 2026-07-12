package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.*;

public class TestPlansPage extends BasePage {

    public static final By TEST_PLANS_TITLE = By.cssSelector("h1");
    public static final By CREATE_PLAN_BUTTON = By.cssSelector("#createButton");
    public static final String CONFIRM_DELETE_TEST_PLAN_TITLE = "//h2[text()='Delete test plan']";
    public static final String CONFIRM_CLONE_TEST_PLAN_TITLE = "//h3[text()='Clone test plan']";
    public static final String NOTIFICATION_FOR_SUCCESSFUL_CLON_TEST_PLAN = ("//span[contains(text(), 'Test plan was cloned successfully!')]");


    public TestPlansPage openPage(String projectCode) {
        Selenide.open("/plan/" + projectCode.toUpperCase(Locale.ROOT));
        return this;
    }

    public TestPlansPage clickCreateTestPlanButton() {
        $(CREATE_PLAN_BUTTON).click();
        return this;
    }

    public TestPlansPage clickCheckboxForTestPlan(String planName) {
        By planCheckbox = By.xpath("//td[descendant::*[text()='" + planName + "']]/preceding-sibling::td//input/.. | //tr[descendant::*[text()='" + planName + "']]/td[1]//span");
        $(planCheckbox).click();
        return this;
    }

    public TestPlansPage clickActionsMenuForTestPlan(String planName) {
        By planMenu = By.xpath("//tr[contains(., '" + planName + "')]//button[@aria-label='Open action menu']");
        $(planMenu).click();
        return this;
    }

    public TestPlansPage clickDeleteForSingleTestPlan() {
        $(byText(DELETE_BUTTON_FOR_SINGLE_TEST_PLAN)).click();
        return this;
    }

    public SelenideElement getTitleOnConfirmDeletePopUp() {
        return $x(CONFIRM_DELETE_TEST_PLAN_TITLE).shouldBe(visible);
    }

    public TestPlansPage clickDeletePlanButtonOnPoUp() {
        $(byText(DELETE_PLAN_BUTTON_ON_POPUP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessfulDeletedPlan(String planName) {
        String notificationForSuccessfulPlanDeleting = String.format("//span[contains(text(), 'Test plan %s was deleted successfully!')]", planName);
        return $x(notificationForSuccessfulPlanDeleting).shouldBe(visible);
    }

    public TestPlansPage clickCloneButtonOnActionMenu() {
        $(byText(CLONE_BUTTON_FOR_SINGLE_TEST_PLAN)).click();
        return this;
    }

    public SelenideElement getTitleOnConfirmClonePopUp() {
        return $x(CONFIRM_CLONE_TEST_PLAN_TITLE).shouldBe(visible);
    }

    public TestPlansPage clickCloneButtonOnPopUp() {
        $(byText(CLONE_BUTTON_ON_POP_UP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessfulClone() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_CLON_TEST_PLAN);
    }

    @Override
    public TestPlansPage isPageOpened() {
        $(TEST_PLANS_TITLE).shouldBe(Condition.visible);
        return this;
    }
}
