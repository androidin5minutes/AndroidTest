package renatoarg.androidtest.model;

/**
 * Created by renato on 11/04/2017.
 */

public class Playlist {

    private String id;
    private String title;

    public Playlist(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
