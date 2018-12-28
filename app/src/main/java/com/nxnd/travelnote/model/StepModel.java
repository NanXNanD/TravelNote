package com.nxnd.travelnote.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by huchuan on 2018/11/1.
 */

@Table(name = "step")//表名注解
public class StepModel {

    //Id
    @Column(name = "id",isId = true,autoGen = true)
    private int id;

    //日记id
    @Column(name = "noteid")
    private int noteid;
    //索引

    @Column(name = "index")
    private int index;

    //图片路径（网络）
    @Column(name = "imageurl")
    private String imageurl;

    //内容
    @Column(name = "content")
    private String content;

    //时间日期
    @Column(name = "datetime")
    private String datetime;

    //地点名称
    @Column(name = "location")
    private String location;

    //经度
    @Column(name = "longitude")
    private float longitude;

    public StepModel() {
    }

    //纬度
    @Column(name = "latitude")

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }
}
