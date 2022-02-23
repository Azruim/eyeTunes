package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import javax.naming.directory.SearchResult;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchRepository {

    private final String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public List<SearchItem> searchResults = new ArrayList<>();

    public List<SearchItem> searchSongsByString(String searchString) {
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT T.Name, Composer, A.Title, G.Name FROM Track T\n" +
                            "JOIN Album A on T.AlbumId = A.AlbumId\n" +
                            "JOIN Genre G on G.GenreId = T.GenreId\n" +
                            "WHERE T.Name LIKE ?");

            preparedStatement.setString(1, "%" + searchString + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                searchResults.add(new SearchItem(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return searchResults;
    }
}

