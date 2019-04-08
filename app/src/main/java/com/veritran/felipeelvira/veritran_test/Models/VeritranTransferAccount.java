package com.veritran.felipeelvira.veritran_test.Models;

import com.google.gson.annotations.SerializedName;

public class VeritranTransferAccount {

    @SerializedName("currentAccountObj")
    private VeritranAccount currentAccountObj;
    private VeritranAccount transferAccountObj;

    public VeritranTransferAccount(VeritranAccount currentAccountObj, VeritranAccount transferAccountObj ) {
        this.currentAccountObj = currentAccountObj;
        this.transferAccountObj = transferAccountObj;
    }
}
