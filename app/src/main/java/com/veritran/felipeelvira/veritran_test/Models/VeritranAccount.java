package com.veritran.felipeelvira.veritran_test.Models;

import com.google.gson.annotations.SerializedName;

public class VeritranAccount {

    @SerializedName("user")
    private String user;
    private String balance;
    private String deposit;
    private String withdraw;
    private String id;

    public VeritranAccount(String user, String balance, String deposit, String id, String withdraw ){
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.deposit = deposit;
        this.withdraw = withdraw;
    }

    public String getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getUser() {
        return user;
    }

    public String getWithdraw() {
        return withdraw;
    }
}

