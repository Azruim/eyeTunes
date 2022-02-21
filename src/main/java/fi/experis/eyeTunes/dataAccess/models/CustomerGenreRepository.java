package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;

public class CustomerGenreRepository {
    private final String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public ArrayList<CustomerGenre> getPopularCustomerGenre(String customerId) {
        ArrayList<CustomerGenre> customerGenres = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement(
                            "SELECT HighestGenre, max(Count) AS Max\n" +
                                    "FROM (\n" +
                                    "SELECT G.Name AS HighestGenre, count(G.Name) AS Count\n" +
                                    "FROM Customer C\n" +
                                    "JOIN Invoice I on C.CustomerId = I.CustomerId\n" +
                                    "JOIN InvoiceLine IL on i.InvoiceId = IL.InvoiceId\n" +
                                    "JOIN Track T on IL.TrackId = T.TrackId\n" +
                                    "JOIN Genre G on T.GenreId = G.GenreId\n" +
                                    "WHERE C.CustomerId = ?\n" +
                                    "GROUP BY G.Name\n" +
                                    "ORDER BY Count DESC);");

            preparedStatement.setString(1, customerId);

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerGenres.add(new CustomerGenre(
                        resultSet.getString("HighestGenre"),
                        resultSet.getInt("Max")
                ));
            }
            System.out.println("Top genres successfully selected");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customerGenres;
    }
}
