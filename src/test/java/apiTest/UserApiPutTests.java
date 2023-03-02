package apiTest;

import framework.apiSpecifications.Specification;
import model.User;
import model.UserModified;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;

@Test
public class UserApiPutTests extends UserApiBaseClass{

    @Test (dataProvider = "user-to-update")
    public void updateUserPutTest(User expectedUser)
    {
        Specification.setupSpecification(Specification.requestSpec(URI), Specification.responseSpecOk());
        UserModified actualUser = given().body(expectedUser).when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserModified.class);

        var regex = "[.]\\d{3,}Z";
        var systemDate = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        Assert.assertEquals(systemDate, actualUser.getUpdatedAt().replaceAll(regex, ""));
    }
}
