package com.kashkash.applicaster.applicaster.util;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by tsahkashkash on 25/06/16.
 */
public class PrefsController {

    private PrefsController() {

    }

    private static final String GENERAL_PREFS_ID = "general_prefs";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCESS_TOKEN_TYPE = "token_status";


    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(GENERAL_PREFS_ID, Context.MODE_PRIVATE);
    }

    public static void setAccessToken(Context context, String token) {
        getPrefs(context).edit().putString(ACCESS_TOKEN, token).apply();
    }

    public static String getAccessToken(Context context) {
        return getPrefs(context).getString(ACCESS_TOKEN, "");
    }

    public static void setTokenType(Context context, String tokenType) {
        getPrefs(context).edit().putString(ACCESS_TOKEN_TYPE, tokenType).apply();
    }

    public static String getTokenType(Context context) {
        return getPrefs(context).getString(ACCESS_TOKEN_TYPE, "");
    }


}