package com.nxnd.travelnote.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.helper.DBHelper;
import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.nxnd.travelnote.service.TravelNotesService;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.zhihu.matisse.Matisse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADDSTEP = 317;

    @BindView(R.id.editA_topbar)
    QMUITopBar topBar;

    @BindView(R.id.edit_title)
    TextView title;

    @BindView(R.id.edit_cover)
    ImageView cover;

    @BindView(R.id.edit_changecover)
    QMUIRoundButton changeCover;

    @BindView(R.id.edit_date)
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        topBar.setTitle("编写日记");
        topBar.addLeftBackImageButton();
        this.initData();
    }

    //初始化数据
    private void initData(){
        TravelNotesModel travelNotesModel = TravelNotesService.getNoteInfo(this);
        //设置标题
        title.setText(travelNotesModel.getTitle());
        //设置封面
        //TODO
        date.setText(travelNotesModel.getStartDate());
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
                    List<StepModel> steps = DBHelper.getInstance().getAllStep();
                    if(steps!=null){
                        Log.d("stepsize", String.valueOf(steps));
                    }else {
                        Log.d("stepsize", "空");
                    }
                }
                break;
        }
    }
}
