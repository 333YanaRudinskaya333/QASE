import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static pages.ProjectPage.REPOSITORY_TITLE;

public class ProjectSortingTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";

    @Test(groups = "with-project")
    public void sortZAProjects() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectsPage.createProject().isPageOpened();
        $(REPOSITORY_TITLE).shouldHave(Condition.text("222tt"));
        projectsPage.openPage()
                .clickSortByProjectsNameButton()
                .getProjectNames().shouldHave(CollectionCondition.exactTexts(" Sharelane", " 222tt"));
    }

    @Test(groups = "with-project")
    public void sortAZProjects() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectsPage.createProject().isPageOpened();
        $(REPOSITORY_TITLE).shouldHave(Condition.text("Saarelane"));
        projectsPage.openPage()
                .clickSortByProjectsNameButton()
                .clickSortByProjectsNameButton()
                .getProjectNames().shouldHave(CollectionCondition.exactTexts(" 222tt", " Sharelane"));
    }

    @Test
    public void changeRowsCountPerPage() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectsPage.isPageOpened()
                .selectRowsPerPage(50)
                .getRowsPerPageDropdown().shouldHave(Condition.text("50"));
    }

    @AfterMethod(onlyForGroups = "with-project")
    public void tearDown() {
        System.out.println("LOG: Запуск очистки данных после теста...");
        projectsPage.openPage()
                .deleteProject(PROJECT_NAME);
    }
}
