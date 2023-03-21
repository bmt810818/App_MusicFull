package com.itplus.app_music.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.itplus.app_music.Adapter.MyViewPagerAdapter;
import com.itplus.app_music.Fragment.Timkiem_Fragment;
import com.itplus.app_music.Fragment.TrangChu_Fragment;
import com.itplus.app_music.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    public static ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        viewPager2.setUserInputEnabled(false);
        initTablayout();
    }
    private void initTablayout() {
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        myViewPagerAdapter.addFragment(new TrangChu_Fragment(),"trang chu");
        myViewPagerAdapter.addFragment(new Timkiem_Fragment(),"tim kiem");
        viewPager2.setAdapter(myViewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {

        }).attach();

        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu).setText("trang chu");
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem).setText("tim kiem");
    }

    private void mapping() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager2 = findViewById(R.id.viewpager);
    }
}