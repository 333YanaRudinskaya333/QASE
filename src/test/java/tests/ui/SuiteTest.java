package tests.ui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class SuiteTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";
    private final String PROJECT_CODE = "111dd";
    private final String SUIT_NAME = "TestName";

    @Test(groups = "with-project", testName = "Успешное создание нового тест-сьюта")
    @Owner("Rudinskaya Y.V.")
    @Feature("Suit")
    @Severity(SeverityLevel.CRITICAL)
    public void createSuite() {
        projectStep.loginAndCreateTestProject(user, password, PROJECT_NAME, PROJECT_CODE);
        projectPage.clickCreateNewSuit()
                .getPopUpTitleElement().shouldHave(Condition.text("Create suite"));
        projectPage.fillSuiteForm(SUIT_NAME, "TestDescription", "TestPreCondition")
                .clickCreateButton()
                .getNotificationForSuccessSuitCreate().shouldHave(Condition.text("Suite was successfully created."));
    }

    @Test(groups = "with-project", testName = "Отмена создания тест-сьюта")
    @Owner("Rudinskaya Y.V.")
    @Feature("Suit")
    @Severity(SeverityLevel.MINOR)
    public void cancelCreateSuite() {
        projectStep.loginAndCreateTestProject(user, password, PROJECT_NAME, PROJECT_CODE);
        projectPage.clickCreateNewSuit()
                .getPopUpTitleElement().shouldHave(Condition.text("Create suite"));
        projectPage.fillSuiteForm(SUIT_NAME, "TestDescription", "TestPreCondition")
                .clickCancelButtonOnCreatePopUpSuit()
                .verifyCreateNewSuiteButtonIsVisible();
    }

    @Test(groups = "with-project", testName = "Удаление тест-сьюта из репозитория")
    @Owner("Rudinskaya Y.V.")
    @Feature("Suit")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteSuite() {
        suiteStep.loginCreateProjectAndSuite(user, password, PROJECT_NAME, PROJECT_CODE, SUIT_NAME);
        projectPage.deleteSuit(SUIT_NAME)
                .getNotificationForSuccessSuitDelete().shouldHave(Condition.text("Suite was successfully deleted."));
    }

    @AfterMethod(onlyForGroups = "with-project")
    @Owner("Rudinskaya Y.V.")
    @Feature("Delete project")
    @Severity(SeverityLevel.CRITICAL)
    public void tearDown() {
        projectStep.deleteProjectIfExists(PROJECT_NAME);
    }
}
