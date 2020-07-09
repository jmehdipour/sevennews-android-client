package com.example.sevennews.data;

import android.content.Context;

public class NewsRepositoryProvider {

    public static NewsDataSource provideNewsDataSource(Context context){
        return new NewsRepository(context);

    }
}
