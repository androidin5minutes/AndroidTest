package renatoarg.androidtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

import renatoarg.androidtest.adapter.VideosAdapter;
import renatoarg.androidtest.async_task.AsyncTask_getVideos;
import renatoarg.androidtest.model.PlaylistVideos;


public class VideosActivity extends AppCompatActivity {

    private RecyclerView recyclerView_videos;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout linearLayout_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("androidtest", Context.MODE_PRIVATE);
        String id = getIntent().getStringExtra("id");
        String playlistName = getIntent().getStringExtra("playlist");
        if(id != null && playlistName != null) {                            // VideosActivity started from MainActivity
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("id", id);
            editor.putString("playlistName", playlistName);
            editor.commit();
        }

        getSupportActionBar().setTitle("Playlist");
        getSupportActionBar().setSubtitle(sharedPref.getString("playlistName", "none"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(VideosActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView_videos = (RecyclerView) findViewById(R.id.recyclerView_videos);
        recyclerView_videos.setLayoutManager(layoutManager);

        linearLayout_progressBar = (LinearLayout) findViewById(R.id.linearLayout_progressBar);
        linearLayout_progressBar.setVisibility(View.VISIBLE);

        AsyncTask_getVideos asyncTask_getVideos = new AsyncTask_getVideos(this, sharedPref.getString("id", "none")) {
            @Override
            protected void onPostExecute(ArrayList<PlaylistVideos> v) {
                super.onPostExecute(v);
                recyclerView_videos.setAdapter(new VideosAdapter(VideosActivity.this, v));
                linearLayout_progressBar.setVisibility(View.GONE);
            }
        };
        asyncTask_getVideos.execute();
    }
}
