package com.felix.simplemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.simplemusic.R;
import com.felix.simplemusic.bean.SongBean;

import java.util.List;

/**
 * Created by chaofei.xue on 2018/8/9.
 */

public class SongListHotAdapter extends RecyclerView.Adapter<SongListHotAdapter.ViewHolder> {

    private List<SongBean> lists;
    private Context mContext;

    public SongListHotAdapter(List<SongBean> lists) {
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_song_list_first_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
