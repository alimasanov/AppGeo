package com.alimasanov.appgeo.db;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Entity.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract DAO getDao();
}
