package com.nxnd.travelnote.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.helper.SQLiteDBUtil;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.nxnd.travelnote.util.CommonUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        //标题
        editor.putString("title", title);
        //时间设置默认日期
        editor.putString("time",CommonUtil.getCurrDate());
        //地点设置默认地点
        editor.putString("location","未设置城市");
        //提交当前数据
        editor.apply();
    }

    //获取日记信息
    public static TravelNotesModel getNoteInfo(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("note", Activity.MODE_PRIVATE);
        String title = sharedPreferences.getString("title",null);
        String coverImage = sharedPreferences.getString("coverUrl","");
        String time = sharedPreferences.getString("time","");
        String location = sharedPreferences.getString("location","未设置");
        Log.d("noteInfo", "title: "+title);
        Log.d("noteInfo", "coverImage: "+coverImage);
        Log.d("noteInfo", "time: "+time);
        Log.d("noteInfo", "location: "+location);
        TravelNotesModel travelNotesModel = new TravelNotesModel(title,coverImage);
        travelNotesModel.setLocation(location);
        travelNotesModel.setStartDate(time);
        return travelNotesModel;
    }

    //修改字段
    public static void changeInfo(Context context,String tag,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("note", Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //用putString的方法保存数据
        //标题
        editor.putString(tag, value);
        //提交当前数据
        editor.apply();
    }

    //删除
    public static void delNote(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("note", Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
