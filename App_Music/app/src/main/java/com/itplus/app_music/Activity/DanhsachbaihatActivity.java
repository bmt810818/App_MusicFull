package com.itplus.app_music.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itplus.app_music.Adapter.DanhsachbaihatAdapter;
import com.itplus.app_music.Model.Album;
import com.itplus.app_music.Model.Baihathot;
import com.itplus.app_music.Model.PlayList;
import com.itplus.app_music.Model.QuangCao;
import com.itplus.app_music.Model.Theloai;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    QuangCao quangCao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgDanhsachcakhuc;
    ArrayList<Baihathot> baihatArraylist;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    PlayList playList;
    Theloai theloai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        Dataintent();
        mapping();
        initkt();
        if (quangCao != null && !quangCao.getTenbaihat().equals("")) {
            setValueInView(quangCao.getTenbaihat(), quangCao.getHinhbaihat());

            GetDataQuangcao(quangCao.getId());
        }
        if (playList != null && !playList.equals("")) {
            setValueInView(playList.getTen(), playList.getHinhnen());
            GetDataPlaylist(playList.getIdplaylist());
        }
        if (theloai != null && !theloai.equals("")) {
            setValueInView(theloai.getTentheloai(), theloai.getHinhtheloai());
            GetDatabhtheoTheloai(theloai.getIdtheloai());
        }
        if (album != null && !album.equals("")) {
            setValueInView(album.getTenalbum(), album.getHinhalbum());
            GetDataAlbum(album.getIdalbum());
        }
    }

    private void GetDataAlbum(String idalbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihathot>> listCall = dataservice.GetDanhsachbhtheoAlbum(idalbum);
        listCall.enqueue(new Callback<List<Baihathot>>() {
            @Override
            public void onResponse(Call<List<Baihathot>> call, Response<List<Baihathot>> response) {
                baihatArraylist = (ArrayList<Baihathot>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, baihatArraylist);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihathot>> call, Throwable t) {
                Log.d("FFF", String.valueOf(t));
            }
        });
    }

    private void GetDatabhtheoTheloai(String idtheloai) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihathot>> listCall = dataservice.GetDanhsachbhtheotheloai(idtheloai);
        listCall.enqueue(new Callback<List<Baihathot>>() {
            @Override
            public void onResponse(Call<List<Baihathot>> call, Response<List<Baihathot>> response) {
                baihatArraylist = (ArrayList<Baihathot>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, baihatArraylist);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihathot>> call, Throwable t) {
                Log.d("FFF", String.valueOf(t));
            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihathot>> listCall = dataservice.GetDanhsachbaihattheoplaylist(idplaylist);
        listCall.enqueue(new Callback<List<Baihathot>>() {
            @Override
            public void onResponse(Call<List<Baihathot>> call, Response<List<Baihathot>> response) {
                baihatArraylist = (ArrayList<Baihathot>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, baihatArraylist);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihathot>> call, Throwable t) {
                Log.d("FFF", String.valueOf(t));
            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        /*collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.get().load(hinh).into(imgDanhsachcakhuc);*/

        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            /*if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }*/
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgDanhsachcakhuc);
    }

    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihathot>> listCall = dataservice.GetDanhsachbaihattheoqc(idquangcao);
        listCall.enqueue(new Callback<List<Baihathot>>() {
            @Override
            public void onResponse(Call<List<Baihathot>> call, Response<List<Baihathot>> response) {
                baihatArraylist = (ArrayList<Baihathot>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, baihatArraylist);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihathot>> call, Throwable t) {

            }
        });
    }

    private void initkt() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void mapping() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarDanhsach);
        recyclerViewDanhsachbaihat = findViewById(R.id.rclvDanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgDanhsachcakhuc = findViewById(R.id.imgDanhsachcakhuc);

    }

    private void Dataintent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangCao.getTenbaihat(), Toast.LENGTH_SHORT).show();
            }
        }
        if (intent.hasExtra("itemplaylist")) {
            playList = (PlayList) intent.getSerializableExtra("itemplaylist");
        }
        if (intent.hasExtra("idtheloai")) {
            theloai = (Theloai) intent.getSerializableExtra("idtheloai");
        }
        if (intent.hasExtra("album")) {
            album = (Album) intent.getSerializableExtra("album");
            Toast.makeText(getApplicationContext(), album.getIdalbum(), Toast.LENGTH_SHORT).show();
        }
    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
            intent.putExtra("allbaihat", baihatArraylist);
            startActivity(intent);
        });
    }
}