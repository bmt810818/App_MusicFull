package com.itplus.app_music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.itplus.app_music.Activity.DanhsachAllChuDetheoTheLoai_Activity;
import com.itplus.app_music.Activity.Danhsachalltheloai_Activity;
import com.itplus.app_music.Activity.DanhsachbaihatActivity;
import com.itplus.app_music.Model.Chude;
import com.itplus.app_music.Model.ChudeTheloai;
import com.itplus.app_music.Model.Theloai;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chude_Theloai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtvXemthem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai,container,false);
        horizontalScrollView = view.findViewById(R.id.hztScrollview);
        txtvXemthem = view.findViewById(R.id.txtvXemthem);
        txtvXemthem.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Danhsachalltheloai_Activity.class);
            startActivity(intent);
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChudeTheloai> chudeCall = dataservice.GetDataChudeTheloai();
        chudeCall.enqueue(new Callback<ChudeTheloai>() {
            @Override
            public void onResponse(Call<ChudeTheloai> call, Response<ChudeTheloai> response) {
                ChudeTheloai chudeTheloai = response.body();
                final ArrayList<Chude> chudeArrayList = new ArrayList<>();
                chudeArrayList.addAll(chudeTheloai.getChude());

                final ArrayList<Theloai> theloaiArrayList = new ArrayList<>();
                theloaiArrayList.addAll(chudeTheloai.getTheloai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(810,400);
                layoutParams.setMargins(10,20,10,30);
                for (int i =0;i<chudeArrayList.size();i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chudeArrayList.get(i).getHinhchude() != null){
                        Picasso.with(getContext()).load(chudeArrayList.get(i).getHinhchude()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalI = i;
                    imageView.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), DanhsachAllChuDetheoTheLoai_Activity.class);
                        intent.putExtra("theloai",theloaiArrayList.get(finalI));
                        startActivity(intent);
                    });
                }
                for (int j =0;j<theloaiArrayList.size();j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theloaiArrayList.get(j).getHinhtheloai() != null){
                        Picasso.with(getContext()).load(theloaiArrayList.get(j).getHinhtheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    int finalJ = j;
                    imageView.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                        intent.putExtra("idtheloai",theloaiArrayList.get(finalJ));
                        startActivity(intent);
                    });

                }
                    horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChudeTheloai> call, Throwable t) {
            }
        });
    }
}
