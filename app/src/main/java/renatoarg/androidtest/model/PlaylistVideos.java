package renatoarg.androidtest.model;

import android.graphics.Bitmap;

/**
 * Created by renato on 11/04/2017.
 */

public class PlaylistVideos {

    String thumbnail;
    String title;
    String publishedAt;
    String videoId;
    Bitmap thumb;

    public PlaylistVideos(String thumbnail, String title, String publishedAt, String videoId) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.publishedAt = publishedAt;
        this.videoId = videoId;
    }

    public PlaylistVideos() {

    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }
}
