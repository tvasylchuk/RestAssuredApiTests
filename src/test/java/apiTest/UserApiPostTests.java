package apiTest;

import framework.Logger;
import framework.apiSpecifications.Specification;
import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

@Test
public class UserApiPostTests extends UserApiBaseClass {

    @Test (dataProvider = "user-to-create")
    public void createUserPostTest(User expectedUser)
    {
        Logger.getInstance().logTestName("apiTest.UserApiPostTests.createUserPostTest");
        Specification.setupSpecification(Specification.requestSpec(URI), Specification.responseSpecInsertOk());
        Logger.getInstance().info("apiTest.UserApiPostTests.setupSpecification.ok");
        UserCreateResponse actualUser = given().body(expectedUser).when()
                .post("api/users")
                .then().statusCode(201).log().all()
                .extract().as(UserCreateResponse.class);

        Assert.assertNotNull(actualUser.getName());
        Assert.assertNotNull(actualUser.getJob());
        Assert.assertNotNull(actualUser.getId());
        Assert.assertNotNull(actualUser.getCreatedAt());

        Assert.assertEquals(actualUser.getName(), expectedUser.getName());
        Assert.assertEquals(actualUser.getJob(), expectedUser.getJob());
    }

    @Test (dataProvider = "login-to-register")
    public void createLoginRegisterPostTest(Login expectedLogin)
    {
        Logger.getInstance().logTestName("apiTest.UserApiPostTests.createLoginRegisterPostTest");
        Specification.setupSpecification(Specification.requestSpec(URI), Specification.responseSpecOk());
        Logger.getInstance().info("apiTest.UserApiPostTests.setupSpecification.ok");
        RegisterLogonResponse actualResponse = given().body(expectedLogin).when()
                .post("api/register")
                .then().log().all()
                .extract().as(RegisterLogonResponse.class);

        Assert.assertNotNull(actualResponse.getId());
        Assert.assertNotNull(actualResponse.getToken());

        Assert.assertEquals(actualResponse.getId(), 4);
        Assert.assertEquals(actualResponse.getToken(), "QpwL5tke4Pnpja7X4");
    }

    @Test (dataProvider = "login-not-register")
    public void createLoginNotRegisterPostTest(BaseLogin expectedLogin)
    {
        Logger.getInstance().logTestName("apiTest.UserApiPostTests.createLoginRegisterPostTest");
        Specification.setupSpecification(Specification.requestSpec(URI), Specification.responseSpecError());
        Logger.getInstance().info("apiTest.UserApiPostTests.setupSpecification.ok");
        ErrorResponse response = given().body(expectedLogin).when()
                .post("api/register")
                .then().log().all()
                .extract().as(ErrorResponse.class);

        Assert.assertEquals(response.getError(), "Missing password");
    }
}
