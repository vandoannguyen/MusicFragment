package com.example.musicfragment.InterfaceCommon;

import com.example.musicfragment.model.Music;

public interface ClickButtonMusic {
    String PAAUSE="pause";
    String PLAY="play";
    void onSeekTime(int timeInMilis);
    void play(Music music);
    void pause();
    void resume(int instan);
}
