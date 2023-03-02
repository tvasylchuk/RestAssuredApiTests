package model;

import java.util.Date;

public class UserModified extends User{
    private String updatedAt;

    public UserModified(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
