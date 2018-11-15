package com.nxnd.travelnote;

import android.app.Application;

import org.xutils.x;

/**
 * Created by huchuan on 2018/11/15.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //是否输出debug日志，开启debug会影响性能。
    }
}
