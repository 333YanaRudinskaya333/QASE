package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseAdapter {
    public static String TOKEN = "e67f693f16e32878286b5575271f864de16aa51c79bfa3038d63d8018a075a6a"; //скпрятать токен
    public static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    public static RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("https://api.qase.io")
            .setBasePath("/v1")
            .setContentType(ContentType.JSON)
            .addHeader("Token", TOKEN)
            .build();

    public static ResponseSpecification ok200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
