package com.gustavoaz7.whatsapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String FILE_NAME = "WHATSAPP_PREFERENCES";
    private int MODE = 0;
    private String NAME = "name";
    private String PHONE = "phone";
    private String TOKEN = "token";

    public Preferences(Context contextParam) {
        context = contextParam;

        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveUserPreferences(String name, String phone, String token) {
        editor.putString(NAME, name);
        editor.putString(PHONE, phone);
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getUserPreferences() {
        HashMap<String, String> userPreferences = new HashMap<>();
        userPreferences.put(NAME, preferences.getString(NAME, null));
        userPreferences.put(PHONE, preferences.getString(PHONE, null));
        userPreferences.put(TOKEN, preferences.getString(TOKEN, null));
        return userPreferences;
    }
}
