package adapters;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.project.ProjectRq;
import models.project.ProjectRs;

import static adapters.BaseAdapter.ok200;
import static adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class ProjectAdapter extends BaseAdapter{

    public static ProjectRs createProject(ProjectRq rq) {
        log.info("API: Отправка запроса на создание проекта");
        return given()
                .spec(spec)
                .body(rq)
                .log().all()
                .when()
                .post("/project")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_project_schema.json"))
                .spec(ok200)
                .extract()
                .as(ProjectRs.class);
    }

    public static void deleteProject(String code) {
        log.info("API: Отправка запроса на удаление проекта с кодом [{}]", code);
        given()
                .spec(spec)
                .pathParam("code", code)
                .log().all()
                .when()
                .delete("/project/{code}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
