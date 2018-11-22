package com.nxnd.travelnote.model;

/**
 * Created by linSir on 17/3/11.游记model
 */
public class TravelNotesModel {

    private String noteId;
    private String userId;
    private String title;
    private String coverUrl;
    private String startDate;
    private String location;
    private String userName;
    private String userImage;
    private int viewNum;

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

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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