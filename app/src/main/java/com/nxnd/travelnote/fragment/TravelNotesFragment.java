package com.nxnd.travelnote.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.nxnd.travelnote.activity.NoteDetailActivity;
import com.nxnd.travelnote.activity.StepActivity;
import com.nxnd.travelnote.activity.TravelNotesActivity;
//import com.nxnd.travelnote.adapter.TravelNotesAdapter;
//import com.nxnd.travelnote.listener.RecyclerItemClickListener;
import com.nxnd.travelnote.adapter.NoteAdapter;
import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

/**
 * Created by huchuan 游记
 */
public class TravelNotesFragment extends Fragment {


    private LinearLayoutManager linearLayoutManager;
    private List<TravelNotesModel> notes;
    private NoteAdapter adapter;
    @BindView(R.id.me_topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.notes_list)
    RecyclerView notesList;
    @BindView(R.id.pull_to_refresh)
    QMUIPullRefreshLayout qmuiPullRefreshLayout;
    private QMUITipDialog tipDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel_notes, container, false);
        ButterKnife.bind(this,view);
        initTopBar();
        initData();
        qmuiPullRefreshLayout.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                qmuiPullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.removeAll();
                        qmuiPullRefreshLayout.finishRefresh();
                        getData();
                    }
                }, 500);

            }
        });
        return view;
    }


    private void initTopBar() {
        mTopBar.setTitle("热门游记");
    }

    private void initData(){

        notes = new ArrayList<TravelNotesModel>();
        //设置布局管理器
        linearLayoutManager = new LinearLayoutManager(getContext());
        notesList.setLayoutManager(linearLayoutManager);
        //设置adapter
        adapter = new NoteAdapter(notes,getContext());
        //设置点击监听
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("itemIndex",i+"");
                TravelNotesModel model = adapter.getItemByIndex(i);
                Intent intent =new Intent(getActivity(), NoteDetailActivity.class);
                intent.putExtra("noteId",model.getNoteId());
                intent.putExtra("userId",model.getUserId());
                intent.putExtra("title",model.getTitle());
                intent.putExtra("coverUrl",model.getCoverUrl());
                intent.putExtra("startDate",model.getStartDate());
                intent.putExtra("location",model.getLocation());
                intent.putExtra("username",model.getUserName());
                intent.putExtra("userImage",model.getUserImage());
                intent.putExtra("viewNum",model.getViewNum());
                startActivity(intent);
            }
        });
        notesList.setAdapter(adapter);
        //设置动画
        notesList.setItemAnimator(new DefaultItemAnimator());
        getData();

    }

    private void getData(){
        RequestParams params = new RequestParams(Url.url_get_note);
        params.addParameter("page",1);
        params.addParameter("number",50);

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
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject note = data.getJSONObject(i);
                            TravelNotesModel travelNotesModel = new TravelNotesModel(
                                    note.getString("noteId"),
                                    note.getString("userId"),
                                    note.getString("title"),
                                    note.getString("coverUrl"),
                                    note.getString("startDate"),
                                    note.getString("location"),
                                    note.getString("username"),
                                    note.optString("userImage"),
                                    note.getInt("viewNum")
                            );
                            adapter.addItem(adapter.getItemCount(), travelNotesModel);
                        }

                    }else {
                        Toast.makeText(getContext(),info,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    tipDialog.dismiss();
                    Log.d("jsonErr",e.toString());
                    Toast.makeText(getContext(),"获取日记失败",Toast.LENGTH_LONG).show();
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
