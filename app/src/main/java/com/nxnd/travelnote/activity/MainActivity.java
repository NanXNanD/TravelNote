package com.nxnd.travelnote.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.adapter.HomeFragmentAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

public class MainActivity extends AppCompatActivity {

    private HomeFragmentAdapter mAdapter;    //适配器
    private QMUIViewPager viewPager;
    private QMUITabSegment tabsegment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        tabsegment = (QMUITabSegment) findViewById(R.id.tabsegment);
        viewPager = (QMUIViewPager) findViewById(R.id.viewpager);
        initviews();


    }

    private void initviews() {
        /**
         * HomeActivity里面的元素,绑定id
         */
        //draw_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mAdapter = new HomeFragmentAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setSwipeable(false);

        tabsegment.setHasIndicator(false);  //是否需要显示indicator
//        tabsegment.setDefaultNormalColor(QMUIResHelper.getAttrColor(this, R.color.qmui_config_color_gray_3));    //设置tab正常下的颜色
//        tabsegment.setDefaultSelectedColor(QMUIResHelper.getAttrColor(this, R.color.colorPrimary));
        QMUITabSegment.Tab home = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.ic_home),
                null,
                "主页", true
        );
        QMUITabSegment.Tab edit = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.ic_edit),
                null,
                "记录", true
        );
        QMUITabSegment.Tab me = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.mipmap.ic_me),
                null,
                "我的", true
        );
        tabsegment.addTab(home);
        tabsegment.addTab(edit);
        tabsegment.addTab(me);
        tabsegment.setupWithViewPager(viewPager, false);

//
//        user.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,MeActivity.class);
//                startActivity(intent);
//            }
//        });
    }

}
