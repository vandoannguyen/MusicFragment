package com.example.musicfragment.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Lyric {
    int timeInmilis;
    String lyric;

    public Lyric(int timeInmilis) {
        this.timeInmilis = timeInmilis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lyric)) return false;
        Lyric lyric = (Lyric) o;
        return Math.abs(getTimeInmilis() - lyric.getTimeInmilis())<80;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(getTimeInmilis());
    }

    public int getTimeInmilis() {
        return timeInmilis;
    }

    public void setTimeInmilis(int timeInmilis) {
        this.timeInmilis = timeInmilis;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Lyric(int timeInmilis, String lyric) {
        this.timeInmilis = timeInmilis;
        this.lyric = lyric;
    }
    public Lyric() {
    }
}
