package com.example.sevennews;

import android.app.Application;
import android.content.Context;

import com.example.sevennews.setting.LocaleManager;

public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.updateResource(base, LocaleManager.getLanguage(base)));
    }
}
