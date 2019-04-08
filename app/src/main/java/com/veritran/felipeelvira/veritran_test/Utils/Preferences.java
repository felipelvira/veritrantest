package com.veritran.felipeelvira.veritran_test.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.veritran.felipeelvira.veritran_test.Models.VeritranUser;

public class Preferences {
    private static String SP_NAME = "veritran-shared-pf";

    private final SharedPreferences mPrefs;
    private boolean mIsLoggedIn = false;
    private String TOKEN_NAME  = "token";
    private String USER_NAME  = "username";
    private String USER_ID  = "id";
    private static Preferences INSTANCE;


    public static Preferences get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Preferences(context);
        }
        return INSTANCE;
    }

    private Preferences(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(TOKEN_NAME, null));
    }

    public boolean isLoggedIn(){
        return mIsLoggedIn;
    }

    public String getToken() {
        SharedPreferences.Editor editor = mPrefs.edit();
        return mPrefs.getString(TOKEN_NAME,null);
    }

    public VeritranUser getUser() {
        SharedPreferences.Editor editor = mPrefs.edit();
        return (VeritranUser) mPrefs.getAll();
    }

    public String getId() {
        SharedPreferences.Editor editor = mPrefs.edit();
        return mPrefs.getString(USER_ID,null);
    }

    public void saveUser(VeritranUser affiliate) {
        if (affiliate != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(TOKEN_NAME, affiliate.getToken());
            editor.putString(USER_NAME, affiliate.getUser());
            editor.putString(USER_ID, affiliate.getId());
            editor.apply();

            mIsLoggedIn = true;
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(TOKEN_NAME, null);
        editor.apply();
    }




}
