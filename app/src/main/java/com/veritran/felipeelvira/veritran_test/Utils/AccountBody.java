package com.veritran.felipeelvira.veritran_test.Utils;

import com.google.gson.annotations.SerializedName;

public class AccountBody {
    @SerializedName("id")
    private String id;

    public AccountBody(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
