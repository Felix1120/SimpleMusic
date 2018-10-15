package com.felix.simplemusic.presenter;

import android.content.Context;

import com.felix.simplemusic.model.IScannerModel;
import com.felix.simplemusic.model.ScannerModel;
import com.felix.simplemusic.view.IScannerView;

/**
 * Created by chaofei.xue on 2018/10/15.
 */

public class ScannerPresenter implements IScannerPresenter {

    private IScannerModel scannerModel;
    private IScannerView scannerView;
    private Context mContext;

    public ScannerPresenter(IScannerView scannerView, Context mContext) {
        scannerModel = new ScannerModel();
        this.scannerView = scannerView;
        this.mContext = mContext;
    }


    @Override
    public void scannerAllFile() {

    }

    @Override
    public void scannerAssignFile() {

    }
}
