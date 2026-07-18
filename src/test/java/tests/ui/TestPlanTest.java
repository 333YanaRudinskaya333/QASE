package tests.ui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestPlanTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";
    private final String SUIT_NAME = "TestName";
    private final String  PROJECT_CODE = "111dd";
    private final  String PLAN_NAME = "Titletest";

    @Test(groups = "with-project", testName = "Создание нового тест-плана с тест-кейсами")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test plan")
    @Severity(SeverityLevel.CRITICAL)
    public void createTestPlan() {
        suiteStep.loginCreateProjectSuiteAndCase(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME, "qqq123");
        testPlanStep.createTestPlan(PROJECT_CODE, PLAN_NAME, "DescriptionTest");
    }

    @Test(groups = "with-project", testName = "Успешное удаление существующего тест-плана")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test plan")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteTestPlan() {
        suiteStep.loginCreateProjectSuiteAndCase(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME, "qqq123");
        testPlanStep.createTestPlan(PROJECT_CODE, PLAN_NAME, "DescriptionTest");
        testPlanPage.clickCheckboxForTestPlan(PLAN_NAME)
                .clickActionsMenuForTestPlan(PLAN_NAME)
                .clickDeleteForSingleTestPlan()
                .getTitleOnConfirmDeletePopUp().shouldHave(Condition.text("Delete test plan"));
        testPlanPage.clickDeletePlanButtonOnPoUp()
                .getNotificationForSuccessfulDeletedPlan(PLAN_NAME).shouldHave(Condition.text("Test plan " + PLAN_NAME + " was deleted successfully!"));

    }

    @Test(groups = "with-project", testName = "Клонирование тест-плана через меню действий")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test plan")
    @Severity(SeverityLevel.MINOR)
    public void cloneTestPlan() {
        suiteStep.loginCreateProjectSuiteAndCase(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME, "qqq123");
        testPlanStep.createTestPlan(PROJECT_CODE, PLAN_NAME, "DescriptionTest");
        testPlanPage.clickCheckboxForTestPlan(PLAN_NAME)
                .clickActionsMenuForTestPlan(PLAN_NAME)
                .clickCloneButtonOnActionMenu()
                .getTitleOnConfirmClonePopUp().shouldHave(Condition.text("Clone test plan"));
        testPlanPage.clickCloneButtonOnPopUp()
                .getNotificationForSuccessfulClone().shouldHave(Condition.text("Test plan was cloned successfully!"));
    }

    @AfterMethod(onlyForGroups = "with-project")
    @Owner("Rudinskaya Y.V.")
    @Feature("Delete project")
    @Severity(SeverityLevel.CRITICAL)
    public void tearDown() {
        projectStep.deleteProjectIfExists(PROJECT_NAME);
    }
}
