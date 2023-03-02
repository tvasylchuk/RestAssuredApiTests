package apiTest;

import framework.Logger;
import framework.apiSpecifications.Specification;
import io.restassured.http.ContentType;
import model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

@Test
public class UserApiTests {

    private static final String URI = "https://reqres.in/";
    @Test
    public void usersAvatarTest()
    {
        Logger.getInstance().logTestName("apiTest.UserApiTests.usersAvatarTest");
        Specification.setupSpecification(Specification.requestSpec(URI), Specification.responseSpecOk());
        Logger.getInstance().info("apiTest.UserApiTests.setupSpecification.ok");
        List<UserData> users = given()
                .when()
                .get("api/users?page=1")
                .then().statusCode(200)
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        Logger.getInstance().info("apiTest.UserApiTests.userData.extracted");
        users.forEach(x->Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Logger.getInstance().info("apiTest.UserApiTests.userData.avatar.userId.verification.ok");
        users.forEach(x->Assert.assertTrue(x.getEmail().endsWith("@reqres.in")));
        Logger.getInstance().info("apiTest.UserApiTests.userData.email.verification.ok");
        Assert.assertTrue(users.stream().allMatch(x->x.getAvatar().startsWith("https://reqres.in/img/faces/")));
        Logger.getInstance().info("apiTest.UserApiTests.userData.avatar.dataHeader.verification.ok");
    }
}
