package com.itplus.app_music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.itplus.app_music.Model.PlayList;
import com.itplus.app_music.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHodel {
        TextView txtvTenplaylist;
        ImageView imgPlaylist, imgBacgroundPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodel viewHodel = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHodel = new ViewHodel();
            viewHodel.txtvTenplaylist = convertView.findViewById(R.id.txtvTenplaylist);
            viewHodel.imgPlaylist = convertView.findViewById(R.id.imgPlaylist);
            viewHodel.imgBacgroundPlaylist = convertView.findViewById(R.id.imgBacgroundPlaylist);
            convertView.setTag(viewHodel);

        } else {
            viewHodel = (ViewHodel) convertView.getTag();
        }
        PlayList playList = getItem(position);
        //Picasso.get().load(playList.getHinhnen()).into(viewHodel.imgBacgroundPlaylist);
        //Picasso.get().load(playList.getHinhicon()).into(viewHodel.imgPlaylist);

        Picasso.with(getContext()).load(playList.getHinhnen()).into(viewHodel.imgBacgroundPlaylist);
        Picasso.with(getContext()).load(playList.getHinhicon()).into(viewHodel.imgPlaylist);

        viewHodel.txtvTenplaylist.setText(playList.getTen());

        return convertView;
    }
}
