package com.example.musicfragment.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musicfragment.Common;
import com.example.musicfragment.InterfaceCommon.fragmentMusicEvent;
import com.example.musicfragment.R;
import com.example.musicfragment.model.Lyric;
import com.example.musicfragment.model.Music;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("ValidFragment")
public class FragmentMusic extends Fragment implements fragmentMusicEvent {
    ArrayList<Lyric> list;
    Music music;
    Animation anim;
    public FragmentMusic(ArrayList<Lyric> list) {
        this.list = list;
    }

    TextView txtLyricItem;
    LinearLayout lineLyricItem;
    CircleImageView img_view;
    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_music, container, false);
        txtLyricItem= view.findViewById(R.id.txtLyricItem);
        lineLyricItem= view.findViewById(R.id.lineLyricItem);
        img_view= view.findViewById(R.id.img_view);
        txtLyricItem.setText(list.get(0).getLyric());
        return view;
    }

    public void setList(ArrayList<Lyric> list) {
        this.list = list;
        txtLyricItem.setText(list.get(0).getLyric());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void changeLyric(int timeInmilis) {
        Lyric li= new Lyric(timeInmilis);
        if(list.contains(li))
        {
           Common.AnimaionUlti(getContext(), lineLyricItem, R.anim.anim_chage_text);
            txtLyricItem.setText(list.get(list.indexOf(li)).getLyric());
        }
    }


    public void setAnimation() {

        if(img_view.getAnimation()!= null)
            img_view.getAnimation().start();
        else
            Common.AnimaionUlti(getContext(), img_view, R.anim.anim_image_view);
    }

    public void cancelAnimation() {
       if(img_view.getAnimation()!= null)
           img_view.getAnimation().cancel();
    }
}
