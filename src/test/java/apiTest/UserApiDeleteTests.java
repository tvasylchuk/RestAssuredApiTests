package apiTest;

import framework.apiSpecifications.Specification;
import model.User;
import model.UserModified;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;

@Test
public class UserApiDeleteTests extends UserApiBaseClass{

    @Test
    public void removeUserDeleteTest()
    {
        Specification.setupSpecification(Specification.requestSpec(URI), Specification.responseSpecCustom(204));
        given().when()
                .delete("api/users/2")
                .then().log().all();
    }
}
