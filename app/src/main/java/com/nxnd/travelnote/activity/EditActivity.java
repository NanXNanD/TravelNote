package com.nxnd.travelnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.adapter.StepAdapter;
import com.nxnd.travelnote.helper.DBHelper;
import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.nxnd.travelnote.service.TravelNotesService;
import com.nxnd.travelnote.util.CommonUtil;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.zhihu.matisse.Matisse;

import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADDSTEP = 317;
    private StepAdapter stepAdapter;

    @BindView(R.id.editA_topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.edit_title)
    TextView title;

    @BindView(R.id.edit_cover)
    ImageView cover;

    @BindView(R.id.edit_changecover)
    QMUIRoundButton changeCover;

    @BindView(R.id.edit_date)
    TextView date;

    @BindView(R.id.step_list)
    ListView stepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        initTopBar();

    }

    private void initTopBar() {
        mTopBar.setTitle("编辑日记");
        final QMUIAlphaImageButton back = mTopBar.addLeftBackImageButton();
        back.setColorFilter(Color.BLACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.initData();
    }

    //初始化数据
    private void initData(){
        TravelNotesModel travelNotesModel = TravelNotesService.getNoteInfo(this);
        //设置标题
        title.setText(travelNotesModel.getTitle());
        //设置封面
        if(travelNotesModel.getCoverUrl()!=null){
            x.image().bind(cover,travelNotesModel.getCoverUrl(), CommonUtil.options);
        }
        date.setText(travelNotesModel.getStartDate());
        //设置Steps
        //获取所有steps
        List<StepModel> steps = DBHelper.getInstance().getAllStep();
        stepAdapter = new StepAdapter(EditActivity.this,R.layout.step_list_item,steps);
        stepList.setAdapter(stepAdapter);
    }

    //修改封面
    @OnClick(R.id.edit_changecover)
    public void onClickChangeCover(){

    }

    //添加足迹
    @OnClick(R.id.add_step)
    public void onClickAddStep(){
        Intent i = new Intent(this,StepActivity.class);
        startActivityForResult(i,this.REQUEST_CODE_ADDSTEP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Log.i("resultInfo", "requestCode" + requestCode + ",resultCode" + resultCode);
        switch (requestCode) {
            case REQUEST_CODE_ADDSTEP:
                if (resultCode == RESULT_OK) {
                    Log.d("editA","backfromaddstep");
//                    List<StepModel> steps = DBHelper.getInstance().getAllStep();

                }
                break;
        }
    }
}
