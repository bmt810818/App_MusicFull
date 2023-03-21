package com.itplus.app_music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.itplus.app_music.Activity.DanhsachAllPlaylist_Activity;
import com.itplus.app_music.Activity.DanhsachbaihatActivity;
import com.itplus.app_music.Adapter.PlaylistAdapter;
import com.itplus.app_music.Model.PlayList;
import com.itplus.app_music.R;
import com.itplus.app_music.server.APIService;
import com.itplus.app_music.server.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Playlist_Fragment extends Fragment {
    View view;
    ListView lvPlaylist;
    TextView txtvTitleplaylist,txtvMorePlaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<PlayList> mangPlaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        mapping();
        GetData();

        return view;
    }

    private void mapping() {
        lvPlaylist = view.findViewById(R.id.lvPlaylist);
        txtvTitleplaylist = view.findViewById(R.id.txtvTitleplaylist);
        txtvMorePlaylist = view.findViewById(R.id.txtvMorePlaylist);
        txtvMorePlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DanhsachAllPlaylist_Activity.class);
            startActivity(intent);
        });
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayList>> listCall = dataservice.GetDataPlaylist();
        listCall.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangPlaylist = (ArrayList<PlayList>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(), android.R.layout.simple_list_item_1,mangPlaylist);
                lvPlaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvPlaylist);
                lvPlaylist.setOnItemClickListener((parent, view1, position, id) -> {
                    Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangPlaylist.get(position));
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {
                Log.d("AAA", String.valueOf(t));
            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
