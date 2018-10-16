package com.felix.simplemusic.presenter;

import android.content.Context;

import com.felix.simplemusic.bean.ScannerInfoBean;
import com.felix.simplemusic.model.IScannerModel;
import com.felix.simplemusic.model.ScannerModel;
import com.felix.simplemusic.utils.MyLog;
import com.felix.simplemusic.view.IScannerView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
        MyLog.info("presenter scanner all file ...");
        scannerModel.scannerAllFile(new Observer<List<ScannerInfoBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ScannerInfoBean> scannerInfoBeans) {
                MyLog.info("onNext ", scannerInfoBeans.size()+"");
                for (ScannerInfoBean sib : scannerInfoBeans) {
                    MyLog.info(sib.getFineName(), sib.getFilePath());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void scannerAssignFile() {

    }
}
