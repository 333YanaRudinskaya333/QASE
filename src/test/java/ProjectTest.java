import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static pages.ProjectPage.*;

public class ProjectTest extends BaseTest {

    private final String PROJECT_NAME = "222tt";

    @Test
    public void checkCreateProject() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectsPage.createProject().isPageOpened();
        $(REPOSITORY_TITLE).shouldHave(Condition.text("222tt"));
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("LOG: Запуск очистки данных после теста...");
        projectsPage.openPage()
                .deleteProject(PROJECT_NAME);
    }
}