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
import com.nxnd.travelnote.Url;
import com.nxnd.travelnote.helper.DBHelper;
import com.nxnd.travelnote.service.UserService;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import permison.PermissonUtil;
import permison.listener.PermissionListener;


/**
 * Created by huchuan on 18/10/28.启动页面
 */
public class StartActivity extends AppCompatActivity {

    private QMUITipDialog tipDialog;
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
                final String phoneNum = pref.getString("phoneNum", "1");
                final String password = pref.getString("password", "1");
                if (phoneNum.equals("1")) {
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //登录
                    String url = Url.url_login;
                    RequestParams params = new RequestParams(url);
                    params.addParameter("phoneNum",phoneNum);
                    params.addParameter("password",password);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject res = new JSONObject(result);
                                boolean status = res.getBoolean("success");
                                String info = res.getString("desc");
                                if (status){
                                    Toast.makeText(StartActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                                    //存入用户信息
                                    JSONObject data = res.getJSONObject("data");
                                    UserService.saveUserInfo(StartActivity.this,phoneNum,password,data.getString("username"),data.getString("userImg"));
                                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(StartActivity.this,info,Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(StartActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Log.d("loginerr",ex.toString());
                            Toast.makeText(StartActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCancelled(CancelledException cex) {
                        }
                        @Override
                        public void onFinished() {
                        }
                    });
                }
            }
        }, 1000);
    }
}
