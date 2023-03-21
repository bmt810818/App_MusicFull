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
import com.itplus.app_music.Model.QuangCao;
import com.itplus.app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHodel> {
    ArrayList<QuangCao> bannerArrayList;
    View view;
    Context context;
    public BannerAdapter(ArrayList<QuangCao> bannerArrayList) {
        this.bannerArrayList = bannerArrayList;
    }


    @NonNull
    @Override
    public BannerAdapter.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_banner,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.ViewHodel holder, int position) {

        QuangCao banner = bannerArrayList.get(position);
        Picasso.with(context).load(banner.getHinhanh()).into(holder.imgBacgroundbanner);
        Picasso.with(context).load(banner.getHinhbaihat()).into(holder.imgViewbanner);
        //Picasso.get().load(banner.getHinhanh()).into(holder.imgBacgroundbanner);
        //Picasso.get().load(banner.getHinhbaihat()).into(holder.imgViewbanner);
        holder.txtvBannerMotabaihat.setText(banner.getNoidung());
        holder.txtvTitelbannerbaihat.setText(banner.getTenbaihat());
        view.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), DanhsachbaihatActivity.class);
            intent.putExtra("banner",bannerArrayList.get(position));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bannerArrayList.size();
    }
    public class ViewHodel extends RecyclerView.ViewHolder{
        ImageView imgBacgroundbanner,imgViewbanner;
        TextView txtvBannerMotabaihat,txtvTitelbannerbaihat;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgBacgroundbanner = itemView.findViewById(R.id.imgBacgroundbanner);
            imgViewbanner = itemView.findViewById(R.id.imgViewbanner);
            txtvBannerMotabaihat = itemView.findViewById(R.id.txtvBannerMotabaihat);
            txtvTitelbannerbaihat = itemView.findViewById(R.id.txtvTitelbannerbaihat);
        }
    }
}
