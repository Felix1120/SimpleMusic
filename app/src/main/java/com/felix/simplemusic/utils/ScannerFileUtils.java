package com.felix.simplemusic.utils;

import com.felix.simplemusic.bean.ScannerInfoBean;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chaofei.xue on 2018/10/15.
 */

public class ScannerFileUtils {

    public final static int SCANNER_ALL = 0;
    public final static int SCANNER_ASSIGN = 1;
    private Observer<List<ScannerInfoBean>> observer;

    public ScannerFileUtils(Observer<List<ScannerInfoBean>> observer) {
        this.observer = observer;
    }

    public void scannerFile(final int flag, final File file) {
        Observable<List<ScannerInfoBean>> observable = Observable.create(new ObservableOnSubscribe<List<ScannerInfoBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ScannerInfoBean>> e) throws Exception {
                List<ScannerInfoBean> lists = new ArrayList<>();
                if (flag == SCANNER_ALL) {
                    MyLog.info("ScannerFileUtils scanner all start...");
                    getFilePath(lists, flag, file);
                    MyLog.info("ScannerFileUtils scanner all end...");
                } else if (flag == SCANNER_ASSIGN) {
                    getFilePath(lists, flag, file);
                }
                e.onNext(lists);
                e.onComplete();
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    private void getFilePath(final List<ScannerInfoBean> lists, final int flag, File file) {
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp3")
                            || name.equalsIgnoreCase(".ape")
                            || name.equalsIgnoreCase(".flac")
                            || name.equalsIgnoreCase(".wav")) {
                        // 得到文件路径
                        String filePath = file.getAbsolutePath();
                        // 得到文件名称
                        String fileName = file.getName();
                        ScannerInfoBean sif = new ScannerInfoBean(filePath, fileName);
                        MyLog.info(fileName, filePath);
                        lists.add(sif);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    // 继续递归搜索子目录
                    if (flag == SCANNER_ALL) {
                        getFilePath(lists, flag, file);
                    }
                }
                return false;
            }
        });
    }
}
