package model;

public class Login extends BaseLogin{
    private String password;

    public Login(String email, String password) {
        super(email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
