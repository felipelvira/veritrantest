package com.veritran.felipeelvira.veritran_test.Utils;

import com.google.gson.annotations.SerializedName;

public class DepositBody {
    @SerializedName("deposit")
    private String deposit;
    private String userId;
    private String withdraw;

    public DepositBody(String deposit, String userId, String withdraw) {
        this.deposit = deposit;
        this.userId = userId;
        this.withdraw = withdraw;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


