package com.itplus.app_music.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Adapter.AllAlbum_Adapter;
import com.itplus.app_music.Model.Album;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachAllAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    AllAlbum_Adapter allalbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_all_album);
        mapping();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> listCall = dataservice.GetAllAlbum();
        listCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                allalbumAdapter = new AllAlbum_Adapter(DanhsachAllAlbumActivity.this,albumArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachAllAlbumActivity.this,2));
                recyclerView.setAdapter(allalbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void mapping() {
        recyclerView = findViewById(R.id.rcvAllAlbum);
        toolbar = findViewById(R.id.toolbarAllAlbum);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("tat ca album");
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}