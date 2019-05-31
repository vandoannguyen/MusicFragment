package com.example.musicfragment;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class Common {
    public static Bus bus= new Bus(ThreadEnforcer.ANY);
    public static Bus getBus(){
        return bus;
    };
    public static int check=0;
    public static void AnimaionUlti(Context context, View view, int anim)
    {
        Animation animation= AnimationUtils.loadAnimation(context, anim);
        view.setAnimation(animation);
    }
}
