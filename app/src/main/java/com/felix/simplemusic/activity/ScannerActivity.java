package com.felix.simplemusic.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.felix.simplemusic.R;
import com.felix.simplemusic.base.BaseActivity;
import com.felix.simplemusic.presenter.IScannerPresenter;
import com.felix.simplemusic.presenter.ScannerPresenter;
import com.felix.simplemusic.utils.MyLog;
import com.felix.simplemusic.utils.ScannerFileUtils;
import com.felix.simplemusic.view.IScannerView;

import butterknife.BindView;

public class ScannerActivity extends BaseActivity implements IScannerView, View.OnClickListener {

    @BindView(R.id.img_close_scanner_activity)
    ImageView imgClose;
    @BindView(R.id.btn_all_scanner_activity)
    Button btnAllScanner;
    @BindView(R.id.btn_assign_scanner_activity)
    Button btnAssignScanner;
    @BindView(R.id.img_search_scanner_activity)
    ImageView imgSearch;

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
        //动态申请文件读写权限
        if (ContextCompat.checkSelfPermission(ScannerActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ScannerActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_close_scanner_activity:
                finish();
                break;
            case R.id.btn_all_scanner_activity:
                presenter.scannerAllFile();
                MyLog.info("all scanner click...");
                break;
            case R.id.btn_assign_scanner_activity:
                break;
        }
    }


    @Override
    public void search(int flag) {
        if (flag == ScannerFileUtils.SCANNER_ALL) {
            btnAllScanner.setEnabled(false);
            btnAllScanner.setText(R.string.stop_scanner);
            imgSearch.setVisibility(View.VISIBLE);
        } else if (flag == ScannerFileUtils.SCANNER_ASSIGN) {

        }
    }

    @Override
    public void noSearch(int flag) {
        if (flag == ScannerFileUtils.SCANNER_ALL) {
            imgSearch.setVisibility(View.GONE);
            btnAllScanner.setEnabled(true);
            btnAllScanner.setText(R.string.all_scanner);
        } else if (flag == ScannerFileUtils.SCANNER_ASSIGN) {

        }
    }

    @Override
    public void messageShow() {

    }
}
