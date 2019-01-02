package com.nxnd.travelnote.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.activity.AboutMeActivity;
import com.nxnd.travelnote.activity.LoginActivity;
import com.nxnd.travelnote.activity.MainActivity;
import com.nxnd.travelnote.activity.RegisterActivity;
import com.nxnd.travelnote.activity.StepActivity;
import com.nxnd.travelnote.service.UserService;
import com.nxnd.travelnote.util.CommonUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tao Yujia 我的
 */
public class MeFragment extends Fragment {

    @BindView(R.id.me_topbar) QMUITopBar mTopBar;
    @BindView(R.id.me_listview) QMUIGroupListView listView;
    @BindView(R.id.fr_me_user_img)
    QMUIRadiusImageView img;
    @BindView(R.id.fr_me_user_name)
    TextView username;
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
        //用户信息
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
        username.setText(sharedPreferences.getString("username","新用户"));
        Glide.with(getActivity())
                .load(sharedPreferences.getString("userImg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546184554110&di=268f8cf0c88e8d286c482184724856ca&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic3%2Fcover%2F01%2F69%2F80%2F595f67bf2026f_610.jpg")).asBitmap()
                .into(img);

        //listView
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
                Uri data = Uri.parse("tel:" + "17600660263");
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
        itemQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空信息
                UserService.clearUserInfo(getActivity());
                //返回登录
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
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
//                Intent intent = new Intent(getContext(),StepActivity.class);
//                startActivity(intent);
            }
        });
    }



}
