package com.felix.simplemusic.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.felix.simplemusic.R;
import com.felix.simplemusic.activity.HomeActivity;
import com.felix.simplemusic.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import solid.ren.skinlibrary.base.SkinBaseActivity;

/**
 * Created by chaofei.xue on 2018/8/6.
 */

public abstract class BaseActivity extends SkinBaseActivity {

    //上下文对象
    protected Context mContext;
    //绑定ButterKnife
    protected Unbinder binder;
    //记录所有activity
    protected static List<BaseActivity> activities = new ArrayList<>();
    //白色状态栏为true 否则为false
    protected boolean isStatusBarWrite = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用动画切换activity
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(initLayout());
        binder = ButterKnife.bind(this);
        mContext = getApplicationContext();
        initView();
        initData();
        activities.add(this);
        //强制横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //状态栏白底黑字的适配
        if (isStatusBarWrite && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                getResources().getColor(R.color.colorPrimary) ==
                        getResources().getColor(R.color.color_primary_write)) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.color_text_write);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    /**
     * 获取布局
     *
     * @return 布局ID
     */
    public abstract int initLayout();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 关闭所有activity
     */
    protected void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    /**
     * 防止字体大小随系统改变
     *
     * @return 设置后的resources
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        return resources;
    }
}
