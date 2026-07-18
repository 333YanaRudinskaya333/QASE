package ui.steps;

import io.qameta.allure.Step;
import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import ui.pages.ProjectPage;

@Log4j2
public class SuiteStep {

    private final ProjectStep projectStep = new ProjectStep();
    private final ProjectPage projectPage = new ProjectPage();

    @Step("Предусловие: Создание проекта и тест-сьюта '{suiteName}'")
    public ProjectPage loginCreateProjectAndSuite(String user, String password, String projectName, String projectCode, String suiteName) {
        log.info("Предусловие: Инициализация проекта '{}' и добавление сьюта '{}'", projectName, suiteName);
        projectStep.loginAndCreateTestProject(user, password, projectName, projectCode);
        projectPage.clickCreateNewSuit()
                .getPopUpTitleElement().shouldHave(Condition.text("Create suite"));
        projectPage.fillSuiteForm(suiteName, "TestDescription", "TestPreCondition")
                .clickCreateButton()
                .getNotificationForSuccessSuitCreate().shouldHave(Condition.text("Suite was successfully created."));
        return projectPage;
    }

    @Step("Предусловие: Создание проекта, сьюта '{suiteName}' и выбор его в списке")
    public ProjectPage loginCreateProjectSuiteAndSelectIt(String user, String password, String projectName, String projectCode, String suiteName) {
        log.info("Предусловие: Создание структуры проекта и фокус на сьюте '{}'", suiteName);
        loginCreateProjectAndSuite(user, password, projectName, projectCode, suiteName);
        projectPage.clickOnSuiteInList(suiteName);
        return projectPage;
    }

    @Step("Предусловие: Создание проекта, сьюта '{suiteName}' и быстрого тест-кейса '{caseTitle}'")
    public ProjectPage loginCreateProjectSuiteAndCase(String user, String password, String projectName, String projectCode, String suiteName, String caseTitle) {
        log.info("Предусловие: Развертывание проекта, сьюта '{}' и создание быстрого тест-кейса '{}'", suiteName, caseTitle);
        projectStep.loginAndCreateTestProject(user, password, projectName, projectCode);
        projectPage.clickCreateNewSuit()
                .getPopUpTitleElement().shouldHave(Condition.text("Create suite"));
        projectPage.fillSuiteForm(suiteName, "TestDescription", "TestPreCondition")
                .clickCreateButton()
                .getNotificationForSuccessSuitCreate().shouldHave(Condition.text("Suite was successfully created."));
        projectPage.clickOnSuiteInList(suiteName)
                .createQuickTest(caseTitle)
                .getSelectAllButton().shouldBe(Condition.hidden);
        return projectPage;
    }
}
