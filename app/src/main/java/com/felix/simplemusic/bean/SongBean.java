package com.felix.simplemusic.bean;

/**
 * Created by chaofei.xue on 2018/8/9.
 */

public class SongBean {

    private String songName;
    private String singer;
    private String totalTime;
    private String album;
    private String quality;
    private String photos;
    private String totalPlay;
    private String lrc;

    public SongBean(String songName, String singer, String totalTime, String album,
                    String quality, String photos, String totalPlay, String lrc) {
        this.songName = songName;
        this.singer = singer;
        this.totalTime = totalTime;
        this.album = album;
        this.quality = quality;
        this.photos = photos;
        this.totalPlay = totalPlay;
        this.lrc = lrc;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getTotalPlay() {
        return totalPlay;
    }

    public void setTotalPlay(String totalPlay) {
        this.totalPlay = totalPlay;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }
}
