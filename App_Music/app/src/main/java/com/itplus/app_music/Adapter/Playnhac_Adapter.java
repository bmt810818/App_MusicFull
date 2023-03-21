package com.itplus.app_music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Model.Baihathot;
import com.itplus.app_music.R;

import java.util.ArrayList;

public class Playnhac_Adapter extends RecyclerView.Adapter<Playnhac_Adapter.ViewHolder>{
    Context context;
    ArrayList<Baihathot> mangbaihat;

    public Playnhac_Adapter(Context context, ArrayList<Baihathot> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_play_baihat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihathot baihat = mangbaihat.get(position);
        holder.txtvtenbaihat.setText(baihat.getTenbaihat());
        holder.txtvindex.setText(position + 1 +"");
        holder.txtvcasi.setText(baihat.getCasi());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtvindex,txtvtenbaihat,txtvcasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvindex = itemView.findViewById(R.id.txtvPlaynhacindex);
            txtvtenbaihat = itemView.findViewById(R.id.txtvPlaynhactenbaihat);
            txtvcasi = itemView.findViewById(R.id.txtvTencasi);
        }
    }
}
