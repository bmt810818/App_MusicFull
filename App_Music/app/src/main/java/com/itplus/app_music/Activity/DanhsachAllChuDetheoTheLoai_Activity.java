package com.itplus.app_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Adapter.DanhsachCDtheoTL_Adapter;
import com.itplus.app_music.Model.Chude;
import com.itplus.app_music.Model.Theloai;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachAllChuDetheoTheLoai_Activity extends AppCompatActivity {
    Theloai theloai;
    RecyclerView rcvTLtheoCD;
    Toolbar toolbartltheocd;
    DanhsachCDtheoTL_Adapter danhsachCDtheoTL_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_all_chu_detheo_the_loai);
        GetIntent();
        Mapping();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Chude>> listCall = dataservice.GetCDtheoTL(theloai.getIdtheloai());
        listCall.enqueue(new Callback<List<Chude>>() {
            @Override
            public void onResponse(Call<List<Chude>> call, Response<List<Chude>> response) {
                ArrayList<Chude> theloaiArrayList = (ArrayList<Chude>) response.body();
                danhsachCDtheoTL_adapter = new DanhsachCDtheoTL_Adapter(DanhsachAllChuDetheoTheLoai_Activity.this,theloaiArrayList);
                rcvTLtheoCD.setLayoutManager(new GridLayoutManager(DanhsachAllChuDetheoTheLoai_Activity.this,2));
                rcvTLtheoCD.setAdapter(danhsachCDtheoTL_adapter);
            }

            @Override
            public void onFailure(Call<List<Chude>> call, Throwable t) {
                Log.d("QQQ", String.valueOf(t));
            }
        });
    }

    private void Mapping() {
        rcvTLtheoCD = findViewById(R.id.rcvTLtheoCD);
        toolbartltheocd = findViewById(R.id.toolbartltheocd);
        setSupportActionBar(toolbartltheocd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(theloai.getTentheloai());
        toolbartltheocd.setNavigationOnClickListener(v -> {
            finish();
        });

    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("theloai")){
            theloai = (Theloai) intent.getSerializableExtra("theloai");
            Toast.makeText(this, theloai.getIdtheloai(), Toast.LENGTH_SHORT).show();
        }


    }
}