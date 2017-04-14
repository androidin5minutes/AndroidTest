package renatoarg.androidtest.async_task;

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
import renatoarg.androidtest.model.Playlist;

/**
 * Created by renato on 11/04/2017.
 */

public class AsyncTask_getPlaylists extends AsyncTask<Void, Void, ArrayList<Playlist>> {

    private static final String TAG = "AsyncTask_getPlaylists";
    private String id_user;
    private String key_google;
    private ArrayList<Playlist> playlists;

    public AsyncTask_getPlaylists(String id_user, String key_google) {
        this.id_user = id_user;
        this.key_google = key_google;
        playlists = new ArrayList<>();
    }

    @Override
    protected ArrayList<Playlist> doInBackground(Void... params) {
        HttpURLConnection connection;

        try {
            URL url = new URL("https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId="+id_user+"&key="+key_google+"&maxResults=50&order=published");
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
                    Playlist playlist = new Playlist(jsonArray.getJSONObject(i).get("id").toString(), jsonArray.getJSONObject(i).getJSONObject("snippet").get("title").toString());
                    playlists.add(playlist);
                } catch (Exception e) {
                    Log.e(TAG, "something went wrong: " + e.getMessage());
                }
            }

            return playlists;
        } catch (Exception e) {
            Log.e(TAG, "MalformedURLException : " + e.getMessage());
            return null;
        }
    }
}
