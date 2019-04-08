package com.veritran.felipeelvira.veritran_test.Utils;


import com.google.gson.annotations.SerializedName;

public class AuthBody {
    @SerializedName("username")
    private String username;
    private String password;

    public AuthBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
