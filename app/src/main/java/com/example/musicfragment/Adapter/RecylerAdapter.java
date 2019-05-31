package com.example.musicfragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musicfragment.InterfaceCommon.Fragmentlist;
import com.example.musicfragment.model.Music;
import com.example.musicfragment.R;

import java.util.ArrayList;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {
    ArrayList<Music> list;
    Fragmentlist fragmentlist;
    public RecylerAdapter(Fragmentlist fragmentlist,ArrayList<Music> list) {
        this.list = list;
        this.fragmentlist= fragmentlist;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view= inflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txtNameOfSong.setText(list.get(i).getNameofSong());
        viewHolder.txtnameOfSinger.setText(list.get(i).getNameofSinger());
        viewHolder.lineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentlist.clickItem(list.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  imgOfSong;
        TextView txtNameOfSong, txtnameOfSinger;
        LinearLayout lineItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgOfSong= itemView.findViewById(R.id.imgOfSong);
            txtnameOfSinger= itemView.findViewById(R.id.txtnameOfSinger);
            txtNameOfSong= itemView.findViewById(R.id.txtNameOfSong);
            imgOfSong.setImageResource(R.drawable.img_view);
            lineItem= itemView.findViewById(R.id.lineItem);
        }
    }
}
