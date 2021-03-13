package com.example.rrszoo.Java;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class ZooLanguage {
    // false equals hebrew
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editore;


    ZooLanguage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editore = sharedPreferences.edit();
    }

    protected boolean isEnglish() {
        return this.sharedPreferences.getBoolean("language", true);
    }

    @SuppressLint("NewApi")
    protected void setEnglish() {
        editore.putBoolean("language", true);
        editore.apply();
    }

    @SuppressLint("NewApi")
    protected void setHebrew() {
        editore.putBoolean("language", false);
        editore.apply();
    }
}
