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
import java.util.ArrayList;

import renatoarg.androidtest.util.Keys;
import renatoarg.androidtest.util.Utils;
import renatoarg.androidtest.model.PlaylistVideos;

/**
 * Created by renato on 11/04/2017.
 */

public class AsyncTask_getVideos extends AsyncTask<Void, Void, ArrayList<PlaylistVideos>> {

    private static final String TAG = "AsyncTask_getVideos";
    private Context context;
    private String id_user;
    private ArrayList<PlaylistVideos> videos;

    public AsyncTask_getVideos(Context context, String id_user) {
        this.context = context;
        this.id_user = id_user;
        videos = new ArrayList<>();
    }

    @Override
    protected ArrayList<PlaylistVideos> doInBackground(Void... params) {
        HttpURLConnection connection;

        try {
            URL url = new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+id_user+"&key=" + Keys.KEY_GOOGLE + "&maxResults=50&order=published");

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
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    PlaylistVideos playlistVideos = new PlaylistVideos();
                    playlistVideos.setTitle(jsonArray.getJSONObject(i).getJSONObject("snippet").get("title").toString());
                    playlistVideos.setThumbnail(jsonArray.getJSONObject(i).getJSONObject("snippet").get("thumbnails").toString());
                    String mThumb = new JSONObject(new JSONObject(jsonArray.getJSONObject(i).getJSONObject("snippet").get("thumbnails").toString()).get(Utils.getScreenDensity(context)).toString()).get("url").toString();
                    URL thumb_url = new URL(mThumb);
                    connection = (HttpURLConnection) thumb_url.openConnection();
                    connection.connect();
                    inputStream = connection.getInputStream();
                    Bitmap thumb = BitmapFactory.decodeStream(inputStream);
                    playlistVideos.setThumb(thumb);
                    playlistVideos.setVideoId(jsonArray.getJSONObject(i).getJSONObject("snippet").get("resourceId").toString());

                    videos.add(playlistVideos);
                } catch (Exception e) {
                    Log.e(TAG, "something went wrong for video " + jsonArray.getJSONObject(i).getJSONObject("snippet").get("title").toString());
                }
            }

            return videos;
        } catch (Exception e) {
            return null;
        }
    }
}
