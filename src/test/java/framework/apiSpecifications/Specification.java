package framework.apiSpecifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    public static RequestSpecification requestSpec(String uri)
    {
        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOk()
    {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecInsertOk()
    {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseSpecError()
    {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecCustom(int status)
    {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static ResponseSpecification responseSpecNotFound()
    {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

    public static void setupSpecification(RequestSpecification request, ResponseSpecification response)
    {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
