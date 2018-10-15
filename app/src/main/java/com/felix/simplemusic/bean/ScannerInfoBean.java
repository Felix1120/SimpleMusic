package com.felix.simplemusic.bean;

/**
 * Created by chaofei.xue on 2018/10/15.
 */

public class ScannerInfoBean {
    private String filePath;
    private String fineName;

    public ScannerInfoBean(String filePath, String fineName) {
        this.filePath = filePath;
        this.fineName = fineName;
    }

    public ScannerInfoBean() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFineName() {
        return fineName;
    }

    public void setFineName(String fineName) {
        this.fineName = fineName;
    }
}
