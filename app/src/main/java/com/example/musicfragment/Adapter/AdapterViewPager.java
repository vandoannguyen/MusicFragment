package com.example.musicfragment.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.musicfragment.FileUlty;
import com.example.musicfragment.Fragment.FragmentLyrc;
import com.example.musicfragment.Fragment.FragmentMusic;
import com.example.musicfragment.InterfaceCommon.FragmentPagerEvent;
import com.example.musicfragment.InterfaceCommon.Fragmentlist;
import com.example.musicfragment.model.Lyric;
import com.example.musicfragment.model.Music;

import java.util.ArrayList;

public class AdapterViewPager extends FragmentPagerAdapter implements FragmentPagerEvent {
    Music music;
    ArrayList<Lyric> listLyric;
    FragmentMusic fragmentMusic;
    FragmentLyrc fragmentLyrc;
    Fragmentlist fragmentlist;
    public AdapterViewPager(FragmentManager fm, Music music, Fragmentlist fragmentlist) {
        super(fm);
        this.fragmentlist= fragmentlist;
        this.music = music;
        listLyric= FileUlty.getLyric(music.getNameOfLyric());
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
        listLyric= FileUlty.getLyric(music.getNameOfLyric());
        fragmentLyrc.setList(listLyric);
        fragmentMusic.setList(listLyric);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i)
        {
            case 0:{
                return fragmentMusic= new FragmentMusic(listLyric);
            }
            case 1:{
                return fragmentLyrc=new FragmentLyrc(listLyric, this);
            }
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * @param time
     * set tim thay đổi theo nhạc
     *
     */
    @Override
    public void timeLyricChange(int time) {
        fragmentMusic.changeLyric(time);
        fragmentLyrc.changeLyric(time);
    }

    /**
     * @param time
     * seekTime Khi click vào lyric nhạc
     *
     */
    @Override
    public void sheekTimeByLyric(int time) {
        fragmentlist.clickItemLyric(time);
    }
    public void startAnimation()
    {
        fragmentMusic.setAnimation();
    }
    public void canelAnimation(){
        fragmentMusic.cancelAnimation();
    }
}
