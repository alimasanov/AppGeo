package com.alimasanov.appgeo.db;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@androidx.room.Entity
public class Entity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String fio;
    private String city;
    private String date;
    private String geoPosition;
    private double latitude;
    private double longitude;

    @Ignore
    private Boolean expanded;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Entity(String fio, String city, String date, String geoPosition, double latitude, double longitude) {
        this.fio = fio;
        this.city = city;
        this.date = date;
        this.geoPosition = geoPosition;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getGeoPosition() {
        return geoPosition;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGeoPosition(String geoPosition) {
        this.geoPosition = geoPosition;
    }
}
