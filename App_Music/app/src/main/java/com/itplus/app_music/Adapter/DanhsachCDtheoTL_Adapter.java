package com.itplus.app_music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Model.Chude;
import com.itplus.app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachCDtheoTL_Adapter extends RecyclerView.Adapter<DanhsachCDtheoTL_Adapter.ViewHodel>{
    Context context;
    ArrayList<Chude> chudeArrayList;

    public DanhsachCDtheoTL_Adapter(Context context, ArrayList<Chude> theloaiArrayList) {
        this.context = context;
        this.chudeArrayList = theloaiArrayList;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_chudetheoteloai,parent,false);

        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        Chude chude = chudeArrayList.get(position);
        Picasso.with(context).load(chude.getHinhchude()).into(holder.imgCDtheoTL);
        //Picasso.get().load(chude.getHinhchude()).into(holder.imgCDtheoTL);
        holder.txtvCDtheoTL.setText(chude.getTenchude());
    }

    @Override
    public int getItemCount() {
        return chudeArrayList.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{
        ImageView imgCDtheoTL;
        TextView txtvCDtheoTL;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgCDtheoTL = itemView.findViewById(R.id.imgCDtheoTL);
            txtvCDtheoTL = itemView.findViewById(R.id.txtvCDtheoTL);
            itemView.setOnClickListener(v -> {
//                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
//                intent.putExtra("idtheloai",chudeArrayList.get(getLayoutPosition()));
//                context.startActivity(intent);
            });
        }
    }
}
