package com.example.musicfragment.model;

import com.example.musicfragment.model.Music;

public class BusObject {
    Music music;
    String check;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public BusObject(Music music, String check) {
        this.music = music;
        this.check = check;
    }
}
