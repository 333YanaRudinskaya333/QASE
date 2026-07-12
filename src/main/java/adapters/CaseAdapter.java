package adapters;

import io.restassured.mapper.ObjectMapperType;
import lombok.extern.log4j.Log4j2;
import models.cases.CaseRq;
import models.cases.CaseRs;
import models.cases.SingleCaseRs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class CaseAdapter extends BaseAdapter{

    public static CaseRs createCase(String code, CaseRq rq) {
        log.info("API: Запрос на создание нового тест-кейса в проекте [{}]", code);
        return given()
                .spec(spec)
                .body(rq, ObjectMapperType.GSON)
                .log().all()
                .when()
                .post("/case/" + code)
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_case_schema.json"))
                .spec(ok200)
                .extract()
                .as(CaseRs.class);
    }

    public static void deleteCase(String code, int id) {
        log.info("API: Удаление тест-кейса ID: [{}] из проекта [{}]", id, code);
        given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .log().all()
                .when()
                .delete("/case/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }

    public static CaseRs updateCase(String code, int id, Object rq) {
        log.info("API: Обновление (PATCH) тест-кейса ID: [{}] в проекте [{}]", id, code);
        return given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .body(rq, ObjectMapperType.GSON)
                .log().all()
                .when()
                .patch("/case/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(CaseRs.class);
    }

    public static SingleCaseRs getCase(String code, int id) {
        log.info("API: Запрос информации о тест-кейсе ID: [{}] из проекта [{}]", id, code);
        return given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .log().all()
                .when()
                .get("/case/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .as(SingleCaseRs.class);
    }
}
