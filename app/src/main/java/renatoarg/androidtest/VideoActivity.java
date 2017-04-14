package renatoarg.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import renatoarg.androidtest.async_task.AsyncTask_getVideo;
import renatoarg.androidtest.model.Video;
import renatoarg.androidtest.util.Utils;

public class VideoActivity extends AppCompatActivity {

    private static final String TAG = "VideoActivity";
    private LinearLayout linearLayout_progressBar;
    private ScrollView scrollView;
    private ImageView imageView_thumbnail;
    private TextView textView_video_title;
    private TextView textView_published_at;
    private TextView textView_description;
    private TextView textView_default_language;
    private TextView textView_default_audio_language;
    private TextView textView_live_broadcast_content;
    private TextView textView_tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getSupportActionBar().setTitle("Video");
        getSupportActionBar().setSubtitle(getIntent().getStringExtra("videoname"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        linearLayout_progressBar = (LinearLayout) findViewById(R.id.linearLayout_progressBar);
        linearLayout_progressBar.setVisibility(View.VISIBLE);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setVisibility(View.GONE);
        imageView_thumbnail = (ImageView) findViewById(R.id.imageView_thumbnail);
        textView_video_title = (TextView) findViewById(R.id.textView_video_title);
        textView_published_at = (TextView) findViewById(R.id.textView_published_at);
        textView_description = (TextView) findViewById(R.id.textView_description);
        textView_default_language = (TextView) findViewById(R.id.textView_default_language);
        textView_default_audio_language = (TextView) findViewById(R.id.textView_default_audio_language);
        textView_live_broadcast_content = (TextView) findViewById(R.id.textView_live_broadcast_content);
        textView_tags = (TextView) findViewById(R.id.textView_tags);

        AsyncTask_getVideo asyncTask_getVideo = new AsyncTask_getVideo(this, getIntent().getStringExtra("videoId")) {
            @Override
            protected void onPostExecute(Video v) {
                super.onPostExecute(v);
                if (v != null) {
                    textView_video_title.setText(v.getTitle());
                    textView_published_at.setText(getString(R.string.published_on, Utils.dateFormater(v.getPublishedAt())));
                    textView_description.setText(v.getDescription());
                    textView_default_language.setText(v.getDefaultLanguage());
                    textView_default_audio_language.setText(v.getDefaultAudioLanguage());
                    textView_live_broadcast_content.setText(v.getLiveBroadcastContent());
                    textView_tags.setText(getString(R.string.tags, v.getTags().replace("[", "").replace("]", "").replace(",", ", ")));
                    imageView_thumbnail.setImageBitmap(v.getThumb());
                    linearLayout_progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(VideoActivity.this, "Sorry, something is wrong with this video...", Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncTask_getVideo.execute();

    }
}
