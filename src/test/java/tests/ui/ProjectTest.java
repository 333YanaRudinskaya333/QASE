package tests.ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static pages.ProjectPage.*;

public class ProjectTest extends BaseTest {

    private final String PROJECT_NAME = "222tt";
    private final String PROJECT_CODE = "111dd";

    @Test(groups = "with-project", testName = "Успешное создание нового проекта")
    public void checkCreateProject() {
        loginStep.loginWithCredentials("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectStep.createNewProject(PROJECT_NAME, PROJECT_CODE);
        $(REPOSITORY_TITLE).shouldHave(Condition.text(PROJECT_NAME));
    }

    @Test(testName = "Отмена создания проекта через форму")
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
