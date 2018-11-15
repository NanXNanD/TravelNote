package com.nxnd.travelnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 胡川 注册界面
 */
public class RegisterActivity extends AppCompatActivity {


    @BindView(R.id.topbar) QMUITopBar mTopBar;
    final String TAG = getClass().getSimpleName();

    @BindView(R.id.reg_username) EditText username;
    @BindView(R.id.reg_phone) EditText phone;
    @BindView(R.id.reg_password) EditText password;
    @BindView(R.id.reg_verifycode) EditText verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //初始化状态栏
        initTopBar();
    }

    private void initTopBar() {
        QMUIAlphaImageButton back = mTopBar.addLeftBackImageButton();
        back.setColorFilter(Color.BLACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopBar.setTitle("注册账号");
    }

    @OnClick(R.id.button_getverifycode)
    public void getVerifyCode(){
        Toast.makeText(RegisterActivity.this,"已发送验证码", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_register)
    public void onClickRegister() {
        if (phone.getText().toString().length() != 11) {
            Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        //登录
        String url = Url.url_reg;
        RequestParams params = new RequestParams(url);
        params.addParameter("username",username.getText().toString());
        params.addParameter("phoneNum",phone.getText().toString());
        params.addParameter("password",password.getText().toString());
//        params.addParameter("verifyCode",verifyCode);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject res = new JSONObject(result);
                    boolean status = res.getBoolean("status");
                    String info = res.getString("info");
                    if (status){
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                        //存入用户信息
                        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                        //实例化SharedPreferences.Editor对象
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        //用putString的方法保存数据
                        editor.putString("phoneNum", phone.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.putString("username", username.getText().toString());
                        //TODO 用户信息
                        //提交当前数据
                        editor.apply();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this,info,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(RegisterActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
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
