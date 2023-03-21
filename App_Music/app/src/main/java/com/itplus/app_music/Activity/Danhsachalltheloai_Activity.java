package com.itplus.app_music.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Adapter.Danhsachalltheloai_Adapter;
import com.itplus.app_music.Model.Theloai;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Danhsachalltheloai_Activity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Danhsachalltheloai_Adapter danhsachalltheloai_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachalltheloai);
        mapping();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Theloai>> listCall = dataservice.GetallTheloai();
        listCall.enqueue(new Callback<List<Theloai>>() {
            @Override
            public void onResponse(Call<List<Theloai>> call, Response<List<Theloai>> response) {
                ArrayList<Theloai> theloaiArrayList = (ArrayList<Theloai>) response.body();
                danhsachalltheloai_adapter = new Danhsachalltheloai_Adapter(Danhsachalltheloai_Activity.this,theloaiArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(Danhsachalltheloai_Activity.this,1));
                recyclerView.setAdapter(danhsachalltheloai_adapter);
            }

            @Override
            public void onFailure(Call<List<Theloai>> call, Throwable t) {

            }
        });
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbaralltheloai);
        recyclerView = findViewById(R.id.rcvallTheloai);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tat ca the loai");
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}