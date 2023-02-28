package com.app.apisvtes.uploads;

public class Users {

    private final int UserId;
    private final String UserName;
    private final String FullName;
    private final String Email;

    public Users(int UserId, String UserName, String FullName, String Email) {
        this.UserId = UserId;
        this.UserName = UserName;
        this.FullName = FullName;
        this.Email = Email;
    }

    public int getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return Email;
    }
}