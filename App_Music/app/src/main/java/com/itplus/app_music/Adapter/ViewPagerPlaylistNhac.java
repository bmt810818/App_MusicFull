package com.itplus.app_music.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerPlaylistNhac extends FragmentStateAdapter {
    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public ViewPagerPlaylistNhac(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    public void AddFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }
}
