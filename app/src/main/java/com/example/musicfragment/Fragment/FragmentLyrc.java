package com.example.musicfragment.Fragment;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicfragment.Adapter.LyrciAdapter;
import com.example.musicfragment.Adapter.RecylerAdapter;
import com.example.musicfragment.FileUlty;
import com.example.musicfragment.InterfaceCommon.FragmentPagerEvent;
import com.example.musicfragment.InterfaceCommon.RecyclerLyricClick;
import com.example.musicfragment.InterfaceCommon.fragmentMusicEvent;
import com.example.musicfragment.R;
import com.example.musicfragment.model.Lyric;
import com.example.musicfragment.model.Music;

import java.io.File;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FragmentLyrc extends Fragment implements fragmentMusicEvent, RecyclerLyricClick {
    ArrayList<Lyric> list;
    Music music;
    RecyclerView rcViewLick;
    LyrciAdapter adapter;
    FragmentPagerEvent fragmentPagerEvent;
    public FragmentLyrc(ArrayList<Lyric> list, FragmentPagerEvent fragmentPagerEvent) {
        this.fragmentPagerEvent= fragmentPagerEvent;
        this.list = list;
    }

    public void setList(ArrayList<Lyric> list) {
        this.list = list;
        adapter= new LyrciAdapter(list, this);
        rcViewLick.setAdapter(adapter);
    }
    //    public FragmentLyrc(Music  music) {
//        this.music= music;
//        this.list = FileUlty.getLyric(music.getNameOfLyric());
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = inflater.inflate(R.layout.fragment_lyric, container, false);
         adapter= new LyrciAdapter(list, this);

//
//        RecyclerView.SmoothScroller smoothScroller= new LinearSmoothScroller(getContext()){
//            @Override
//            protected int getVerticalSnapPreference() {
//               return   LinearSmoothScroller.SNAP_TO_START;
//            }
//        };
//        smoothScroller.setTargetPosition(5);
//        linearLayoutManager.startSmoothScroll(smoothScroller);


        rcViewLick= view.findViewById(R.id.rcViewLick);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager( view.getContext(),LinearLayoutManager.VERTICAL, false );
        linearLayoutManager.scrollToPositionWithOffset(5, 100);

        rcViewLick.setLayoutManager(linearLayoutManager);
        rcViewLick.setAdapter(adapter);
//        rcViewLick.smoothScrollToPosition(6);
//        rcViewLick.setTop();
         return view;
    }

    @Override
    public void changeLyric(int timeInmilis) {
//        rcViewLick.setTop(list.indexOf(new Lyric(timeInmilis)));
        ((LinearLayoutManager)rcViewLick.getLayoutManager()).scrollToPositionWithOffset(list.indexOf(new Lyric(timeInmilis)),500);
    }

    @Override
    public void onClickItem(int posion) {
        Lyric lyric= list.get(posion);
        fragmentPagerEvent.sheekTimeByLyric(lyric.getTimeInmilis());
    }
}
