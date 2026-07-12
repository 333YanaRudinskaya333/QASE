package tests.api;

import adapters.CaseAdapter;
import adapters.ProjectAdapter;
import models.cases.CaseRq;
import models.cases.CaseRs;
import models.cases.SingleCaseRs;
import models.project.ProjectRq;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;
import static org.testng.Assert.*;

public class CaseAPITest {
    private final String CODE = "QA";
    private int createdCaseId;

    @BeforeClass
    public void setUp() {
        ProjectRq projectRq = ProjectRq.builder()
                .title("Project " + CODE)
                .code(CODE)
                .description("Infrastructural project for case tests")
                .access("all")
                .build();

        ProjectAdapter.createProject(projectRq);
    }

    @Test(testName = "API: Успешное создание нового тест-кейса")
    public void checkCreateCase() {
        CaseRq rq = CaseRq.builder()
                .code(CODE)
                .description("qqq")
                .preconditions("sss")
                .postconditions("www")
                .title("Aaa")
                .severity(1)
                .priority(1)
                .behavior(1)
                .type(1)
                .layer(1)
                .is_flaky(1)
                .build();

        CaseRs rs = CaseAdapter.createCase(CODE, rq);
        assertTrue(rs.getStatus());
        this.createdCaseId = rs.getResult().getId();
    }

    @Test(
            dependsOnMethods = "checkCreateCase",
            testName = "API: Успешное обновление полей существующего тест-кейса"
    )
    public void checkUpdateCase() {
        CaseRq updateRq = CaseRq.builder()
                .title("NEW updated title")
                .description("NEW updated description")
                .preconditions("NEW updated preconditions")
                .postconditions("NEW updated postconditions")
                .build();
        CaseRs updateRs = CaseAdapter.updateCase(CODE, createdCaseId, updateRq);
        assertTrue(updateRs.getStatus());
    }

    @Test(
            dependsOnMethods = "checkCreateCase",
            testName = "API: Получение информации о конкретном тест-кейсе по ID"
    )
    public void checkGetSingleTestCase() {
        SingleCaseRs response = CaseAdapter.getCase(CODE, createdCaseId);
        assertTrue(response.getStatus(), "Статус ответа должен быть true");
        assertNotNull(response.getResult(), "Объект 'result' не должен быть null");
        assertEquals(response.getResult().getId(), Integer.valueOf(createdCaseId),"ID полученного кейса должен совпадать с созданным");
        assertEquals(response.getResult().getTitle(), "Aaa", "Title не совпадает");
        assertEquals(response.getResult().getSeverity(), Integer.valueOf(1), "Severity должен быть 1");
    }

    @AfterClass(alwaysRun = true)
    public void deleteCase() {
        if (createdCaseId != 0) {
            CaseAdapter.deleteCase(CODE, createdCaseId);
        }
        ProjectAdapter.deleteProject(CODE);
    }
}
