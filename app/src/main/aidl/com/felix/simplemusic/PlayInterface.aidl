package com.felix.simplemusic;

interface PlayInterface {
    //第一次播放调用
    void playMusic();
    //恢复暂停状态调用
    void rePlayMusic();
    //停止播放调用
    void stopMusic();
    //暂停播放调用
    void pauseMusic();
    //单曲重复调用
    void setLooping(boolean looping);
    //随机播放调用
    void randomPlay();
    //设置播放进度条
    void setSeekTo(int time);
    //设置播放的文件
    void setDataSource(String path);
    //获取歌曲总时长
    int getTotalTime();

}
