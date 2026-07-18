package api.adapters;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import api.models.project.ProjectRq;
import api.models.project.ProjectRs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class ProjectAdapter extends BaseAdapter{

    @Step("API: Создать новый проект")
    public static ProjectRs createProject(ProjectRq rq) {
        log.info("API: Отправка запроса на создание проекта");
        return given()
                .spec(spec)
                .body(rq)
                .when()
                .post("/project")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/create_project_schema.json"))
                .spec(ok200)
                .extract()
                .as(ProjectRs.class);
    }

    @Step("API: Удалить проект с кодом '{code}'")
    public static void deleteProject(String code) {
        log.info("API: Отправка запроса на удаление проекта с кодом [{}]", code);
        given()
                .spec(spec)
                .pathParam("code", code)
                .when()
                .delete("/project/{code}")
                .then()
                .spec(ok200);
    }
}
