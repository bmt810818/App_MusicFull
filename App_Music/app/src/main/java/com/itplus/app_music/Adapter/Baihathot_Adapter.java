package com.itplus.app_music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Activity.PlayNhacActivity;
import com.itplus.app_music.Model.Baihathot;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Baihathot_Adapter extends RecyclerView.Adapter<Baihathot_Adapter.ViewHodel>{
    Context context;
    ArrayList<Baihathot> baihathotArrayList;

    public Baihathot_Adapter(Context context, ArrayList<Baihathot> baihathotArrayList) {
        this.context = context;
        this.baihathotArrayList = baihathotArrayList;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihathot,parent,false);

        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        Baihathot baihathot = baihathotArrayList.get(position);
        holder.txtvTencasibaihathot.setText(baihathot.getCasi());
        holder.txtvBaihathot.setText(baihathot.getTenbaihat());
        //Picasso.get().load(baihathot.getHinhbaihat()).into(holder.imgBaihathot);
        Picasso.with(context).load(baihathot.getHinhbaihat()).into(holder.imgBaihathot);
    }

    @Override
    public int getItemCount() {
        return baihathotArrayList.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{
        TextView txtvBaihathot,txtvTencasibaihathot;
        ImageView imgBaihathot,imgLuotThich;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtvTencasibaihathot = itemView.findViewById(R.id.txtvTencasibaihathot);
            txtvBaihathot = itemView.findViewById(R.id.txtvBaihathot);
            imgBaihathot = itemView.findViewById(R.id.imgBaihathot);
            imgLuotThich = itemView.findViewById(R.id.imgLuotThich);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc",baihathotArrayList.get(getLayoutPosition()));
                context.startActivity(intent);
            });
            imgLuotThich.setOnClickListener(v -> {
                imgLuotThich.setImageResource(R.drawable.iconloved);
                Dataservice dataservice = APIService.getService();
                Call<String> call = dataservice.UpdateLike("1",baihathotArrayList.get(getLayoutPosition()).getIdbaihat());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua = response.body();
                        if (ketqua.equals("success")){
                            Toast.makeText(context, "da like", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "bi loi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                imgLuotThich.setEnabled(false);
            });
        }
    }
}
