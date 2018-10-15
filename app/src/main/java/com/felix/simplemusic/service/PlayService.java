package com.felix.simplemusic.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.RemoteViews;

import com.felix.simplemusic.PlayInterface;
import com.felix.simplemusic.R;
import com.felix.simplemusic.utils.MyLog;

import java.io.IOException;

public class PlayService extends Service {


    private MediaPlayer mediaPlayer;

    public PlayService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
        notificationInit();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationInit();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    int i = 0;
                    while (true) {
                        Thread.sleep(1000);
                        MyLog.info(i++ + "");
                    }
                } catch (Exception x) {

                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mediaPlayer = new MediaPlayer();
        //监听播放状态
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
    }

    /**
     * 设置播放数据源
     *
     * @param path 歌曲地址
     */
    private void setDataSource(String path) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setDataSource(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 状态栏通知
     */
    private void notificationInit() {
        NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        //Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel
                    ("1", "felix", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(PlayService.this)
                    .setChannelId("1")
                    .setSmallIcon(R.mipmap.music_notification)
                    .setDefaults(Notification.DEFAULT_ALL);
            RemoteViews remoteViews = new RemoteViews("com.felix.simplemusic"
                    , R.layout.notification_play);
            Notification notification = builder.setContent(remoteViews).build();
            //manager.notify(888, notification);
            startForeground(1, notification);
        } else {
            //Android5.0 -- Android7.0
            Notification.Builder builder = new Notification.Builder(PlayService.this)
                    .setSmallIcon(R.mipmap.music_notification)
                    .setDefaults(Notification.DEFAULT_ALL);
            RemoteViews remoteViews = new RemoteViews("com.felix.simplemusic"
                    , R.layout.notification_play);
            Notification notification = builder.setContent(remoteViews).build();
            //manager.notify(888, notification);
            startForeground(1, notification);
        }
    }

    /**
     * 暴露给其他地方调用的AIDL
     */
    private class MyBinder extends PlayInterface.Stub {

        /**
         * 播放音乐
         */
        @Override
        public void playMusic() throws RemoteException {
            if (mediaPlayer != null) {
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
            }
        }

        /**
         * 恢复播放
         */
        @Override
        public void rePlayMusic() throws RemoteException {
            if(mediaPlayer != null && !mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }

        /**
         * 停止
         */
        @Override
        public void stopMusic() throws RemoteException {
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }

        /**
         * 暂停
         */
        @Override
        public void pauseMusic() throws RemoteException {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }

        /**
         * 单曲循环
         */
        @Override
        public void setLooping(boolean looping) throws RemoteException {
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(looping);
            }
        }

        /**
         * 随机
         */
        @Override
        public void randomPlay() throws RemoteException {

        }

        /**
         * 定位播放
         */
        @Override
        public void setSeekTo(int time) throws RemoteException {
            if(mediaPlayer != null){
                mediaPlayer.seekTo(time);
            }
        }

        /**
         * 设置数据源
         */
        @Override
        public void setDataSource(String path) throws RemoteException {
            PlayService.this.setDataSource(path);
        }

        /**
         * 获取总时间
         */
        @Override
        public int getTotalTime() throws RemoteException {
            if(mediaPlayer != null){
                return mediaPlayer.getDuration();
            }
            return 0;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(100);
        }
        MyLog.info("service kill");
    }
}
