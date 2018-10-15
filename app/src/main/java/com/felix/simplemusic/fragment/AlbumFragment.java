package com.felix.simplemusic.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.simplemusic.R;
import com.felix.simplemusic.activity.HomeActivity;
import com.felix.simplemusic.adapter.AlbumAdapter;
import com.felix.simplemusic.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by chaofei.xue on 2018/8/7.
 */

public class AlbumFragment extends BaseFragment {
    @BindView(R.id.recycler_view_fragment_album)
    RecyclerView rvAlbum;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

    @Override
    public void initView() {
        AlbumAdapter adapter = new AlbumAdapter(null);
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);

        rvAlbum.setAdapter(adapter);
        rvAlbum.setLayoutManager(manager);
    }

    @Override
    public void initData() {

    }

}
