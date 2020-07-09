package com.example.sevennews.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;


    public abstract LocalDataSource getLocalDataSource();

    public static AppDatabase getInstance(Context context){

        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "seven_news")
                    .allowMainThreadQueries().build();
        }

        return appDatabase;
    }



}
