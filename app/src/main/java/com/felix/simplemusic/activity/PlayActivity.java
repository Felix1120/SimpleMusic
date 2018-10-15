package com.felix.simplemusic.activity;

import android.content.Intent;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;

import com.felix.simplemusic.R;
import com.felix.simplemusic.base.BaseActivity;
import com.felix.simplemusic.service.PlayService;

import butterknife.BindView;

/**
 * Created by chaofei.xue on 2018/8/9.
 */

public class PlayActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.img_close_activity_play)
    ImageView imgClose;
    @Override
    public int initLayout() {
        return R.layout.activity_paly;
    }

    @Override
    public void initView() {
        isStatusBarWrite = false;
        Transition transition = TransitionInflater.from(mContext)
                .inflateTransition(R.transition.slide);
        getWindow().setExitTransition(transition);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);
    }

    @Override
    public void initData() {
        imgClose.setOnClickListener(this);
        startService(new Intent(mContext, PlayService.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_close_activity_play:
                finish();
                break;
        }
    }
}
