package model;

public class RegisterLogonResponse {
    public Integer id;
    public String token;
    public RegisterLogonResponse(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
