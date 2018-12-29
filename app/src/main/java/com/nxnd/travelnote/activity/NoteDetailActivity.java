package com.nxnd.travelnote.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.nxnd.travelnote.adapter.StepCardAdapter;
import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.nxnd.travelnote.util.CommonUtil;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class NoteDetailActivity extends AppCompatActivity {


    private static final String TAG = "CollapsingTopBarLayout";

    List<StepModel> steps;

    @BindView(R.id.steps_card_list)
    ListView listView;
    @BindView(R.id.collapsing_topbar_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;
    @BindView(R.id.detail_topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.detail_cover)
    ImageView cover;

    //数据
    private String id;
    private String title;
    private String coverUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);
        initTopBar();
        Intent intent = getIntent();
        id = intent.getStringExtra("noteId");
        title = intent.getStringExtra("title");
        coverUrl = intent.getStringExtra("coverUrl");
        //设置状态栏
        mCollapsingTopBarLayout.setTitle(title);
        //设置封面
        x.image().bind(cover, CommonUtil.getImageUrl(coverUrl),CommonUtil.options);
        initSteps();
        mCollapsingTopBarLayout.setScrimUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "scrim: " + animation.getAnimatedValue());
            }
        });

    }


    private void initTopBar() {
        final QMUIAlphaImageButton back = mTopBar.addLeftBackImageButton();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void initSteps(){
        steps = new ArrayList<StepModel>();
        RequestParams params = new RequestParams(Url.url_get_deatil);
        params.addParameter("noteId",id);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject res = new JSONObject(result);
                    boolean status = res.getBoolean("success");
                    String info = res.getString("desc");
                    if (status){
                        JSONArray data = res.getJSONArray("data");
                        //遍历
                        for (int i=0;i<data.length();i++){
                            JSONObject step = data.getJSONObject(i);
                            StepModel stepModel = new StepModel(
                                    i,//TODO 改过来
                                    step.getString("imageUrl"),
                                    step.getString("content"),
                                    step.getString("time"),//TODO 时间日期格式
                                    step.getString("location"),
                                    new Float(0.0),//TODO 改过来
                                    new Float(0.0)
                            );
                            steps.add(stepModel);
                        }
                        StepCardAdapter stepCardAdapter = new StepCardAdapter(NoteDetailActivity.this,R.layout.step_card,steps);
                        listView.setAdapter(stepCardAdapter);
                    }else {
                        Toast.makeText(NoteDetailActivity.this,info,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.d("jsonErr",e.toString());
                    Toast.makeText(NoteDetailActivity.this,"获取日记信息失败",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
