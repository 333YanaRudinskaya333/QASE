package tests.ui;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestCaseTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";
    private final String PROJECT_CODE = "111dd";
    private final String SUIT_NAME = "TestName";

    TestCase testCase = TestCase.builder()
            .title("Авторизация с валидными данными")
            .status("Draft")
            .suite(SUIT_NAME)
            .severity("Major")
            .priority("High")
            .type("Functional")
            .layer("E2E")
            .isFlaky("No")
            .milestone("Not set")
            .behavior("Positive")
            .automation("Automated")
            .description("Проверка базовой авторизации")
            .preConditions("Предусловие: открыта форма")
            .postConditions("Постусловие: сессия закрыта")
            .isMuted(false)
            .build();


    @Test(groups = "with-project", testName = "Создание полноценного тест-кейса через форму")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test case")
    @Severity(SeverityLevel.CRITICAL)
    public void createTestCase() {
        suiteStep.loginCreateProjectAndSuite(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.clickManualTestButton();
        createTestCasePage.isPageOpened()
                .fillTestCaseForm(testCase)
                .addParameter(1, "UserRole", "Admin")
                .clickSaveButton();
        projectPage.getNotificationForSuccessCaseCreation().shouldHave(Condition.text("Test case was created successfully!"));
    }

    @Test(groups = "with-project", testName = "Массовое удаление всех существующих тест-кейсов")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test case")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteAllTestCases() {
        suiteStep.loginCreateProjectAndSuite(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.clickManualTestButton();
        createTestCasePage.isPageOpened()
                .fillTestCaseForm(testCase)
                .addParameter(1, "UserRole", "Admin")
                .clickSaveButton();
        projectPage.getNotificationForSuccessCaseCreation().shouldHave(Condition.text("Test case was created successfully!"));
        projectPage.clickCloseSideBarMenuButton()
                .clickSelectAllButton()
                .clickDeleteTestCaseButton()
                .getGetDeleteTestCaseTitle().shouldHave(Condition.text("Delete test case"));
        projectPage.enterConfirmIntoField("CONFIRM")
                .clickDeleteButtonAfterConfirmOnPopUp();
        projectPage.getNotificationForSuccessDeleteTestCase().shouldHave(Condition.text("Deletion of 1 test case started"));
    }

    @Test(groups = "with-project", testName = "Создание быстрого тест-кейса внутри сьюта")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test case")
    @Severity(SeverityLevel.CRITICAL)
    public void createQuickTestCase() {
        suiteStep.loginCreateProjectSuiteAndSelectIt(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.createQuickTest("qqq123")
                .getSelectAllButton().shouldBe(Condition.hidden);
    }

    @Test(groups = "with-project", testName = "Восстановление удаленного тест-кейса из корзины")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test case")
    @Severity(SeverityLevel.CRITICAL)
    public void restoreTestCase() {
        suiteStep.loginCreateProjectSuiteAndSelectIt(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.createQuickTest("qqq123")
                .getSelectAllButton().shouldBe(Condition.hidden);
        projectPage.clickSelectAllButton()
                .clickDeleteTestCaseButton()
                .getGetDeleteTestCaseTitle().shouldHave(Condition.text("Delete test case"));
        projectPage.enterConfirmIntoField("CONFIRM")
                .clickDeleteButtonAfterConfirmOnPopUp();
        projectPage.getNotificationForSuccessDeleteTestCase().shouldHave(Condition.text("Deletion of 1 test case started"));
        projectPage.goToTrashBin();
        trashBinPage.restoreAllTestCasesFromTrashBin()
                .getNotificationForSuccessRestore().shouldHave(Condition.text("Items restored successfully"));
    }

    @Test(testName = "Отмена создания проекта")
    @Owner("Rudinskaya Y.V.")
    @Feature("Test case")
    @Severity(SeverityLevel.MINOR)
    public void cancelCreateProject() {
        loginStep.loginWithCredentials(user, password);
        projectsPage.cancelCreateProject()
                .getProjectTitle().shouldHave(Condition.text("Projects"));
    }

    @AfterMethod(onlyForGroups = "with-project")
    @Owner("Rudinskaya Y.V.")
    @Feature("Delete project")
    @Severity(SeverityLevel.CRITICAL)
    public void tearDown() {
        projectStep.deleteProjectIfExists(PROJECT_NAME);
    }
}
