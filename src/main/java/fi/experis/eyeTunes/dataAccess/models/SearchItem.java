package fi.experis.eyeTunes.dataAccess.models;

public class SearchItem {
    private String song;
    private String artist;
    private String album;
    private String genre;

    public SearchItem(String song, String artist, String album, String genre) {
        this.song = song;
        this.artist = artist;
        this.album = album;
        this.album = album;
        this.genre = genre;
    }

    public String getSong() {
        return song;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }
}
