package com.felix.simplemusic.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.simplemusic.R;
import com.felix.simplemusic.adapter.SongListContentAdapter;
import com.felix.simplemusic.adapter.SongListHotAdapter;
import com.felix.simplemusic.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by chaofei.xue on 2018/8/7.
 */

public class SongListFragment extends BaseFragment {

    @BindView(R.id.recycler_view_content_fragment_song_list)
    RecyclerView rvContent;
    @BindView(R.id.recycler_view_hot_fragment_song_list)
    RecyclerView rvHot;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_song_list, container, false);
    }

    @Override
    public void initView() {
        SongListContentAdapter adapter = new SongListContentAdapter(null);
        GridLayoutManager glManager = new GridLayoutManager(mContext, 1);
        rvContent.setAdapter(adapter);
        rvContent.setLayoutManager(glManager);
        rvContent.setNestedScrollingEnabled(false);

        SongListHotAdapter hotAdapter = new SongListHotAdapter(null);
        LinearLayoutManager llManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false);
        rvHot.setAdapter(hotAdapter);
        rvHot.setLayoutManager(llManager);
        rvHot.setNestedScrollingEnabled(false);
    }

    @Override
    public void initData() {

    }
}
