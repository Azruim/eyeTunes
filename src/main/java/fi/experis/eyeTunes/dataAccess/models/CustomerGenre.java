package fi.experis.eyeTunes.dataAccess.models;

public class CustomerGenre {
    private String genre;
    private int count;

    public CustomerGenre(String genre, int count) {
        this.genre = genre;
        this.count = count;
    }

    public String getGenre() {
        return genre;
    }

    public int getCount() {
        return count;
    }
}
