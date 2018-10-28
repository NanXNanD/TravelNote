package com.nxnd.travelnote.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.squareup.okhttp.Request;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by linSir on 17/3/11.注册界面
 */
public class RegisterActivity extends AppCompatActivity {


    @BindView(R.id.topbar) QMUITopBar mTopBar;
    final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //初始化状态栏
        initTopBar();
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTopBar.setTitle("注册账号");
    }

//    @OnClick(R.id.ac_register_register)
//    public void onClickRegister() {
//        if (userPhone.getText().toString().length() != 11) {
//            Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//
//        String url = Url.url + "register";
//        OkHttpUtils
//                .get()
//                .url(url)
//                .addParams("phone", userPhone.getText().toString().trim())
//                .addParams("pwd", pwd.getText().toString().trim())
//                .addParams("name", userName.getText().toString().trim())
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
//                        Log.i("lin", "---lin's log--->" + e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        if (response.equals("100")) {
//                            Toast.makeText(RegisterActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
//                            finish();
//                        } else if (response.equals("101")) {
//                            Toast.makeText(RegisterActivity.this, "注册失败--用户已存在", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//
//
//    }

}
