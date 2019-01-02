package com.nxnd.travelnote.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;


/**
 * Created by huchuan on 2018/11/1.
 */

public class UserService {

    public static void saveUserInfo(Context context,String phoneNum,String password,String username,String userImg){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("phoneNum", phoneNum);
        editor.putString("password", password);
        editor.putString("username",username);
        editor.putString("userImg",userImg);
        //提交当前数据
        editor.apply();
    }

    public static void clearUserInfo(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
