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

import com.itplus.app_music.Activity.PlayNhacActivity;
import com.itplus.app_music.Adapter.Playnhac_Adapter;
import com.itplus.app_music.R;

public class Playbaihat_Fragment extends Fragment {
    View view;
    RecyclerView rcvplaynhac;
    Playnhac_Adapter playnhac_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playnhac,container,false);
        rcvplaynhac = view.findViewById(R.id.rcvplaynhac);
        if (PlayNhacActivity.mangbaihat.size() > 0){
            playnhac_adapter = new Playnhac_Adapter(getActivity(), PlayNhacActivity.mangbaihat);
            rcvplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcvplaynhac.setAdapter(playnhac_adapter);
        }
        return view;
    }
}
