package renatoarg.androidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;

import renatoarg.androidtest.adapter.PlayListAdapter;
import renatoarg.androidtest.async_task.AsyncTask_getPlaylists;
import renatoarg.androidtest.model.Playlist;
import renatoarg.androidtest.util.Keys;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout_progressBar;
    private ListView listView_playLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Android developers");
        getSupportActionBar().setSubtitle("Playlists");

        linearLayout_progressBar = (LinearLayout) findViewById(R.id.linearLayout_progressBar);
        listView_playLists = (ListView) findViewById(R.id.listView_playlists);
        listView_playLists.setVisibility(GONE);

        AsyncTask_getPlaylists asyncTask_getPlaylists = new AsyncTask_getPlaylists(Keys.ID_USER, Keys.KEY_GOOGLE) {
            @Override
            protected void onPostExecute(final ArrayList<Playlist> playlists) {
                super.onPostExecute(playlists);
                PlayListAdapter mAdapter = new PlayListAdapter(MainActivity.this, R.layout.row_playlists, playlists);
                listView_playLists.setAdapter(mAdapter);
                listView_playLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, VideosActivity.class);
                        i.putExtra("playlist", playlists.get(position).getTitle());
                        i.putExtra("id", playlists.get(position).getId());
                        startActivity(i);
                    }
                });
                linearLayout_progressBar.setVisibility(GONE);
                listView_playLists.setVisibility(VISIBLE);
            }
        };
        asyncTask_getPlaylists.execute();
    }
}
