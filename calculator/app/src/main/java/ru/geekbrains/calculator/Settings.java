package ru.geekbrains.calculator;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class Settings {

    private final SharedPreferences preferences;
    private final Context context;

    public Settings(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isDarkTheme() {
        return preferences.getBoolean(context.getString(R.string.settings_key_theme), false);
    }
}
