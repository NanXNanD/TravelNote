package com.nxnd.travelnote.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.nxnd.travelnote.R;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.step_topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.step_listview)
    QMUIGroupListView listView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.add_image)
    ImageButton addImage;
    private String date = "";
    private List<Uri> mSelected;
    public static final int REQUEST_CODE_CHOOSE=123;
    public static final int REQUEST_CODE_LOCATION=234;
    private QMUICommonListItemView itemLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        ButterKnife.bind(this);
        initTopBar();
        initTime();
        itemLocation = listView.createItemView("所在位置");
        itemLocation.setDetailText("北京");
        itemLocation.setImageDrawable(getResources().getDrawable(R.drawable.ic_location));
        itemLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent( StepActivity.this,LocationActivity.class);
                startActivityForResult(i, REQUEST_CODE_LOCATION);//打开定位页面
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

    @OnClick(R.id.add_image)
    public void addImage(){
        chooseImage();
    }

    private void chooseImage(){
        Matisse.from(StepActivity.this)
                .choose(MimeType.allOf()) // 选择 mime 的类型
                .maxSelectable(1) // 图片选择的最多数量
                .theme(R.style.Matisse_Zhihu)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Log.i("resultInfo","requestCode"+requestCode+",resultCode"+resultCode);
        switch (requestCode){
            case REQUEST_CODE_CHOOSE:
                if (resultCode == RESULT_OK) {
                    mSelected = Matisse.obtainResult(data);
                    Log.d("Matisse", "mSelected: " + mSelected);
                    imageView.setImageURI(mSelected.get(0));
                    imageView.setVisibility(View.VISIBLE);
                    addImage.setVisibility(View.INVISIBLE);
                }
                break;
            case REQUEST_CODE_LOCATION:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String cityname = bundle.getString("cityname");
                    LatLonPoint latlonpoint = (LatLonPoint) bundle.get("latlonpoint");
                    String snippet = bundle.getString("snippet");
                    String value = bundle.getString("value");
                    String title = bundle.getString("title");
                    String adname = bundle.getString("adname");
                    itemLocation.setDetailText(title);
                    Log.d("backFromLocation", cityname+snippet);
                }
                break;
        }

    }

}
