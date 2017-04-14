package renatoarg.androidtest.async_task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import renatoarg.androidtest.R;
import renatoarg.androidtest.util.Keys;
import renatoarg.androidtest.util.Utils;
import renatoarg.androidtest.model.Video;

/**
 * Created by renato on 11/04/2017.
 */

public class AsyncTask_getVideo extends AsyncTask<Void, Void, Video> {

    private static final String TAG = "AsyncTask_getVideo";
    private Context context;
    private String videoId;

    public AsyncTask_getVideo(Context context, String videoId) {
        this.context = context;
        this.videoId = videoId;
    }

    @Override
    protected Video doInBackground(Void... params) {
        HttpURLConnection connection;

        try {
            URL url = new URL("https://www.googleapis.com/youtube/v3/videos?id=" + videoId + "&part=snippet&key=" + Keys.KEY_GOOGLE);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            inputStream.close();
            connection.disconnect();

            JSONArray jsonArray = new JSONObject(stringBuffer.toString()).getJSONArray("items");

            Video video = new Video();
            for (int i = 0; i < jsonArray.length(); i++) {                                          // There will be only 1
                try {
                    try {
                        video.setTitle(jsonArray.getJSONObject(i).getJSONObject("snippet").get("title").toString());
                    } catch (Exception e) {
                        video.setTitle("No information");
                    }
                    try {
                        video.setPublishedAt(jsonArray.getJSONObject(i).getJSONObject("snippet").get("publishedAt").toString());
                    } catch (Exception e) {
                        video.setPublishedAt("No information");
                    }
                    try {
                        video.setDescription(jsonArray.getJSONObject(i).getJSONObject("snippet").get("description").toString());
                    } catch (Exception e) {
                        video.setDescription("No information");
                    }
                    try {
                        video.setLiveBroadcastContent(jsonArray.getJSONObject(i).getJSONObject("snippet").get("liveBroadcastContent").toString());
                    } catch (Exception e) {
                        video.setLiveBroadcastContent("No information");
                    }
                    try {
                        video.setDefaultLanguage(jsonArray.getJSONObject(i).getJSONObject("snippet").get("defaultLanguage").toString());
                    } catch (Exception e) {
                        video.setDefaultLanguage("No information");
                    }
                    try {
                        video.setDefaultAudioLanguage(jsonArray.getJSONObject(i).getJSONObject("snippet").get("defaultAudioLanguage").toString());
                    } catch (Exception e) {
                        video.setDefaultAudioLanguage("No information");
                    }
                    try {
                        video.setThumbnail(jsonArray.getJSONObject(i).getJSONObject("snippet").get("thumbnails").toString());
                    } catch (Exception e) {
                        video.setThumbnail("No information");
                    }
                    try {
                        video.setTags(jsonArray.getJSONObject(i).getJSONObject("snippet").get("tags").toString());
                    } catch (Exception e) {
                        video.setTags("No information");
                    }
                    try {
                        String mThumb = new JSONObject(new JSONObject(jsonArray.getJSONObject(i).getJSONObject("snippet").get("thumbnails").toString()).get(Utils.getScreenDensity(context)).toString()).get("url").toString();
                        URL thumb_url = new URL(mThumb);
                        connection = (HttpURLConnection) thumb_url.openConnection();
                        connection.connect();
                        inputStream = connection.getInputStream();
                        Bitmap thumb = BitmapFactory.decodeStream(inputStream);
                        video.setThumb(thumb);
                    } catch (Exception e) {
                        video.setThumb(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_play));
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            return video;
        } catch (Exception e) {
            return null;
        }
    }
}
