package ui.steps;

import io.qameta.allure.Step;
import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import ui.pages.CreateTestPlanPage;
import ui.pages.TestPlansPage;

@Log4j2
public class TestPlanStep {

    private final TestPlansPage testPlanPage = new TestPlansPage();
    private final CreateTestPlanPage createTestPlanPage = new CreateTestPlanPage();

    @Step("Создать новый тест-план '{planName}' в проекте '{projectCode}'")
    public TestPlansPage createTestPlan(String projectCode, String planName, String description) {
        log.info("Создание тест-плана '{}' для репозитория '{}'", planName, projectCode);
        testPlanPage.openPage(projectCode)
                .clickCreateTestPlanButton();
        createTestPlanPage.isPageOpened();
        createTestPlanPage.fillTitle(planName)
                .fillDescription(description)
                .clickAddCasesButton()
                .clickSelectAllButton()
                .clickDoneButton()
                .clickCreatePlanButton();
        createTestPlanPage.getNotificationForSuccessfulTestPlanCreation()
                .shouldHave(Condition.text("Test plan was created successfully!"));
        return testPlanPage;
    }
}
