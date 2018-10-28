package com.nxnd.travelnote.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.activity.MainActivity;
import com.nxnd.travelnote.activity.RegisterActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huchuan 我的
 */
public class MeFragment extends Fragment {

    @BindView(R.id.me_topbar) QMUITopBar mTopBar;
    @BindView(R.id.me_listview) QMUIGroupListView listView;
    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this,view);
        QMUIGroupListView.newSection(getContext())
                .setTitle("")
                .addItemView(listView.createItemView("item 1"), new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Toast.makeText(getContext(), "section 1 item 1", Toast.LENGTH_SHORT).show();
                      }
                  })
                .setUseDefaultTitleIfNone(false)
                .setUseTitleViewForSectionSpace(false)
                .addTo(listView);
        initTopBar();
        return view;
    }

    private void initTopBar() {
        mTopBar.setTitle("我的");
        mTopBar.addRightImageButton(R.mipmap.ic_setting,R.integer.setting_id).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(getContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



}
