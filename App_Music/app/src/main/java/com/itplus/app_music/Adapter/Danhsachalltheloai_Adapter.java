package com.itplus.app_music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Activity.DanhsachAllChuDetheoTheLoai_Activity;
import com.itplus.app_music.Model.Theloai;
import com.itplus.app_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Danhsachalltheloai_Adapter extends RecyclerView.Adapter<Danhsachalltheloai_Adapter.ViewHolder>{
    Context context;
    ArrayList<Theloai> theloaiArrayList;

    public Danhsachalltheloai_Adapter(Context context, ArrayList<Theloai> theloaiArrayList) {
        this.context = context;
        this.theloaiArrayList = theloaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_alltheloai,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theloai theloai = theloaiArrayList.get(position);
        Picasso.with(context).load(theloai.getHinhtheloai()).into(holder.imgTheloai);
        //Picasso.get().load(theloai.getHinhtheloai()).into(holder.imgTheloai);
    }

    @Override
    public int getItemCount() {
        return theloaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTheloai = itemView.findViewById(R.id.imgdongtheloai);
            imgTheloai.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhsachAllChuDetheoTheLoai_Activity.class);
                intent.putExtra("theloai",theloaiArrayList.get(getLayoutPosition()));
                context.startActivity(intent);
            });
        }
    }
}
