package tests.ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestCaseTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";
    private final String PROJECT_CODE = "111dd";
    private final String SUIT_NAME = "TestName";

    @Test(groups = "with-project", testName = "Создание полноценного тест-кейса через форму")
    public void createTestCase() {
        suiteStep.loginCreateProjectAndSuite("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.clickManualTestButton();
        createTestCasePage.isPageOpened()
                .fillTestCaseForm(
                        "Авторизация с валидными данными",
                        "Draft",
                        SUIT_NAME,
                        "Major",
                        "High",
                        "Functional",
                        "E2E",
                        "No",
                        "Not set",
                        "Positive",
                        "Automated",
                        "Проверка базовой авторизации",
                        "Предусловие: открыта форма",
                        "Постусловие: сессия закрыта",
                        false
                )
                .addParameter(1, "UserRole", "Admin")
                .clickSaveButton();
        projectPage.getNotificationForSuccessCaseCreation().shouldHave(Condition.text("Test case was created successfully!"));
    }

    @Test(groups = "with-project", testName = "Массовое удаление всех существующих тест-кейсов")
    public void deleteAllTestCases() {
        suiteStep.loginCreateProjectAndSuite("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.clickManualTestButton();
        createTestCasePage.isPageOpened()
                .fillTestCaseForm(
                        "Авторизация с валидными данными",
                        "Draft",
                        SUIT_NAME,
                        "Major",
                        "High",
                        "Functional",
                        "E2E",
                        "No",
                        "Not set",
                        "Positive",
                        "Automated",
                        "Проверка базовой авторизации",
                        "Предусловие: открыта форма",
                        "Постусловие: сессия закрыта",
                        false
                )
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
    public void createQuickTestCase() {
        suiteStep.loginCreateProjectSuiteAndSelectIt("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.createQuickTest("qqq123")
                .getSelectAllButton().shouldBe(Condition.hidden);
    }

    @Test(groups = "with-project", testName = "Восстановление удаленного тест-кейса из корзины")
    public void restoreTestCase() {
        suiteStep.loginCreateProjectSuiteAndSelectIt("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
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
