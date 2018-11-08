package com.nxnd.travelnote.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.util.SQLiteDBUtil;

/**
 * Created by huchuan on 2018/11/1.
 */

public class TravelNotesService {

    //新增Step
    public void addStep(Context context, StepModel stepModel){
        SQLiteDBUtil db = new SQLiteDBUtil(context);
        SQLiteDatabase sd = db.getWritableDatabase();
        //创建sql语句
        String sql = "insert into step values(null,?,?,?,?,?,?,?)";
        //执行sql语句
        sd.execSQL(sql, new Object[] { stepModel.getImageurl(), stepModel.getIndex()
                ,stepModel.getContent(),stepModel.getDatetime(),stepModel.getLocation()
                ,stepModel.getLongitude(),stepModel.getLatitude()
        });
        //关闭数据库
        db.close();
    }
    //修改Step
    public void updateStep(){

    }

}
