package com.example.musicfragment.InterfaceCommon;

import com.example.musicfragment.model.Music;

public interface ServiceMusiCallBack {
    void seekBarr(int seekTime);
    void timeDuration(int timeimmilis);
    void nextSong(Music music);
}
