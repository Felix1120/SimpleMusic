package com.felix.simplemusic.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.simplemusic.R;
import com.felix.simplemusic.activity.HomeActivity;
import com.felix.simplemusic.adapter.SingerAdapter;
import com.felix.simplemusic.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by chaofei.xue on 2018/8/7.
 */

public class SingerFragment extends BaseFragment {
    @BindView(R.id.recycler_view_fragment_singer)
    RecyclerView rvSinger;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_singer, container, false);
    }

    @Override
    public void initView() {
        SingerAdapter adapter = new SingerAdapter(null);
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        rvSinger.setAdapter(adapter);
        rvSinger.setLayoutManager(manager);
    }

    @Override
    public void initData() {

    }
}
