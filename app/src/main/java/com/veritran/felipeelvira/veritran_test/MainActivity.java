package com.veritran.felipeelvira.veritran_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.veritran.felipeelvira.veritran_test.Models.VeritranAccount;
import com.veritran.felipeelvira.veritran_test.Models.VeritranTransferAccount;
import com.veritran.felipeelvira.veritran_test.Models.VeritranUser;
import com.veritran.felipeelvira.veritran_test.Services.AccountService;
import com.veritran.felipeelvira.veritran_test.Services.AuthService;
import com.veritran.felipeelvira.veritran_test.Services.ServiceClient;
import com.veritran.felipeelvira.veritran_test.Utils.DepositBody;
import com.veritran.felipeelvira.veritran_test.Utils.Preferences;
import com.veritran.felipeelvira.veritran_test.Utils.AccountBody;
import com.veritran.felipeelvira.veritran_test.Utils.TransferBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button depositButton, withdrawButton, transferButton, logutButton;
    private TextView depositText, withdrawText, balanceText, transferText;
    private LinearLayout transferLayout;


    private AccountService accountService;
    private VeritranAccount veritranAccount;
    private List<VeritranUser> veritranUsers;
    private AuthService veritranUser;
    private Spinner usersDropDown;
    private VeritranUser selectedUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }


    private void initControls() {
        depositButton = (Button) findViewById(R.id.addDepositButton);
        transferButton = (Button) findViewById(R.id.transfer_button);
        withdrawButton = (Button) findViewById(R.id.withDrawButton);
        logutButton = findViewById(R.id.logout_button);
        usersDropDown = (Spinner) findViewById(R.id.spinner);

        depositText = findViewById(R.id.depositText);
        balanceText = findViewById(R.id.balanceText);
        transferText = findViewById(R.id.external_deposit_text);
        withdrawText = findViewById(R.id.withdrawText);

        transferLayout = findViewById(R.id.transfer_layout);

        transferLayout.setVisibility(View.INVISIBLE);

        accountService = ServiceClient.getClientInstance(MainActivity.this).create((AccountService.class));
        getBalanceAccount();


        veritranUser = ServiceClient.getClientInstance(MainActivity.this).create((AuthService.class));
        getAllUsers();


        usersDropDown.setOnItemSelectedListener(new ItemSelectedListener());
        depositButton.setOnClickListener(this);
        withdrawButton.setOnClickListener(this);
        transferButton.setOnClickListener(this);
        logutButton.setOnClickListener(this);
    }

    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view != null)
        {
            int id = view.getId();
            Log.v("id", String.valueOf(id));
            if(id == depositButton.getId()){
                String input = depositText.getText().toString();
                Log.v("depositamount", input);
                if(input != "0"){
                    addDepositToAccount(input);
                }
            }
            else if( id == withdrawButton.getId()){
                String input = withdrawText.getText().toString();
                Log.v("withdrawamount", input);
                if(input != "0"){
                    withdrawFromAccount(input);
                }
            }
            else if( id == transferButton.getId() && selectedUser != null){
                String input = transferText.getText().toString();
                String user = selectedUser.getId();
                Log.v("transferamount", user);
                if(input != "0"){
                    transferDeposit(input, user);
                }
            }
            else if(id == logutButton.getId()){
                logutFunction();
            }

        }
    }

    private void getAllUsers(){
        Call<List<VeritranUser>> usersCall = veritranUser.all(Preferences.get(MainActivity.this).getId());
        usersCall.enqueue(new Callback<List<VeritranUser>>() {
            @Override
            public void onResponse(Call<List<VeritranUser>> call, Response<List<VeritranUser>> response) {
                if( response.code() != 200 ){
                    showError(response.message());
                    return;
                }
                veritranUsers  = response.body();
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, veritranUsers);
                spinnerArrayAdapter.insert(new VeritranUser("Select User", "" ,""), 0);
                usersDropDown.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<VeritranUser>> call, Throwable t) {
                Log.v("error", String.valueOf(call.isExecuted()), t);
            }
        });

    }

    private void  getBalanceAccount (){
        Call<VeritranAccount> accountCall = accountService.account(new AccountBody(Preferences.get(MainActivity.this).getId() ));
        accountCall.enqueue(new Callback<VeritranAccount>() {
            @Override
            public void onResponse(Call<VeritranAccount> call, Response<VeritranAccount> response) {
                if( response.code() != 200 ){
                    showError(response.message());
                    return;
                }
                veritranAccount = response.body();
                Log.v("Account Balance", veritranAccount.getBalance());
                balanceText.setText(veritranAccount.getBalance());
            }

            @Override
            public void onFailure(Call<VeritranAccount> call, Throwable t) {
                Log.v("error", String.valueOf(call.isExecuted()), t);
            }
        });
    }

    private void addDepositToAccount (String depositAmount){
        String userId = Preferences.get(MainActivity.this).getId();
        Call<VeritranAccount> depositCall = accountService.deposit(new DepositBody(depositAmount,userId, ""));
        depositCall.enqueue(new Callback<VeritranAccount>() {
            @Override
            public void onResponse(Call<VeritranAccount> call, Response<VeritranAccount> response) {
                if( response.code() != 200 ){
                    showError(response.message());
                    return;
                }
                veritranAccount = response.body();
                Log.v("After Deposit Balance", veritranAccount.getBalance());
                balanceText.setText(veritranAccount.getBalance());
                depositText.setText("");
            }

            @Override
            public void onFailure(Call<VeritranAccount> call, Throwable t) {
                Log.v("error", String.valueOf(call.isExecuted()), t);
            }
        });
    }


    private void withdrawFromAccount (String withdrawAmount){
        String userId = Preferences.get(MainActivity.this).getId();
        Call<VeritranAccount> withdrawCall = accountService.withdraw(new DepositBody("",userId, withdrawAmount));

        withdrawCall.enqueue(new Callback<VeritranAccount>() {
            @Override
            public void onResponse(Call<VeritranAccount> call, Response<VeritranAccount> response) {
                if( response.code() != 200 ){
                    showError(response.message());
                    return;
                }
                veritranAccount = response.body();
                Log.v("After Withdral Balance", veritranAccount.getBalance());
                balanceText.setText(veritranAccount.getBalance());
                withdrawText.setText("");
            }

            @Override
            public void onFailure(Call<VeritranAccount> call, Throwable t) {
                Log.v("error", String.valueOf(call.isExecuted()), t);
            }
        });
    }


    private void transferDeposit (String depositAmount,String userId){

        String currentUserId = Preferences.get(MainActivity.this).getId();

        Log.v("trnasfer", new TransferBody(currentUserId,userId,depositAmount).toString() );

        Call<VeritranTransferAccount> depositCall = accountService.transfer(new TransferBody(currentUserId,userId,depositAmount));
        depositCall.enqueue(new Callback<VeritranTransferAccount>() {
            @Override
            public void onResponse(Call<VeritranTransferAccount> call, Response<VeritranTransferAccount> response) {
                Log.v("code", String.valueOf(response.code()));
                if( response.code() != 200 ){
                    showError(response.message());
                    return;
                }
                Log.v("fromext-DepositBalance", response.body().toString());

                getBalanceAccount();
                //veritranAccount = response.body();
                //balanceText.setText(veritranAccount.getBalance());
                transferText.setText("");
            }

            @Override
            public void onFailure(Call<VeritranTransferAccount> call, Throwable t) {
                Log.v("error", String.valueOf(call.isExecuted()), t);
            }
        });
    }

    private void logutFunction(){
        Preferences.get(MainActivity.this).logOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(usersDropDown.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if ( String.valueOf(usersDropDown.getSelectedItem()).equals( "Select User")) {
                Log.v("selected", firstItem);
                transferLayout.setVisibility(View.INVISIBLE);
                // ToDo when first item is selected
            } else {
                transferLayout.setVisibility(View.VISIBLE);
                if(veritranUsers != null){
                    selectedUser = veritranUsers.get(pos);
                    Toast.makeText(parent.getContext(),
                        "You have selected : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();

                }
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }
}
