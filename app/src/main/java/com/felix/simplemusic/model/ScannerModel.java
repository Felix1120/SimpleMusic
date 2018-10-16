package com.felix.simplemusic.model;

import android.os.Environment;

import com.felix.simplemusic.bean.ScannerInfoBean;
import com.felix.simplemusic.utils.MyLog;
import com.felix.simplemusic.utils.ScannerFileUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by chaofei.xue on 2018/10/15.
 */

public class ScannerModel implements IScannerModel {

    public ScannerModel() {

    }

    @Override
    public void scannerAllFile(Observer<List<ScannerInfoBean>> observer) {
        ScannerFileUtils sfu = new ScannerFileUtils(observer);
        MyLog.info(getSDCardPath().getPath());
        sfu.scannerFile(ScannerFileUtils.SCANNER_ALL, getSDCardPath());
    }

    public File getSDCardPath(){
        return Environment.getExternalStorageDirectory();
    }
}
