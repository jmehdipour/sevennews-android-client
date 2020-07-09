package com.example.sevennews.setting;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class LocaleManager {

    public static void setNewLocale(Context c, String language){
        persistLanguage(c,language);
        updateResource(c,language);

    }

    public static String getLanguage(Context c){
        return SettingSharedPrefManager.getInstance(c).getDefaultLanguage();
    }

    public static void persistLanguage(Context c, String language){
        SettingSharedPrefManager.getInstance(c).saveDefaultLanguage(language);
    }


    public static Context updateResource(Context context, String language){
        Locale myLocale = new Locale(language);
        Locale.setDefault(myLocale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        if (Build.VERSION.SDK_INT > 17){
            configuration.setLocale(myLocale);
            context = context.createConfigurationContext(configuration);
        }else{
            configuration.locale = myLocale;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        }

        return context;

    }




}
