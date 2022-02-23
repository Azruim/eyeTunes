package fi.experis.eyeTunes.dataAccess.models;

public class Song {
    private String songTitle;

    public Song(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }
}
