package com.felix.simplemusic.activity;


import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.felix.simplemusic.R;
import com.felix.simplemusic.base.BaseActivity;
import com.felix.simplemusic.presenter.IScannerPresenter;
import com.felix.simplemusic.presenter.ScannerPresenter;
import com.felix.simplemusic.view.IScannerView;

import butterknife.BindView;

public class ScannerActivity extends BaseActivity implements IScannerView, View.OnClickListener{

    @BindView(R.id.img_close_scanner_activity)
    ImageView imgClose;
    @BindView(R.id.btn_all_scanner_activity)
    Button btnAllScanner;
    @BindView(R.id.btn_assign_scanner_activity)
    Button btnAssignScanner;

    private IScannerPresenter presenter;

    @Override
    public int initLayout() {
        return R.layout.activity_scanner;
    }

    @Override
    public void initView() {
        //添加进入 退出动画
        Transition transition = TransitionInflater.from(mContext)
                .inflateTransition(R.transition.fade);
        getWindow().setExitTransition(transition);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);

        //监听点击事件
        imgClose.setOnClickListener(this);
        btnAllScanner.setOnClickListener(this);
        btnAssignScanner.setOnClickListener(this);

        //绑定presenter
        presenter = new ScannerPresenter(this, mContext);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_close_scanner_activity:
                finish();
                break;
            case R.id.btn_all_scanner_activity:
                presenter.scannerAllFile();
                break;
            case R.id.btn_assign_scanner_activity:
                break;
        }
    }
}
