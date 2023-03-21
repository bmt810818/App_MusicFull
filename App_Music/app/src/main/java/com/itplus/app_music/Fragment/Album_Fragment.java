package com.itplus.app_music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Activity.DanhsachAllAlbumActivity;
import com.itplus.app_music.Adapter.AlbumAdapter;
import com.itplus.app_music.Model.Album;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Album_Fragment extends Fragment {
    View view;
    RecyclerView recyclerAlbum;
    TextView txtvXemthemAlbum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album,container,false);
        recyclerAlbum = view.findViewById(R.id.recyclerAlbum);
        txtvXemthemAlbum = view.findViewById(R.id.txtvXemthemAlbum);
        txtvXemthemAlbum.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DanhsachAllAlbumActivity.class);
            startActivity(intent);
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> listCall = dataservice.GetDataAlbum();
        listCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),albumArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerAlbum.setLayoutManager(linearLayoutManager);
                recyclerAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
