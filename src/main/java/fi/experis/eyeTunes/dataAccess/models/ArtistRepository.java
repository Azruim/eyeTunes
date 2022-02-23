package fi.experis.eyeTunes.dataAccess.models;


import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArtistRepository {
    private final String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Name FROM Artist");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                artists.add(new Artist(
                        resultSet.getString("Name")
                ));
            }
            System.out.println("All artists selected");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        Collections.shuffle(artists);
        return artists.subList(0, 5);
    }
}
