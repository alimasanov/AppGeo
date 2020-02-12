package com.alimasanov.appgeo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DAO {

    @Insert
    void insert(Entity entity);

    @Delete
    void delete(Entity entity);

    @Query("select * from entity")
    Flowable<List<Entity>> list();
}
