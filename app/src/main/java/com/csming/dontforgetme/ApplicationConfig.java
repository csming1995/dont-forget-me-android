package com.csming.dontforgetme;

import android.content.Context;

/**
 * @author Created by csming on 2018/10/4.
 */
public class ApplicationConfig {

    private static final String PREFERENCE_NAME = BuildConfig.APPLICATION_ID + "_preferecne";

    public static void setToken(Context context, String token) {
        context.getSharedPreferences(PREFERENCE_NAME, 0).edit().putString("token", token).apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, 0).getString("token", "");
    }

}
