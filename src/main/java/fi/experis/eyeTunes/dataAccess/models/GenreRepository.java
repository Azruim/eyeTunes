package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenreRepository {
    private final String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public List<Genre> getRandomGenres(int amount) {
        List<Genre> genres = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT G.Name\n" +
                            "FROM Track T\n" +
                            "JOIN Genre G on G.GenreId = T.GenreId\n" +
                            "GROUP BY G.Name");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genres.add(new Genre(resultSet.getString(1)));
            }

            Collections.shuffle(genres);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return genres.subList(0, amount);
    }
}