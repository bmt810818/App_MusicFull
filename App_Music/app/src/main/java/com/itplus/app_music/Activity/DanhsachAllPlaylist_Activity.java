package com.itplus.app_music.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Adapter.DanhsachallPlaylist_Adapter;
import com.itplus.app_music.Model.PlayList;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachAllPlaylist_Activity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    DanhsachallPlaylist_Adapter danhsachallPlaylist_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_all_playlist);
        mapping();
        toobar();
        GetDataAllPlayList();
    }

    private void GetDataAllPlayList() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayList>> listCall = dataservice.GetDanhsachAllPlaylist();
        listCall.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> mangPlaylist = (ArrayList<PlayList>) response.body();
                danhsachallPlaylist_adapter = new DanhsachallPlaylist_Adapter(DanhsachAllPlaylist_Activity.this,mangPlaylist);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachAllPlaylist_Activity.this,2));
                recyclerView.setAdapter(danhsachallPlaylist_adapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void toobar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("play lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbarDanhsachallplaylist);
        recyclerView = findViewById(R.id.rcvDanhsachallplaylist);

    }
}