package com.felix.simplemusic.presenter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.Message;

import com.felix.simplemusic.bean.ScannerInfoBean;
import com.felix.simplemusic.model.IScannerModel;
import com.felix.simplemusic.model.ScannerModel;
import com.felix.simplemusic.utils.MyLog;
import com.felix.simplemusic.utils.ScannerFileUtils;
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
        scannerView.search(ScannerFileUtils.SCANNER_ALL);
        scannerModel.scannerAllFile(new Observer<List<ScannerInfoBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ScannerInfoBean> scannerInfoBeans) {
                MyLog.info("onNext ", scannerInfoBeans.size()+"");
                for (ScannerInfoBean sib : scannerInfoBeans) {
                    MyLog.info(sib.getFineName(), sib.getFilePath());
                    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                    try {
                        mmr.setDataSource(sib.getFilePath());
                        MyLog.info(sib.getFineName(), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
                        MyLog.info(sib.getFineName(), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
                        MyLog.info(sib.getFineName(), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
                        MyLog.info(sib.getFineName(), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE));
                        MyLog.info(sib.getFineName(), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
                        MyLog.info(sib.getFineName(), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE));
                    }catch (Exception e){
                        MyLog.info("illegal music file");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                scannerView.noSearch(ScannerFileUtils.SCANNER_ALL);
            }
        });
    }

    @Override
    public void scannerAssignFile() {

    }
}
