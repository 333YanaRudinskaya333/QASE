package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ui.dict.Elements.*;

@Log4j2
public class TestPlansPage extends BasePage {

    public static final By TEST_PLANS_TITLE = By.cssSelector("h1");
    public static final By CREATE_PLAN_BUTTON = By.cssSelector("#createButton");
    public static final String CONFIRM_DELETE_TEST_PLAN_TITLE = "//h2[text()='Delete test plan']";
    public static final String CONFIRM_CLONE_TEST_PLAN_TITLE = "//h3[text()='Clone test plan']";
    public static final String NOTIFICATION_FOR_SUCCESSFUL_CLONE_TEST_PLAN = ("//span[contains(text(), 'Test plan was cloned successfully!')]");
    private static final String TEST_PLAN_CHECKBOX_XPATH_TEMPLATE = "//td[descendant::*[text()='%1$s']]/preceding-sibling::td//input/.. | //tr[descendant::*[text()='%1$s']]/td[1]//span";
    private static final String TEST_PLAN_ACTIONS_MENU_XPATH_TEMPLATE = "//tr[contains(., '%s')]//button[@aria-label='Open action menu']";
    private static final String TEST_PLAN_DELETED_NOTIFICATION_XPATH_TEMPLATE = "//span[contains(text(), 'Test plan %s was deleted successfully!')]";

    @Step("Открыть страницу тест-планов для проекта: '{projectCode}'")
    public TestPlansPage openPage(String projectCode) {
        log.info("Opening Test Plans page for project: '{}'", projectCode);
        Selenide.open(TEST_PLANS_PAGE + projectCode.toUpperCase(Locale.ROOT));
        return this;
    }

    @Step("Нажать кнопку создания тест-плана ('Create')")
    public TestPlansPage clickCreateTestPlanButton() {
        log.info("Clicking the 'Create' test plan button");
        $(CREATE_PLAN_BUTTON).click();
        return this;
    }

    @Step("Выбрать чекбокс для тест-плана: '{planName}'")
    public TestPlansPage clickCheckboxForTestPlan(String planName) {
        log.info("Selecting checkbox for test plan: '{}'", planName);
        String formattedXpath = String.format(TEST_PLAN_CHECKBOX_XPATH_TEMPLATE, planName);
        $(By.xpath(formattedXpath)).click();
        return this;
    }

    @Step("Открыть меню действий для тест-плана: '{planName}'")
    public TestPlansPage clickActionsMenuForTestPlan(String planName) {
        log.info("Opening actions menu for test plan: '{}'", planName);
        String formattedXpath = String.format(TEST_PLAN_ACTIONS_MENU_XPATH_TEMPLATE, planName);
        $(By.xpath(formattedXpath)).click();
        return this;
    }

    @Step("Нажать опцию 'Delete' в меню действий")
    public TestPlansPage clickDeleteForSingleTestPlan() {
        log.info("Clicking 'Delete' option in the actions menu");
        $(byText(DELETE_BUTTON_FOR_SINGLE_TEST_PLAN)).click();
        return this;
    }

    public SelenideElement getTitleOnConfirmDeletePopUp() {
        log.debug("Getting title element on Delete Confirmation pop-up");
        return $x(CONFIRM_DELETE_TEST_PLAN_TITLE).shouldBe(visible);
    }

    @Step("Подтвердить удаление: нажать кнопку 'Delete plan' в модальном окне")
    public TestPlansPage clickDeletePlanButtonOnPoUp() {
        log.info("Confirming deletion: clicking 'Delete plan' button on pop-up");
        $(byText(DELETE_PLAN_BUTTON_ON_POPUP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessfulDeletedPlan(String planName) {
        log.debug("Waiting for success notification for deleted test plan: '{}'", planName);
        String formattedXpath = String.format(TEST_PLAN_DELETED_NOTIFICATION_XPATH_TEMPLATE, planName);
        return $x(formattedXpath).shouldBe(visible);
    }

    @Step("Нажать опцию 'Clone' в меню действий")
    public TestPlansPage clickCloneButtonOnActionMenu() {
        log.info("Clicking 'Clone' option in the actions menu");
        $(byText(CLONE_BUTTON_FOR_SINGLE_TEST_PLAN)).click();
        return this;
    }

    public SelenideElement getTitleOnConfirmClonePopUp() {
        log.debug("Getting title element on Clone Confirmation pop-up");
        return $x(CONFIRM_CLONE_TEST_PLAN_TITLE).shouldBe(visible);
    }

    @Step("Подтвердить клонирование: нажать кнопку 'Clone' в модальном окне")
    public TestPlansPage clickCloneButtonOnPopUp() {
        log.info("Confirming clone: clicking 'Clone' button on pop-up");
        $(byText(CLONE_BUTTON_ON_POP_UP)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessfulClone() {
        log.debug("Getting success notification element for cloned test plan");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_CLONE_TEST_PLAN);
    }

    @Override
    @Step("Проверить, что страница тест-планов успешно открыта")
    public TestPlansPage isPageOpened() {
        log.debug("Checking if Test Plans page is opened");
        $(TEST_PLANS_TITLE).shouldBe(Condition.visible);
        log.info("Test Plans page is successfully opened");
        return this;
    }
}
