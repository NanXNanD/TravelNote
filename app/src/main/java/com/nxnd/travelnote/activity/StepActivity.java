package com.nxnd.travelnote.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nxnd.travelnote.R;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.step_topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.step_listview)
    QMUIGroupListView listView;

    private String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        ButterKnife.bind(this);
        initTopBar();
        initTime();
        QMUICommonListItemView itemLocation = listView.createItemView("所在位置");
//       if(getIntent().getStringExtra("value").equals("1")||getIntent()!=null)//传回定位数据
//            itemLocation.setDetailText("北京");
//            itemLocation.setDetailText(getIntent().getStringExtra("snippet")+getIntent().getStringExtra("latlonpoint"));//位置

        itemLocation.setImageDrawable(getResources().getDrawable(R.drawable.ic_location));
        itemLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent( StepActivity.this,LocationActivity.class);
                startActivity(i);//打开定位页面
            }
        });
        QMUICommonListItemView itemTime = listView.createItemView("记录时间");
        itemTime.setDetailText(date);
        itemTime.setImageDrawable(getResources().getDrawable(R.drawable.ic_time));
        QMUIGroupListView.newSection(this)
                .setTitle("")
                .addItemView(itemLocation,null)
                .addItemView(itemTime,null)
                .setUseDefaultTitleIfNone(false)
                .setUseTitleViewForSectionSpace(false)
                .addTo(listView);
    }


    private void initTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        date = format.format(calendar.getTime());
    }

    private void initTopBar() {
        mTopBar.setTitle("编辑足迹");
        final QMUIAlphaImageButton back = mTopBar.addLeftBackImageButton();
        back.setColorFilter(Color.BLACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepActivity.this.finish();
            }
        });
        //保存
        mTopBar.addRightTextButton(R.string.step_save,R.integer.save).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
//                Intent intent = new Intent(getContext(),RegisterActivity.class);
//                startActivity(intent);
            }
        });
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case 200://刚才的识别码
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){//用户同意权限,执行我们的操作
//                    // startLocaion();//开始定位
//                }else{//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
//                    Toast.makeText(MainActivity.this,"未开启定位权限,请手动到设置去开启权限",Toast.LENGTH_LONG).show();
//                }
//                break;
//            default:break;
//        }
}
