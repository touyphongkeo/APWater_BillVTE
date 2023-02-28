package com.app.apisvtes.uploads;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Belal on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class SharedPrefManagers {

    //the constants
    private static final String SHARED_PREF_NAME = "apwater";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_FULLNAME = "keyfullname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_USERID = "keyuserid";
    //
    private static final String KEY_HH_ID= "keyhh_id";
    private static final String KEY_HH_CODE = "keyhh_code";
    private static final String KEY_HH_HEAD_NAME = "keyhh_head_name";
    private static final String KEY_TOTAL_HH_MEMBERS = "keytotal_hh_members";
    private static final String KEY_TOTAL_HH_NEMBERS_FEMALE = "keytotal_hh_members_female";


    private static SharedPrefManagers mInstance;
    private static Context mCtx;

    private SharedPrefManagers(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManagers getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagers(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(Users userLogin) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USERID, userLogin.getUserId());
        editor.putString(KEY_USERNAME, userLogin.getUserName());
        editor.putString(KEY_FULLNAME, userLogin.getFullName());
        editor.putString(KEY_EMAIL, userLogin.getEmail());
        editor.apply();
    }


    public void viewinfor(infor viewinfor) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_HH_ID, viewinfor.gethh_id());
        editor.putString(KEY_HH_CODE, viewinfor.gethh_code());
        editor.putString(KEY_HH_HEAD_NAME, viewinfor.gethh_head_name());
        editor.putString(KEY_TOTAL_HH_MEMBERS, viewinfor.gettotal_hh_members());
        editor.putString(KEY_TOTAL_HH_NEMBERS_FEMALE, viewinfor.gettotal_hh_members_female());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public Users getUsers() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Users(
                sharedPreferences.getInt(KEY_USERID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_FULLNAME, null)
        );
    }

    //this method will give the logged in user
    public infor getInfor() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new infor(
                sharedPreferences.getString(KEY_HH_ID, null),
                sharedPreferences.getString(KEY_HH_CODE, null),
                sharedPreferences.getString(KEY_HH_HEAD_NAME, null),
                sharedPreferences.getString(KEY_TOTAL_HH_MEMBERS, null),
                sharedPreferences.getString(KEY_TOTAL_HH_NEMBERS_FEMALE, null)
        );
    }

}
