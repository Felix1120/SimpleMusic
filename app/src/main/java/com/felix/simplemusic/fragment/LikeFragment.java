package com.felix.simplemusic.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.simplemusic.R;
import com.felix.simplemusic.adapter.LikeAdapter;
import com.felix.simplemusic.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by chaofei.xue on 2018/8/7.
 */

public class LikeFragment extends BaseFragment {
    @BindView(R.id.recycler_view_fragment_like)
    RecyclerView rvLike;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_like, container, false);
    }

    @Override
    public void initView() {
        LikeAdapter adapter = new LikeAdapter(null);
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        rvLike.setAdapter(adapter);
        rvLike.setLayoutManager(manager);
    }

    @Override
    public void initData() {

    }
}
