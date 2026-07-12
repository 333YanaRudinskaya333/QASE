package tests.ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class SuiteTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";
    private final String PROJECT_CODE = "111dd";
    private final String SUIT_NAME = "TestName";

    @Test(groups = "with-project", testName = "Успешное создание нового тест-сьюта")
    public void createSuite() {
        projectStep.loginAndCreateTestProject("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE);
        projectPage.clickCreateNewSuit()
                .getPopUpTitleElement().shouldHave(Condition.text("Create suite"));
        projectPage.fillSuiteForm(SUIT_NAME, "TestDescription", "TestPreCondition")
                .clickCreateButton()
                .getNotificationForSuccessSuitCreate().shouldHave(Condition.text("Suite was successfully created."));
    }

    @Test(groups = "with-project", testName = "Отмена создания тест-сьюта")
    public void cancelCreateSuite() {
        projectStep.loginAndCreateTestProject("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE);
        projectPage.clickCreateNewSuit()
                .getPopUpTitleElement().shouldHave(Condition.text("Create suite"));
        projectPage.fillSuiteForm(SUIT_NAME, "TestDescription", "TestPreCondition")
                .clickCancelButtonOnCreatePopUpSuit()
                .verifyCreateNewSuiteButtonIsVisible();
    }

    @Test(groups = "with-project", testName = "Удаление тест-сьюта из репозитория")
    public void deleteSuite() {
        suiteStep.loginCreateProjectAndSuite("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.deleteSuit(SUIT_NAME)
                .getNotificationForSuccessSuitDelete().shouldHave(Condition.text("Suite was successfully deleted."));
    }

    @AfterMethod(onlyForGroups = "with-project")
    public void tearDown() {
        projectStep.deleteProjectIfExists(PROJECT_NAME);
    }
}
