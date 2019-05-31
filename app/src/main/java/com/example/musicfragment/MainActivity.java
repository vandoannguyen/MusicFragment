package com.example.musicfragment;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicfragment.Adapter.AdapterViewPager;
import com.example.musicfragment.Adapter.RecylerAdapter;
import com.example.musicfragment.Fragment.FragmentMusic;
import com.example.musicfragment.InterfaceCommon.ServiceMusiCallBack;
import com.example.musicfragment.InterfaceCommon.Fragmentlist;
import com.example.musicfragment.model.Music;
import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity implements Fragmentlist, ServiceMusiCallBack {
    ArrayList<Music> list;
    ImageButton btnPlay, btnBack , btnNext, btnDropDow,btnPlayf,btnNextf,btnBackf;
    TextView txtNameOfSong, txtNameOfSinger, txtSong, txtSinger, txtCurrentTime, txtDurationTime;
    RecyclerView rcViewListMusic;
    RecylerAdapter adapter;
    LinearLayout lineMusic;
    FragmentMusic fragmenMusic;
    LinearLayout fragment;
    ViewPager viewpager;
    AdapterViewPager adapterViewPager;
    CircleIndicator indicator;
    ServiceMusic serviceMusic;
    SeekBar seekBar;
    Music mu;
    CircleImageView imgViewSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requetPermission();
//        check();
        serviceMusic= new ServiceMusic(this);
        Intent intent = new Intent(this,serviceMusic.getClass());
        startService(intent);
        list= new ArrayList<>();
        list= FileUlty.getListMusic();
        mu= list.get(0);
        initControl();
        initEvent();
    }

    private void initEvent() {
        lineMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.setVisibility(View.VISIBLE);
                Common.AnimaionUlti(v.getContext(), fragment, R.anim.anim_fragment_music);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playOrPause();
            }
        });
        btnDropDow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.AnimaionUlti(v.getContext(), fragment,R.anim.anim_drop_down);
                fragment.setVisibility(View.INVISIBLE);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        btnPlayf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playOrPause();

            }
        });
        btnBackf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        btnNextf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                serviceMusic.onSeekTime(seekBar.getProgress());
            }
        });

    }

    private void playOrPause() {
        if(Common.check==0)
        {
            Common.check=1;
//                    Common.AnimaionUlti(v.getContext(), btnPlay,R.anim.press_button_play);
            Animation animator= AnimationUtils.loadAnimation(btnPlay.getContext(), R.anim.press_button_play);
            btnPlay.setAnimation(animator);
            btnPlay.setImageResource(R.mipmap.ic_pause);
            btnPlayf.setImageResource(R.mipmap.ic_pause);
            serviceMusic.play(mu);
            adapterViewPager.startAnimation();
            Common.AnimaionUlti(imgViewSongs.getContext(), imgViewSongs, R.anim.anim_image_view);
        }
        else
        if(Common.check==2)
        {
            Common.check=1;
//                    Common.AnimaionUlti(v.getContext(), v,R.anim.press_button_play);
            btnPlay.setImageResource(R.mipmap.ic_pause);
            btnPlayf.setImageResource(R.mipmap.ic_pause);
            serviceMusic.resume(seekBar.getProgress());
            adapterViewPager.startAnimation();
            Common.AnimaionUlti(imgViewSongs.getContext(), imgViewSongs, R.anim.anim_image_view);
            adapterViewPager.startAnimation();
        }else
        if(Common.check==1)
        {
            Common.AnimaionUlti(btnPlayf.getContext(), btnPlay,R.anim.press_button_pause);
            btnPlay.setImageResource(R.drawable.ic_play);
            btnPlayf.setImageResource(R.drawable.ic_play);
            serviceMusic.pause();

            imgViewSongs.getAnimation().cancel();
            adapterViewPager.canelAnimation();
            Common.check=2;
        }
    }

    private void next() {
        int index= list.indexOf(mu)+1;
        if(index<list.size())
        {
            mu=list.get(index);
            if(Common.check==1)
                serviceMusic.play(mu);
            changgeText(mu);
        }
        else
        {
            mu=list.get(0);
            if(Common.check==1)
                serviceMusic.play(mu);
            changgeText(mu);
        }
    }

    private void back() {
        int index= list.indexOf(mu)-1;
        if(index>=0)
        {
            mu=list.get(index);
            if(Common.check==1)
                serviceMusic.play(mu);
            changgeText(mu);
        }
        else
        {

            mu=list.get(list.size()-1);
            changgeText(mu);
            if(Common.check==1)
                serviceMusic.play(mu);
        }
    }
    private  void changgeText(Music music )
    {
        txtNameOfSinger.setText(music.getNameofSinger());
        txtNameOfSong.setText(music.getNameofSong());
        txtSinger.setText(music.getNameofSinger());
        txtSong.setText(music.getNameofSong());
        adapterViewPager.setMusic(music);
    }
    private void initControl() {
        txtNameOfSong=findViewById(R.id.txtNameOfSong);
        txtNameOfSinger= findViewById(R.id.txtnameOfSinger);
        btnBack= findViewById(R.id.btnBack);
        btnPlay= findViewById(R.id.btnPlay);
        btnNext= findViewById(R.id.btnNext);
        imgViewSongs= findViewById(R.id.imgViewSong);
        rcViewListMusic= findViewById(R.id.rcViewListMusic);
        lineMusic= findViewById(R.id.lineMusic);
        btnDropDow= findViewById(R.id.btnDropDown);
        viewpager= findViewById(R.id.viewpager);
        indicator= findViewById(R.id.indicator);
        txtSinger= findViewById(R.id.txtSinger);
        txtSong= findViewById(R.id.txtSong);
        btnPlayf= findViewById(R.id.btnPlayf);
        btnNextf= findViewById(R.id.btnNextf);
        btnBackf= findViewById(R.id.btnBackf);
        seekBar= findViewById(R.id.seekBar);
        FragmentManager fragmentManager= getSupportFragmentManager();
        txtCurrentTime= findViewById(R.id.txtCurrentTime);
        txtDurationTime= findViewById(R.id.txtDurationTime);
        txtNameOfSinger.setText(mu.getNameofSinger());
        txtNameOfSong.setText(mu.getNameofSong());
        txtSinger.setText(mu.getNameofSinger());
        txtSong.setText(mu.getNameofSong());


        adapterViewPager=new AdapterViewPager(fragmentManager, mu, this);
        viewpager.setAdapter(adapterViewPager);
        indicator.setViewPager(viewpager);

        adapter= new RecylerAdapter(this,list);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        rcViewListMusic.setLayoutManager(layoutManager);
        rcViewListMusic.setAdapter(adapter);
        fragment= findViewById(R.id.fragment);
        fragment.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(String p:permissions)
        {
            if(ContextCompat.checkSelfPermission(this, p)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{p}, 0);
            }
        }
    }
    private void requetPermission()
    {
        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE};
        for(String p:permissions)
        {
            ActivityCompat.requestPermissions(this, permissions, 0);
        }
    }

    @Override
    public void clickItem(Music music) {
        serviceMusic.play(music);
        mu=music;
        fragment.setVisibility(View.VISIBLE);
        Common.AnimaionUlti(this, fragment, R.anim.anim_fragment_music);

        changgeText(mu);
        Common.check=1;
        btnPlayf.setImageResource(R.mipmap.ic_pause);
        btnPlay.setImageResource(R.mipmap.ic_pause);
        Common.AnimaionUlti(imgViewSongs.getContext(), imgViewSongs, R.anim.anim_image_view);

    }

    @Override
    public void clickItemLyric(int i) {
        serviceMusic.onSeekTime(i);
    }

    @Override
    public void onBackPressed() {
        fragment.setVisibility(View.INVISIBLE);
        Common.AnimaionUlti(this, fragment, R.anim.anim_drop_down);
        super.onBackPressed();
    }


    @Override
    public void seekBarr(int seekTime) {
        seekBar.setProgress((int) seekTime);
        adapterViewPager.timeLyricChange(seekTime);
        int m= seekTime/1000/60;
        int s= seekTime/1000 - m*60;
        String mm, ss;
        if(m<=10)
        {
            mm="0"+m;
        }
        else
            mm=m+"";
        if(s<10)
            ss="0"+s;
        else
            ss=s+"";
        txtCurrentTime.setText(mm+":"+ss);
    }

    @Override
    public void timeDuration(int timeimmilis) {
        seekBar.setMax( timeimmilis);
        seekBar.setProgress(0);
        int m= timeimmilis/1000/60;
        int s= timeimmilis/1000 - m*60;
        String mm, ss;
        if(m<=10)
        {
            mm="0"+m;
        }
        else
            mm=m+"";
        if(s<10)
            ss="0"+s;
        else
            ss=s+"";
        txtDurationTime.setText(mm+":"+ss);
    }

    @Override
    public void nextSong(Music music) {
        next();
    }
}
