package com.veritran.felipeelvira.veritran_test.Utils;

import com.google.gson.annotations.SerializedName;

public class TransferBody {
    @SerializedName("currentUser")
    private String currentUser;
    private String transferUser;
    private String transferDeposit;

    public TransferBody(String currentUser, String transferUser, String transferDeposit) {
        this.currentUser = currentUser;
        this.transferUser = transferUser;
        this.transferDeposit = transferDeposit;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getTransferUser() {
        return transferUser;
    }

    public String getTransferDeposit() {
        return transferDeposit;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void setTransferDeposit(String transferDeposit) {
        this.transferDeposit = transferDeposit;
    }

    public void setTransferUser(String transferUser) {
        this.transferUser = transferUser;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
