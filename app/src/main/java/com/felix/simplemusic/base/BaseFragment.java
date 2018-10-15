package com.felix.simplemusic.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.simplemusic.activity.HomeActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import solid.ren.skinlibrary.base.SkinBaseFragment;

/**
 * Created by chaofei.xue on 2018/8/6.
 */

public abstract class BaseFragment extends SkinBaseFragment {

    protected Unbinder binder;
    protected Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initLayout(inflater, container);
        mContext = container.getContext();
        binder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    public abstract View initLayout(LayoutInflater inflater, ViewGroup container);

    public abstract void initView();

    public abstract void initData();

}
