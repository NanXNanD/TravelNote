package com.nxnd.travelnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nxnd.travelnote.service.UserService;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.HttpMethod;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Created by huchuan 登录
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_phone) EditText userName;
    @BindView(R.id.login_password) EditText userPwd;
    @BindView(R.id.login_login) Button login;

    private QMUITipDialog tipDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_login)
    public void onCLickLogin() {
        if (userName.getText().toString().length() != 11) {
            Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPwd.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "用户密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        loginPost(userName.getText().toString(),userPwd.getText().toString().trim());
    }

    private void loginPost(String phoneNum,String password){
        tipDialog = new QMUITipDialog.Builder(LoginActivity.this)
                .setTipWord("登录中")
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .create();
        tipDialog.show();
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
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                        //存入用户信息
                        JSONObject data = res.getJSONObject("data");
                        UserService.saveUserInfo(LoginActivity.this,userName.getText().toString(),userPwd.getText().toString(),data.getString("username"),data.getString("userImg"));
                        tipDialog.dismiss();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        tipDialog.dismiss();
                        Toast.makeText(LoginActivity.this,info,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    tipDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("loginerr",ex.toString());
                tipDialog.dismiss();
                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    @OnClick(R.id.login_reg)
    public void onCLickRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
