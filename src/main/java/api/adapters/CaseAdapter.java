package api.adapters;

import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import lombok.extern.log4j.Log4j2;
import api.models.cases.CaseRq;
import api.models.cases.CaseRs;
import api.models.cases.SingleCaseRs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class CaseAdapter extends BaseAdapter{

    @Step("API: Создать новый тест-кейс в проекте '{code}'")
    public static CaseRs createCase(String code, CaseRq rq) {
        log.info("API: Запрос на создание нового тест-кейса в проекте [{}]", code);
        return given()
                .spec(spec)
                .body(rq, ObjectMapperType.GSON)
                .when()
                .post("/case/" + code)
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/create_case_schema.json"))
                .spec(ok200)
                .extract()
                .as(CaseRs.class);
    }

    @Step("API: Удалить тест-кейс ID: {id} из проекта '{code}'")
    public static void deleteCase(String code, int id) {
        log.info("API: Удаление тест-кейса ID: [{}] из проекта [{}]", id, code);
        given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .when()
                .delete("/case/{code}/{id}")
                .then()
                .spec(ok200);
    }

    @Step("API: Обновить тест-кейс ID: {id} в проекте '{code}'")
    public static CaseRs updateCase(String code, int id, Object rq) {
        log.info("API: Обновление (PATCH) тест-кейса ID: [{}] в проекте [{}]", id, code);
        return given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .body(rq, ObjectMapperType.GSON)
                .when()
                .patch("/case/{code}/{id}")
                .then()
                .spec(ok200)
                .extract()
                .as(CaseRs.class);
    }

    @Step("API: Получить информацию о тест-кейсе ID: {id} из проекта '{code}'")
    public static SingleCaseRs getCase(String code, int id) {
        log.info("API: Запрос информации о тест-кейсе ID: [{}] из проекта [{}]", id, code);
        return given()
                .spec(spec)
                .pathParam("code", code)
                .pathParam("id", id)
                .when()
                .get("/case/{code}/{id}")
                .then()
                .spec(ok200)
                .extract()
                .as(SingleCaseRs.class);
    }
}
