package com.felix.simplemusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.felix.simplemusic.utils.MyLog;

import java.util.List;

/**
 * Created by Felix on 2018/8/7.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> lists;

    public HomeViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> lists) {
        super(fragmentManager);
        this.lists = lists;
    }

    @Override
    public Fragment getItem(int position) {
        MyLog.info("HomeViewPagerAdapter", "position = " + position);
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
