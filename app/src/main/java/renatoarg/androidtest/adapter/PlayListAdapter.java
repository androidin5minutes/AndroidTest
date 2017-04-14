package renatoarg.androidtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import renatoarg.androidtest.model.Playlist;
import renatoarg.androidtest.R;

/**
 * Created by renato on 11/04/2017.
 */

public class PlayListAdapter extends ArrayAdapter<Playlist> {

    public PlayListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PlayListAdapter(Context context, int resource, List<Playlist> playlists) {
        super(context, resource, playlists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_playlists, null);
        }

        Playlist p = getItem(position);

        if (p != null) {
            TextView playlist_title = (TextView) v.findViewById(R.id.textView_playlist_title);

            if (playlist_title != null) {
                playlist_title.setText(p.getTitle());
            }

        }

        return v;
    }

}