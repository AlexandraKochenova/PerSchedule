package com.example.client.classes;

public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private int familyId;
    private boolean isHeadOfFamily;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public boolean isHeadOfFamily() {
        return isHeadOfFamily;
    }

    public void setHeadOfFamily(boolean headOfFamily) {
        isHeadOfFamily = headOfFamily;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
