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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHoder>{
    Context context;
    ArrayList<Baihathot> mangbaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihathot> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachbaihat,parent,false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Baihathot baihat = mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txttenbaihat.setText(baihat.getTenbaihat());
        holder.txtindex.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txtcasi;
        ImageView imgluotthich;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.txtvDanhsachindex);
            txttenbaihat = itemView.findViewById(R.id.txtvTenbaihat);
            txtcasi = itemView.findViewById(R.id.txtvTencasi);
            imgluotthich = itemView.findViewById(R.id.imgLuotthichdanhsachbh);
            imgluotthich.setOnClickListener(v -> {
                imgluotthich.setImageResource(R.drawable.iconloved);
                Dataservice dataservice = APIService.getService();
                Call<String> call = dataservice.UpdateLike("1",mangbaihat.get(getLayoutPosition()).getIdbaihat());
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
                imgluotthich.setEnabled(false);
            });
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc",mangbaihat.get(getLayoutPosition()));
                context.startActivity(intent);
            });
        }
    }
}
