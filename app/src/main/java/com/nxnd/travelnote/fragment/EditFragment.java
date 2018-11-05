package com.nxnd.travelnote.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.squareup.okhttp.Request;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.nxnd.travelnote.activity.RaidersActivity;
import com.nxnd.travelnote.adapter.RaidersAdapter;
import com.nxnd.travelnote.listener.RecyclerItemClickListener;
import com.nxnd.travelnote.model.RaidersModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linSir on 17/3/11.攻略
 */
public class EditFragment extends Fragment {
    private ImageView imageView;
    private RecyclerView recyclerView;
    private RaidersAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<RaidersModel> mLists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }



}
