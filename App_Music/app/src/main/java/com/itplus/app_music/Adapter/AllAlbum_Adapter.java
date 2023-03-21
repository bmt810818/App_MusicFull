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

public class AllAlbum_Adapter extends RecyclerView.Adapter<AllAlbum_Adapter.ViewHodel>{
    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbum_Adapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_all_album,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.with(context).load(album.getHinhalbum()).into(holder.imgallalbum);


        //Picasso.get().load(album.getHinhalbum()).into(holder.imgallalbum);
        holder.txtvallalbum.setText(album.getTenalbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{
        ImageView imgallalbum;
        TextView txtvallalbum;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgallalbum = itemView.findViewById(R.id.imgDanhsachallallbum);
            txtvallalbum = itemView.findViewById(R.id.txtvtendanhsachallalbum);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("album",albumArrayList.get(getLayoutPosition()));
                context.startActivity(intent);
            });
        }
    }
}
