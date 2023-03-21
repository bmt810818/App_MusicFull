package com.itplus.app_music.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.app_music.Adapter.Baihathot_Adapter;
import com.itplus.app_music.Model.Baihathot;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Baihathot_Fragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    Baihathot_Adapter baihathot_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihathot,container,false);
        recyclerView = view.findViewById(R.id.recyclerBaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihathot>> listCall = dataservice.GetDataBaihatHot();
        listCall.enqueue(new Callback<List<Baihathot>>() {
            @Override
            public void onResponse(Call<List<Baihathot>> call, Response<List<Baihathot>> response) {
                ArrayList<Baihathot> baihathotArrayList = (ArrayList<Baihathot>) response.body();
                baihathot_adapter = new Baihathot_Adapter(getActivity(),baihathotArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(baihathot_adapter);
            }

            @Override
            public void onFailure(Call<List<Baihathot>> call, Throwable t) {

            }
        });
    }
}
