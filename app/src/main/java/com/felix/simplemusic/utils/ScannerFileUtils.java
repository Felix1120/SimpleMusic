package com.felix.simplemusic.utils;

import com.felix.simplemusic.bean.ScannerInfoBean;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by chaofei.xue on 2018/10/15.
 */

public class ScannerFileUtils {

    public final static int SCANNER_ALL = 0;
    public final static int SCANNER_ASSIGN = 1;
    private Observer<List> observer;

    public ScannerFileUtils(Observer<List> observer) {
        this.observer = observer;
    }

    public List<ScannerInfoBean> sacnnerFile(int flag, File file) {
        List<ScannerInfoBean> lists = new ArrayList<>();
        if (flag == SCANNER_ALL) {
            getFilePath(lists, flag, file);
        } else if (flag == SCANNER_ASSIGN) {
            getFilePath(lists, flag, file);
        }
        return lists;
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
                        lists.add(sif);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    // 继续递归搜索子目录
                    // 如果注释 则只搜索当前目录
                    if (flag == SCANNER_ALL) {
                        getFilePath(lists, flag, file);
                    }
                }
                return false;
            }
        });
    }
}
