package com.nxnd.travelnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.nxnd.travelnote.adapter.StepAdapter;
import com.nxnd.travelnote.helper.DBHelper;
import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.nxnd.travelnote.service.TravelNotesService;
import com.nxnd.travelnote.util.CommonUtil;
import com.nxnd.travelnote.util.ImageLoader;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogMenuItemView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int REQUEST_CODE_ADDSTEP = 317;
    private static final int REQUEST_CODE_CHOOSE = 318;
    private StepAdapter stepAdapter;
    private List<StepModel> steps;
    private QMUITipDialog tipDialog;

    @BindView(R.id.editA_topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.edit_title)
    TextView title;

    @BindView(R.id.edit_cover)
    ImageView cover;

    @BindView(R.id.edit_date)
    TextView date;

    @BindView(R.id.edit_location)
    TextView location;

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
        mTopBar.addRightImageButton(R.drawable.ic_more,R.integer.setting_id).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showSimpleBottomSheetGrid();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.initBaseData();
        this.initData();
    }

    //初始化基本数据
    private void initBaseData(){
        TravelNotesModel travelNotesModel = TravelNotesService.getNoteInfo(this);

        //设置标题
        title.setText(travelNotesModel.getTitle());
        //设置封面
        if(travelNotesModel.getCoverUrl()!=null){
            x.image().bind(cover,CommonUtil.getImageUrl(travelNotesModel.getCoverUrl()), CommonUtil.options);
        }
        //设置时间
        if(travelNotesModel.getStartDate()!=null){
            date.setText(travelNotesModel.getStartDate());
        }
        //设置地点
        if(travelNotesModel.getLocation()!=null){
            location.setText(travelNotesModel.getLocation());
        }

    }
    //初始化步骤数据
    private void initData(){

        //设置Steps
        //获取所有steps
        steps = DBHelper.getInstance().getAllStep();
        if (steps==null){
            steps = new ArrayList<StepModel>();
        }
        stepAdapter = new StepAdapter(EditActivity.this,R.layout.step_list_item,steps);
        stepList.setAdapter(stepAdapter);
        stepList.setOnItemClickListener(this);
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
            case REQUEST_CODE_CHOOSE:
                if (resultCode == RESULT_OK) {
                    Uri uri = Matisse.obtainResult(data).get(0);
                    tipDialog = new QMUITipDialog.Builder(EditActivity.this)
                            .setTipWord("上传图片中")
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                            .create();
                    tipDialog.show();
                    ImageLoader.uploadImage(EditActivity.this, uri, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject res = new JSONObject(result);
                                boolean status = res.getBoolean("success");
                                String info = res.getString("desc");
                                if (status) {
                                    //上传成功
                                    JSONObject data = res.getJSONObject("data");
                                    String imgUrl = data.getString("url");
                                    TravelNotesService.changeInfo(EditActivity.this,"coverUrl",imgUrl);
                                    initBaseData();
                                    tipDialog.dismiss();
                                    Toast.makeText(EditActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                                } else {
                                    tipDialog.dismiss();
                                    Toast.makeText(EditActivity.this, info, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                Log.d("imgerr", e.toString());
                                tipDialog.dismiss();
                                Toast.makeText(EditActivity.this, "图片上传失败", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Log.d("imgerr", ex.toString());
                            Toast.makeText(EditActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                            tipDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {
                        }

                        @Override
                        public void onFinished() {
                        }
                    });

                }
                break;
        }
    }

    //生成底部表格
    private void showSimpleBottomSheetGrid() {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        final int TAG_SHARE_LOCAL = 4;
        final int TAG_UPLOAD = 5;
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(EditActivity.this);
        builder.addItem(R.drawable.ic_edit_title, "修改标题", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.ic_change_img, "设置封面", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.ic_change_location, "设置地点", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.ic_change_time, "修改时间", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.ic_delete, "删除", TAG_SHARE_LOCAL, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .addItem(R.drawable.ic_note_upload, "发布", TAG_UPLOAD, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        switch (tag) {
                            case TAG_SHARE_WECHAT_FRIEND:
                                final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(EditActivity.this);
                                builder.setTitle("日记标题")
                                        .setPlaceholder("在此输入日记标题")
                                        .setInputType(InputType.TYPE_CLASS_TEXT)
                                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                                            @Override
                                            public void onClick(QMUIDialog dialog, int index) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                                            @Override
                                            public void onClick(QMUIDialog dialog, int index) {
                                                String text = builder.getEditText().getText().toString();
                                                TravelNotesService.changeInfo(EditActivity.this,"title",text);
                                                initBaseData();
                                                dialog.dismiss();
                                            }
                                        })
                                        .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
                                break;
                            case TAG_SHARE_WECHAT_MOMENT:
                                Matisse.from(EditActivity.this)
                                        .choose(MimeType.allOf()) // 选择 mime 的类型
                                        .maxSelectable(1) // 图片选择的最多数量
                                        .theme(R.style.Matisse_Zhihu)
                                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                        .thumbnailScale(0.85f) // 缩略图的比例
                                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                                        .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
                                break;
                            case TAG_SHARE_WEIBO:
                                //TODO 修改地点
                                break;
                            case TAG_SHARE_CHAT:
                                //TODO 修改时间
                                break;
                            case TAG_SHARE_LOCAL:
                                new QMUIDialog.MessageDialogBuilder(EditActivity.this)
                                        .setTitle("标题")
                                        .setMessage("确定要删除吗？")
                                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                                            @Override
                                            public void onClick(QMUIDialog dialog, int index) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                                            @Override
                                            public void onClick(QMUIDialog dialog, int index) {
                                                TravelNotesService.delNote(EditActivity.this);
                                                DBHelper.getInstance().delAllSteps();
                                                Toast.makeText(EditActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                                finish();
                                            }
                                        })
                                        .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
                                break;
                            case TAG_UPLOAD:
                                //TODO 发布
                                tipDialog = new QMUITipDialog.Builder(EditActivity.this)
                                        .setTipWord("上传日记中...")
                                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                                        .create();
                                tipDialog.show();
                                TravelNotesModel travelNotesModel = TravelNotesService.getNoteInfo(EditActivity.this);
                                RequestParams params =new RequestParams(Url.url_add_note);
                                params.addParameter("userId",3);
                                params.addParameter("title",travelNotesModel.getTitle());
                                params.addParameter("coverUrl",travelNotesModel.getCoverUrl());
                                params.addParameter("startDate",travelNotesModel.getStartDate());
                                params.addParameter("location",travelNotesModel.getLocation());
                                x.http().post(params, new Callback.CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        try {
                                            JSONObject res = new JSONObject(result);
                                            boolean status = res.getBoolean("success");
                                            String info = res.getString("desc");
                                            if (status) {
                                                //上传成功
                                                JSONObject data = res.getJSONObject("data");
                                                String noteId = data.getString("noteId");

                                                //开始上传步骤
                                                for (int i = 0;i<steps.size();i++){
                                                    StepModel step = steps.get(i);
                                                    RequestParams params1 = new RequestParams(Url.url_add_step);
                                                    params1.addParameter("noteId",noteId);
                                                    params1.addParameter("index",i);
                                                    params1.addParameter("imageUrl",step.getImageurl());
                                                    params1.addParameter("content",step.getContent());
                                                    params1.addParameter("time",step.getDatetime());
                                                    params1.addParameter("location",step.getLocation());
                                                    params1.addParameter("longitude",step.getLongitude());
                                                    params1.addParameter("latitude",step.getLatitude());
                                                    int num = 0;
                                                    x.http().post(params1, new CommonCallback<String>() {
                                                        @Override
                                                        public void onSuccess(String result) {
                                                            try {
                                                                JSONObject res = new JSONObject(result);
                                                                boolean status = res.getBoolean("success");
                                                                String info = res.getString("desc");
                                                                if (status) {

                                                                } else {
                                                                    tipDialog.dismiss();
                                                                    Toast.makeText(EditActivity.this, info, Toast.LENGTH_LONG).show();
                                                                }
                                                            } catch (JSONException e) {
                                                                Log.d("imgerr", e.toString());
                                                                tipDialog.dismiss();
                                                                Toast.makeText(EditActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                        @Override
                                                        public void onError(Throwable ex, boolean isOnCallback) {
                                                            Log.d("imgerr", ex.toString());
                                                            Toast.makeText(EditActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                                                            tipDialog.dismiss();
                                                        }

                                                        @Override
                                                        public void onCancelled(CancelledException cex) {
                                                        }

                                                        @Override
                                                        public void onFinished() {
                                                        }
                                                    });
                                                }
                                            } else {
                                                tipDialog.dismiss();
                                                Toast.makeText(EditActivity.this, info, Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            Log.d("imgerr", e.toString());
                                            tipDialog.dismiss();
                                            Toast.makeText(EditActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable ex, boolean isOnCallback) {
                                        Log.d("imgerr", ex.toString());
                                        Toast.makeText(EditActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                                        tipDialog.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(CancelledException cex) {
                                    }

                                    @Override
                                    public void onFinished() {
                                    }
                                });
                                break;
                        }
                    }
                }).build().show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //进入step编辑界面
        StepModel step = steps.get(i);
        Log.d("stepDetail", "onItemClick: "+step.getId());
        Intent intent = new Intent(EditActivity.this,StepActivity.class);
        intent.putExtra("stepId",step.getId());
        startActivity(intent);
    }
}
