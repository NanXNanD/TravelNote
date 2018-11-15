package com.nxnd.travelnote.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        itemLocation.setDetailText("北京");
        itemLocation.setImageDrawable(getResources().getDrawable(R.drawable.ic_location));
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
}
