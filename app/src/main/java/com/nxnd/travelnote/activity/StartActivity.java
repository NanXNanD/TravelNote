package com.nxnd.travelnote.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.helper.DBHelper;

import org.xutils.DbManager;

import permison.PermissonUtil;
import permison.listener.PermissionListener;


/**
 * Created by huchuan on 18/10/28.启动页面
 */
public class StartActivity extends AppCompatActivity {
    //需要的权限列表
    String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
    };
    private static final int LOCATION_CODE=101;

    private LocationManager lm;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        //获取权限
        getPermissions();
        //设置Sqlite路径
//        String strPath = getFilesDir().getAbsolutePath() + "/"; // 正式数据库存储地址
        String strPath = Environment.getExternalStorageDirectory() + "/lxrj/";     // 测试数据库存储地址
        DBHelper.getInstance().setDbDir(strPath);

    }

    /**
     * 获取权限 by huchuan
     */
    private void getPermissions(){
        PermissonUtil.checkPermission(StartActivity.this, new PermissionListener() {
            @Override
            public void havePermission() {
                Toast.makeText(StartActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                checkLoginState();
            }

            @Override
            public void requestPermissionFail() {
                Toast.makeText(StartActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
            }
        }, permissions);
    }

    /**
     * 判断登录状态并跳转
     */
    private void checkLoginState(){
        //判断是否已经登录
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
                String name = pref.getString("phoneNum", "1");
                String password = pref.getString("password", "1");
                if (name.equals("1")) {
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1000);
    }
}
