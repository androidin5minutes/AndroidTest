package renatoarg.androidtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import renatoarg.androidtest.VideoActivity;
import renatoarg.androidtest.R;
import renatoarg.androidtest.model.PlaylistVideos;


/**
 * Created by renato on 11/04/2017.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PlaylistVideos> videos;

    public VideosAdapter(Context context, ArrayList<PlaylistVideos> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public VideosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_videos, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideosAdapter.ViewHolder holder, final int position) {
        try {
            holder.linearLayout_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int mPosition = position;
                    try {
                        String videoId = new JSONObject(videos.get(mPosition).getVideoId()).get("videoId").toString();
                        Intent i = new Intent(context, VideoActivity.class);
                        i.putExtra("videoId", videoId);
                        i.putExtra("videoname", videos.get(position).getTitle());
                        context.startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.textView_video_title.setText(videos.get(position).getTitle());
            holder.imageView.setImageBitmap(videos.get(position).getThumb());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
            return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout_row;
        ImageView imageView;
        TextView textView_video_title;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout_row = (LinearLayout) itemView.findViewById(R.id.linearLayout_row);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_thumbnail);
            textView_video_title = (TextView) itemView.findViewById(R.id.textView_video_title);
        }
    }

}