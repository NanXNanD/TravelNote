package com.nxnd.travelnote.model;

/**
 * Created by huchuan on 2018/11/1.
 */

public class StepModel {
    private int index;
    private String imageurl;
    private String content;
    private String datetime;
    private String location;
    private float longitude;
    private float latitude;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public StepModel(int index, String imageurl, String content, String datetime, String location, float longitude, float latitude) {

        this.index = index;
        this.imageurl = imageurl;
        this.content = content;
        this.datetime = datetime;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
