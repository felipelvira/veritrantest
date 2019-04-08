package com.veritran.felipeelvira.veritran_test.Models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class VeritranUser {

    @SerializedName("username")
    private String username;
    private String password;
    private String _id;
    private String token;

    public VeritranUser(String username, String token, String _id) {
        this.username = username;
        this.token = token;
        this._id = _id;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username, String token) {
        this.username = username;
        this.token = token;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String toString()
    {
        return( username );
    }


}
