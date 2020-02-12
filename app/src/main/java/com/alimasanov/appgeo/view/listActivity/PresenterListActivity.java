package com.alimasanov.appgeo.view.listActivity;

import android.annotation.SuppressLint;

import com.alimasanov.appgeo.db.Entity;
import com.alimasanov.appgeo.db.MyApp;
import com.alimasanov.appgeo.db.MyDatabase;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

class PresenterListActivity {

    private MyDatabase db;
    private ListActivity listActivity;

    PresenterListActivity(ListActivity listActivity) {
        this.listActivity = listActivity;
        db = MyApp.getInstance().getDb();
    }

    @SuppressLint("CheckResult")
    void loadData() {
        db.getDao().list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Entity>>() {
                    @Override
                    public void accept(List<Entity> entities) {
                        listActivity.showLocation(entities);
                    }
                });
    }
}
