package com.itplus.app_music.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.itplus.app_music.Adapter.BannerAdapter;
import com.itplus.app_music.Model.QuangCao;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Banner_Fragment extends Fragment {
    View view;
    ViewPager2 viewPager2;
    ViewPager2 viewPagerBanner;
    CircleIndicator3 circleIndicator3;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        mapping();
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<QuangCao>> listCall = dataservice.GetDatabanner();
        listCall.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banner = (ArrayList<QuangCao>) response.body();
                bannerAdapter = new BannerAdapter(banner);
                viewPagerBanner.setAdapter(bannerAdapter);
                circleIndicator3.setViewPager(viewPagerBanner);
                handler = new Handler();
                runnable = () -> {
                    currentItem = viewPagerBanner.getCurrentItem();
                    currentItem++;
                    if (currentItem >= viewPagerBanner.getAdapter().getItemCount()){
                        currentItem = 0;
                    }
                    viewPagerBanner.setCurrentItem(currentItem,true);
                    handler.postDelayed(runnable,4500);
                };
                handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {
                Log.d("BBB", String.valueOf(t));
            }
        });
    }
    private void mapping() {
        viewPager2 = view.findViewById(R.id.viewpager);
        viewPagerBanner = view.findViewById(R.id.viewpagerBanner);
        circleIndicator3 = view.findViewById(R.id.indicator);
    }
}
