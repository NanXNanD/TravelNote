package com.nxnd.travelnote.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.fragment.MeFragment;
import com.nxnd.travelnote.fragment.RaidersFragment;
import com.nxnd.travelnote.fragment.TravelNotesFragment;

/**
 * Created by linSir on 17/2/27.主界面的适配器
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = {new TravelNotesFragment(), new RaidersFragment() , new MeFragment()};
    private View view;
    private Context context;

    public HomeFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

}
