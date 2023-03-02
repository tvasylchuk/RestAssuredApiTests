package model;

public class UserToCreate {
    private String name;
    private String job;

    public UserToCreate(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
