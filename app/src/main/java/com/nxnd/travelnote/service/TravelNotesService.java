package com.nxnd.travelnote.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.helper.SQLiteDBUtil;
import com.nxnd.travelnote.model.TravelNotesModel;

/**
 * Created by huchuan on 2018/11/1.
 */

public class TravelNotesService {

    //新建日记
    public static void createNote(String title,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("note", Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("title", title);
        //提交当前数据
        editor.apply();
    }

    //获取日记信息
    public static TravelNotesModel getNoteInfo(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("note", Activity.MODE_PRIVATE);
        String title = sharedPreferences.getString("title",null);
        String coverImage = sharedPreferences.getString("coverUrl",null);
        TravelNotesModel travelNotesModel = new TravelNotesModel(title,coverImage);
        return travelNotesModel;
    }
}
