package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// DB 활용을 위한 SharedPreferences 활용 클래스
class AppPreference {
    SharedPreferences prefs;
    Context context;

    public AppPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", input);
        editor.commit();
    }

    public Boolean getFirstRun() {
        return prefs.getBoolean("isFirst", true);
    }
}