package com.alimasanov.appgeo.db;

import android.app.Application;

import androidx.room.Room;

public class MyApp extends Application {

    private static MyApp instance;
    private MyDatabase db;

    public static MyApp getInstance() {
        return instance;
    }

    public MyDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(
                instance,
                MyDatabase.class,
                "db").build();
    }
}
