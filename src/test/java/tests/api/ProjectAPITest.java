package tests.api;

import api.adapters.ProjectAdapter;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import api.models.project.ProjectRq;
import api.models.project.ProjectRs;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.*;

public class ProjectAPITest {
    private final String CODE = "QA";

    @Test(testName = "API: Успешное создание нового проекта")
    @Owner("Rudinskaya Y.V.")
    @Feature("API Project")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateProject() {
        ProjectRq rq = ProjectRq.builder()
                .title("Project" + CODE)
                .code(CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();

        ProjectRs rs = ProjectAdapter.createProject(rq);
        assertTrue(rs.status);
        assertEquals(rs.result.code, CODE);
    }

    @AfterMethod(description = "Удаление проекта")
    @Owner("Rudinskaya Y.V.")
    @Feature("API Project")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteProject (){
        ProjectAdapter.deleteProject(CODE);
    }
}
