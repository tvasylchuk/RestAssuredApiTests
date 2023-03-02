package apiTest;

import model.BaseLogin;
import model.Login;
import model.User;
import org.testng.annotations.DataProvider;

public class UserApiBaseClass {
    protected static final String URI = "https://reqres.in/";

    @DataProvider(name="user-to-create")
    public Object[][] userInit()
    {
        return new Object[][] {{new User("morpheus", "leader")}};
    }

    @DataProvider(name="user-to-update")
    public Object[][] userUpdate()
    {
        return new Object[][] {{new User("morpheus", "zion resident")}};
    }

    @DataProvider(name="login-to-register")
    public Object[][] loginInit()
    {
        return new Object[][] {{new Login("eve.holt@reqres.in", "pistol")}};
    }

    @DataProvider(name="login-not-register")
    public Object[][] baseLoginInit()
    {
        return new Object[][] {{new BaseLogin("sydney@fife")}};
    }
}
