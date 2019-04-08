package com.veritran.felipeelvira.veritran_test.Services;

import com.veritran.felipeelvira.veritran_test.Models.VeritranAccount;
import com.veritran.felipeelvira.veritran_test.Models.VeritranTransferAccount;
import com.veritran.felipeelvira.veritran_test.Utils.DepositBody;
import com.veritran.felipeelvira.veritran_test.Utils.AccountBody;
import com.veritran.felipeelvira.veritran_test.Utils.TransferBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountService {
    @POST("accounts/deposit")
    Call<VeritranAccount> deposit(@Body DepositBody depositBody);

    @POST("accounts/transfer")
    Call<VeritranTransferAccount> transfer(@Body TransferBody transferBody);

    @POST("accounts/withdral")
    Call<VeritranAccount> withdraw(@Body DepositBody depositBody);

    @POST("accounts/account")
    Call<VeritranAccount> account(@Body AccountBody accountBody);
}
