package tests.ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestPlanTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";
    private final String SUIT_NAME = "TestName";
    private final String  PROJECT_CODE = "111dd";
    private final  String PLAN_NAME = "Titletest";

    @Test(groups = "with-project", testName = "Создание нового тест-плана с тест-кейсами")
    public void createTestPlan() {
        suiteStep.loginCreateProjectSuiteAndCase("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME, "qqq123");
        testPlanStep.createTestPlan(PROJECT_CODE, PLAN_NAME, "DescriptionTest");
    }

    @Test(groups = "with-project", testName = "Успешное удаление существующего тест-плана")
    public void deleteTestPlan() {
        suiteStep.loginCreateProjectSuiteAndCase("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME, "qqq123");
        testPlanStep.createTestPlan(PROJECT_CODE, PLAN_NAME, "DescriptionTest");
        testPlanPage.clickCheckboxForTestPlan(PLAN_NAME)
                .clickActionsMenuForTestPlan(PLAN_NAME)
                .clickDeleteForSingleTestPlan()
                .getTitleOnConfirmDeletePopUp().shouldHave(Condition.text("Delete test plan"));
        testPlanPage.clickDeletePlanButtonOnPoUp()
                .getNotificationForSuccessfulDeletedPlan(PLAN_NAME).shouldHave(Condition.text("Test plan " + PLAN_NAME + " was deleted successfully!"));

    }

    @Test(groups = "with-project", testName = "Клонирование тест-плана через меню действий")
    public void cloneTestPlan() {
        suiteStep.loginCreateProjectSuiteAndCase("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME, "qqq123");
        testPlanStep.createTestPlan(PROJECT_CODE, PLAN_NAME, "DescriptionTest");
        testPlanPage.clickCheckboxForTestPlan(PLAN_NAME)
                .clickActionsMenuForTestPlan(PLAN_NAME)
                .clickCloneButtonOnActionMenu()
                .getTitleOnConfirmClonePopUp().shouldHave(Condition.text("Clone test plan"));
        testPlanPage.clickCloneButtonOnPopUp()
                .getNotificationForSuccessfulClone().shouldHave(Condition.text("Test plan was cloned successfully!"));
    }

    @Test(testName = "Отмена создания проекта на странице проектов")
    public void cancelCreateProject() {
        loginStep.loginWithCredentials("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectsPage.cancelCreateProject()
                .getProjectTitle().shouldHave(Condition.text("Projects"));
    }

    @AfterMethod(onlyForGroups = "with-project")
    public void tearDown() {
        projectStep.deleteProjectIfExists(PROJECT_NAME);
    }
}
