package com.nxnd.travelnote.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by huchuan on 日记model
 */
public class TravelNotesModel {

//    日记id

    public TravelNotesModel(String noteId, String userId, String title, String coverUrl, String startDate, String location, String userName, String userImage, int viewNum) {
        this.noteId = noteId;
        this.userId = userId;
        this.title = title;
        this.coverUrl = coverUrl;
        this.startDate = startDate;
        this.location = location;
        this.userName = userName;
        this.userImage = userImage;
        this.viewNum = viewNum;
    }

    private String noteId;

    //用户id
    private String userId;

    //标题
    private String title;

    //封面图片
    private String coverUrl;

    //开始时间
    private String startDate;

    //地点
    private String location;

    //用户名
    private String userName;
    //用户头像
    private String userImage;
    //阅读数量
    private int viewNum;

    public TravelNotesModel(String title, String coverUrl) {
//        this.noteId = noteId;
        this.title = title;
        this.coverUrl = coverUrl;
    }

    public TravelNotesModel( String title, String coverUrl, String startDate, String location, String userName, String userImage, int viewNum) {
        this.title = title;
        this.coverUrl = coverUrl;
        this.startDate = startDate;
        this.location = location;
        this.userName = userName;
        this.userImage = userImage;
        this.viewNum = viewNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }
}