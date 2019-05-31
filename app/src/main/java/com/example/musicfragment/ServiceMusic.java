package com.example.musicfragment;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import com.example.musicfragment.InterfaceCommon.ClickButtonMusic;
import com.example.musicfragment.model.Music;
import com.example.musicfragment.InterfaceCommon.ServiceMusiCallBack;

import java.io.IOException;

public class ServiceMusic extends Service implements MediaPlayer.OnCompletionListener, ClickButtonMusic{
    Thread threadNhac;
    MediaPlayer mPlayer;
    Music music=null;
    ServiceMusiCallBack serviceMusiCallBack;
    CountDownTimer countDownTimer;
    public ServiceMusic(  ServiceMusiCallBack serviceMusiCallBack) {
        this.serviceMusiCallBack= serviceMusiCallBack;
        mPlayer= new MediaPlayer();
    }

    public ServiceMusic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer= new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        countDownTimer.cancel();
        serviceMusiCallBack.nextSong(music);
    }

    @Override
    public void onSeekTime(int timeInMilis) {
        mPlayer.seekTo(timeInMilis);
    }

    @Override
    public void play(Music music) {
        this.music= music;
        if(mPlayer.isPlaying()) mPlayer.stop();
        mPlayer= new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {

            mPlayer.setDataSource(music.getLink());
//                        mPlayer.setDataSource(ulr);
            mPlayer.prepare();
            mPlayer.start();
            onCoutDowTimmer();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        mPlayer.setOnCompletionListener(this);
    }

    private void onCoutDowTimmer() {

        final int i= mPlayer.getDuration()/10*10;
        serviceMusiCallBack.timeDuration(i);
         countDownTimer= new CountDownTimer( i, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                serviceMusiCallBack.seekBarr((int) mPlayer.getCurrentPosition());
            }

            @Override
            public void onFinish() {
                countDownTimer.start();
            }
        };
         countDownTimer.start();
    }

    @Override
    public void pause() {
        if(mPlayer.isPlaying())
        {
            mPlayer.pause();
            countDownTimer.cancel();
        }
    }

    @Override
    public void resume(int time) {
        countDownTimer.start();
        mPlayer.start();
    }
}
