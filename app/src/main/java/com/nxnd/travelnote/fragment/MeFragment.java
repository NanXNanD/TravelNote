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
import com.nxnd.travelnote.activity.AboutMeActivity;
import com.nxnd.travelnote.activity.MainActivity;
import com.nxnd.travelnote.activity.RegisterActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tao Yujia 我的
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
        QMUICommonListItemView itemPhone = listView.createItemView("手机号");
        itemPhone.setDetailText("11111111111");
        itemPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone));
        QMUICommonListItemView itemWorks = listView.createItemView("我的作品");
        itemWorks.setImageDrawable(getResources().getDrawable(R.drawable.ic_gallery));
        QMUICommonListItemView itemService = listView.createItemView("客服中心");
        itemService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "17736102287");
                intent.setData(data);
                startActivity(intent);
            }
        });
        itemService.setImageDrawable(getResources().getDrawable(R.drawable.ic_online_support));
        QMUICommonListItemView itemAbout = listView.createItemView("关于我们");
        itemAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AboutMeActivity.class);
                startActivity(intent);
            }
        });
        itemAbout.setImageDrawable(getResources().getDrawable(R.drawable.ic_about));
        QMUICommonListItemView itemQuit = listView.createItemView("退出登录");
        itemQuit.setImageDrawable(getResources().getDrawable(R.drawable.ic_left_down2));

        QMUIGroupListView.newSection(getContext())
                .setTitle("")
                .addItemView(itemPhone,null)
                .addItemView(itemWorks,null)
                .addItemView(itemService,null)
                .addItemView(itemAbout,null)
                .addItemView(itemQuit,null)
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
