package vn.anhnguyen.mt.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;

public class SharePrefUtils implements ISharedPrefUtils {

    private final static String PREF_LOGIN_STATUS = "login status";
    private final static String PREF_TOKEN = "token";
    private final static String PREF_USER_ID = "user id";
    private final static String PREF_USER_NAME = "user-name";
    private final static String PREF_AVATAR = "avatar";
    private final static String PREF_NAME = "name";
    private final static String PREF_LAST_NAME = "lase-name";
    private final static String PREF_BALANCE = "balance";
    private final static String PREF_TYPE = "type";
    private final static String PREF_POINT = "point";

    @SuppressLint("StaticFieldLeak")
    private static SharePrefUtils sInstance;
    private Context mContext;

    public static SharePrefUtils instance() {
        if (sInstance == null)
            sInstance = new SharePrefUtils();

        return sInstance;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    //PREF_LOGIN_STATUS
    @Override
    public void setLoginStatus(Boolean loginStatus) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_LOGIN_STATUS, loginStatus);
        editor.apply();
    }

    @Override
    public Boolean getLoginStatus() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getBoolean(PREF_LOGIN_STATUS, false);
    }

    // PREF_TOKEN
    @Override
    public void setLoginStatusToken(String token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_TOKEN, token);
        editor.apply();
    }

    @Override
    public String getLoginStatusToken() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_TOKEN, null);
    }

    // UserId
    @Override
    public void setUserId(int userId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_USER_ID, userId);
        editor.apply();
    }

    @Override
    public int getUserId() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getInt(PREF_USER_ID, 0);
    }

    // Username
    @Override
    public void setUserName(String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }

    @Override
    public String getUserName() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_USER_NAME, "");
    }

    // Avatar
    @Override
    public void setAvatar(String avatar_url) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_AVATAR, avatar_url);
        editor.apply();
    }

    @Override
    public String getAvatar() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_AVATAR, "");
    }

    @Override
    public void setName(String Name) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_NAME, Name);
        editor.apply();
    }

    @Override
    public String getName() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_NAME, "");
    }

    @Override
    public void setLastname(String lastname) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_LAST_NAME, lastname);
        editor.apply();
    }

    @Override
    public String getLastname() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_LAST_NAME, "");
    }

    @Override
    public void setAccountType(int type) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_TYPE, type);
        editor.apply();
    }

    @Override
    public int getAccountType() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getInt(PREF_TYPE, 0);
    }

    @Override
    public void setBalance(int getBalance) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_BALANCE, getBalance);
        editor.apply();
    }

    @Override
    public int getBalance() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getInt(PREF_BALANCE, 0);
    }

    @Override
    public void setPoint(int point) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_POINT, point);
        editor.apply();
    }

    @Override
    public int getPoint() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getInt(PREF_POINT, 0);
    }

}
