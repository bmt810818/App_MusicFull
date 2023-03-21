package com.itplus.app_music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Activity.DanhsachbaihatActivity;
import com.itplus.app_music.Model.PlayList;
import com.itplus.app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachallPlaylist_Adapter extends RecyclerView.Adapter<DanhsachallPlaylist_Adapter.ViewHodel>{
    Context context;
    ArrayList<PlayList> playListArrayList;

    public DanhsachallPlaylist_Adapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachallplaylist,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        PlayList playList = playListArrayList.get(position);
        Picasso.with(context).load(playList.getHinhicon()).into(holder.imgDanhsachallplalist);
        //Picasso.get().load(playList.getHinhicon()).into(holder.imgDanhsachallplalist);
        holder.txtvtendanhsachallplaylist.setText(playList.getTen());

    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{
        ImageView imgDanhsachallplalist;
        TextView txtvtendanhsachallplaylist;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgDanhsachallplalist = itemView.findViewById(R.id.imgDanhsachallplalist);
            txtvtendanhsachallplaylist = itemView.findViewById(R.id.txtvtendanhsachallplaylist);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("itemplaylist",playListArrayList.get(getLayoutPosition()));
                context.startActivity(intent);
            });
        }
    }
}
