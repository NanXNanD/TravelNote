package com.nxnd.travelnote.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huchuan on 2018/11/1.
 */

public class SQLiteDBUtil extends SQLiteOpenHelper {

    public static final String NAME = "travelnote";//数据库名

    public static final int VERSION = 1;//版本号

    public SQLiteDBUtil(Context context) {
        super(context, NAME, null, VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//执行创建数据表的sql语句
        String userSQL = "create table step" +
                "(id integer primary key autoincrement," +
                "imageurl varchar(32)," +
                "index integer" +
                "content varchar(500)" +
                "datetime varchar(32)" +
                "location varchar(32)" +
                "longitude decimal(5,2)" +
                "latitude decimal(5,2))";
        db.execSQL(userSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
