package tests.ui;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class ProjectSortingTest extends BaseTest {
    private final String PROJECT_NAME = "222tt";
    private final String PROJECT_CODE = "111dd";

    @Test(groups = "with-project", testName = "Сортировка проектов по имени от Z до A")
    public void sortZAProjects() {
        projectStep.loginCreateProjectAndReturnToProjectsPage("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE);
        projectsPage.clickSortByProjectsNameButton()
                .getProjectNames().shouldHave(CollectionCondition.exactTexts(" Sharelane", " 222tt"));
    }

    @Test(groups = "with-project", testName = "Сортировка проектов по имени от A до Z")
    public void sortAZProjects() {
        projectStep.loginCreateProjectAndReturnToProjectsPage("rudinskaya.yana@gmail.com", "TeSt123Qq===", PROJECT_NAME, PROJECT_CODE);
        projectsPage.clickSortByProjectsNameButton()
                .clickSortByProjectsNameButton()
                .getProjectNames().shouldHave(CollectionCondition.exactTexts(" 222tt", " Sharelane"));
    }

    @Test(testName = "Изменение количества отображаемых строк на страницу")
    public void changeRowsCountPerPage() {
        loginStep.loginWithCredentials("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectsPage.isPageOpened()
                .selectRowsPerPage(50)
                .getRowsPerPageDropdown().shouldHave(Condition.text("50"));
    }

    @AfterMethod(onlyForGroups = "with-project")
    public void tearDown() {
        projectStep.deleteProjectIfExists(PROJECT_NAME);
    }
}
