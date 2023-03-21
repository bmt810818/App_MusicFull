package com.itplus.app_music.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.itplus.app_music.Adapter.ViewPagerPlaylistNhac;
import com.itplus.app_music.Fragment.Dianhac_Fragment;
import com.itplus.app_music.Fragment.Playbaihat_Fragment;
import com.itplus.app_music.Model.Baihathot;
import com.itplus.app_music.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarPlaynhac;
    TextView txtvtimesong,txtvTotalTimeSong;
    ViewPager2 vpg2Playnhac;
    SeekBar seekbarSong;
    ImageButton imgButtonSuffle,imgButtonpre,imgButtonPlay,imgButtonNext,imgButtonRepeat;
    public static ArrayList<Baihathot> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistNhac adapterNhac;
    Dianhac_Fragment dianhac_fragment;
    Playbaihat_Fragment playbaihat_fragment;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        GetdataFromIntent();
        mapping();
        eventClick();
    }

    private void eventClick() {
       Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (adapterNhac.getItem(1)!=null){
                if (mangbaihat.size()>0){
                    dianhac_fragment.Playnhac(mangbaihat.get(0).getHinhbaihat());
                    handler.removeCallbacks(this::eventClick);
                }else {
                    handler.postDelayed((Runnable) this,300);
                }
            }
        },500);
        imgButtonPlay.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                imgButtonPlay.setImageResource(R.drawable.iconplay);
            }else {
                mediaPlayer.start();
                imgButtonPlay.setImageResource(R.drawable.iconpause);
            }
        });
    }

    private void GetdataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                Baihathot baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
            }
            if (intent.hasExtra("allbaihat")){
                ArrayList<Baihathot> baihatArrayList = intent.getParcelableArrayListExtra("allbaihat");
                mangbaihat = baihatArrayList;
            }
        }
    }

    private void mapping() {
        toolbarPlaynhac = findViewById(R.id.toolbarPlaynhac);
        txtvtimesong = findViewById(R.id.txtvtimesong);
        txtvTotalTimeSong = findViewById(R.id.txtvTotalTimeSong);
        vpg2Playnhac = findViewById(R.id.vpg2Playnhac);
        seekbarSong = findViewById(R.id.seekbarSong);
        imgButtonSuffle = findViewById(R.id.imgButtonSuffle);
        imgButtonpre = findViewById(R.id.imgButtonpre);
        imgButtonPlay = findViewById(R.id.imgButtonPlay);
        imgButtonNext = findViewById(R.id.imgButtonNext);
        imgButtonRepeat = findViewById(R.id.imgButtonRepeat);
        setSupportActionBar(toolbarPlaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaynhac.setNavigationOnClickListener(v -> {
            finish();
        });
        toolbarPlaynhac.setTitleTextColor(Color.WHITE);
        dianhac_fragment = new Dianhac_Fragment();
        playbaihat_fragment = new Playbaihat_Fragment();
        adapterNhac = new ViewPagerPlaylistNhac(this);

        adapterNhac.AddFragment(playbaihat_fragment);
        adapterNhac.AddFragment(dianhac_fragment);
        vpg2Playnhac.setAdapter(adapterNhac);
        dianhac_fragment = (Dianhac_Fragment) adapterNhac.getItem(1);
        if (mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgButtonPlay.setImageResource(R.drawable.iconpause);
        }
    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.stop();
                mediaPlayer.reset();
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtvtimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekbarSong.setMax(mediaPlayer.getDuration());
    }
}