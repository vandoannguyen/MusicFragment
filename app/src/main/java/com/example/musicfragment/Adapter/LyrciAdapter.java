package com.example.musicfragment.Adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musicfragment.InterfaceCommon.Fragmentlist;
import com.example.musicfragment.InterfaceCommon.RecyclerLyricClick;
import com.example.musicfragment.R;
import com.example.musicfragment.model.Lyric;

import java.util.ArrayList;
import java.util.HashMap;

public class LyrciAdapter extends RecyclerView.Adapter<LyrciAdapter.ViewHolder> {
    ArrayList<Lyric> list;
    RecyclerLyricClick recyclerLyricClick;
    public LyrciAdapter(ArrayList<Lyric> list ,RecyclerLyricClick recyclerLyricClick) {
        this.recyclerLyricClick= recyclerLyricClick;
        this.list = list;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.stirng_lyric, viewGroup, false);
        view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
        return new ViewHolder(view);
    }

    public void setList(ArrayList<Lyric> list) {
        this.list = list;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txtlyric.setText(list.get(i).getLyric());
        viewHolder.lineTextLyric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerLyricClick.onClickItem(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtlyric;
        LinearLayout lineTextLyric;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtlyric= itemView.findViewById(R.id.txtLyric);
            lineTextLyric= itemView.findViewById(R.id.lineTextLyric);
        }
    }
}
