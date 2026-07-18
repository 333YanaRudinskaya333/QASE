package tests.ui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static ui.pages.ProjectPage.REPOSITORY_TITLE;

public class ProjectTest extends BaseTest {

    private final String PROJECT_NAME = "222tt";
    private final String PROJECT_CODE = "111dd";

    @Test(groups = "with-project", testName = "Успешное создание нового проекта")
    @Owner("Rudinskaya Y.V.")
    @Feature("Project")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateProject() {
        loginStep.loginWithCredentials(user, password);
        projectStep.createNewProject(PROJECT_NAME, PROJECT_CODE);
        $(REPOSITORY_TITLE).shouldHave(Condition.text(PROJECT_NAME));
    }

    @Test(testName = "Отмена создания проекта через форму")
    @Owner("Rudinskaya Y.V.")
    @Feature("Project")
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
