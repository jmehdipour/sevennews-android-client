package com.example.sevennews.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.sevennews.setting.LocaleManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.updateResource(newBase, LocaleManager.getLanguage(newBase)));
    }

    public abstract void setProgressIndicator(boolean shouldShow);
}
