package renatoarg.androidtest.model;

import android.graphics.Bitmap;

/**
 * Created by renato on 11/04/2017.
 */

public class Video {

    String thumbnail;
    String title;
    String publishedAt;
    String videoId;
    String description;
    String liveBroadcastContent;
    String defaultLanguage;
    String defaultAudioLanguage;
    String tags;
    Bitmap thumb;

    public Video() {

    }

    public Video(String thumbnail, String title, String publishedAt, String videoId, String description, String liveBroadcastContent, String defaultLanguage, String defaultAudioLanguage, String tags) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.publishedAt = publishedAt;
        this.videoId = videoId;
        this.description = description;
        this.liveBroadcastContent = liveBroadcastContent;
        this.defaultLanguage = defaultLanguage;
        this.defaultAudioLanguage = defaultAudioLanguage;
        this.tags = tags;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getDefaultAudioLanguage() {
        return defaultAudioLanguage;
    }

    public void setDefaultAudioLanguage(String defaultAudioLanguage) {
        this.defaultAudioLanguage = defaultAudioLanguage;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }
}
