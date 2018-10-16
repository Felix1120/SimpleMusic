package com.felix.simplemusic.model;

import com.felix.simplemusic.bean.ScannerInfoBean;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by chaofei.xue on 2018/10/15.
 */

public interface IScannerModel {
    void scannerAllFile(Observer<List<ScannerInfoBean>> observer);
}
