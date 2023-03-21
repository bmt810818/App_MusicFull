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
import com.itplus.app_music.Model.Album;
import com.itplus.app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHodel>{
    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);

        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        Album album = albumArrayList.get(position);
        holder.txtvTencsAlbum.setText(album.getTencasialbum());
        holder.txtvTenAlbum.setText(album.getTenalbum());
        Picasso.with(context).load(album.getHinhalbum()).into(holder.imgAlbum);
        //Picasso.get().load(album.getHinhalbum()).into(holder.imgAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{
        ImageView imgAlbum;
        TextView txtvTenAlbum,txtvTencsAlbum;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.imgAlbum);
            txtvTenAlbum = itemView.findViewById(R.id.txtvTenAlbum);
            txtvTencsAlbum = itemView.findViewById(R.id.txtvTencsAlbum);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("album",albumArrayList.get(getLayoutPosition()));
                context.startActivity(intent);
            });
        }
    }

}
