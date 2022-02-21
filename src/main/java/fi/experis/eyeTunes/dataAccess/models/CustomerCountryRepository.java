package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;

public class CustomerCountryRepository {
    private final String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public ArrayList<CustomerCountry> getCustomerCountByCountry() {
        ArrayList<CustomerCountry> customerCountries = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement(
                            "SELECT Country, count(Country) AS Count\n" +
                            "FROM Customer\n" +
                            "GROUP BY Country\n" +
                            "ORDER BY Count DESC;");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerCountries.add(new CustomerCountry(
                        resultSet.getString("Country"),
                        resultSet.getInt("Count")
                ));
            }
            System.out.println("Country statistics successfully selected");
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
        return customerCountries;
    }
}
