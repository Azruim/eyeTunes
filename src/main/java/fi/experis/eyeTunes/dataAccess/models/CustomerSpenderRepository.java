package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;

public class CustomerSpenderRepository {

    private final String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public ArrayList<CustomerSpender> getTopCustomerSpenders() {
        ArrayList<CustomerSpender> customerSpenders = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement(
                            "SELECT C.CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email, sum(Total) AS Sum\n" +
                                    "FROM Customer C\n" +
                                    "JOIN Invoice I on C.CustomerId = I.CustomerId\n" +
                                    "GROUP BY  C.CustomerId\n" +
                                    "ORDER BY Sum DESC;");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerSpenders.add(new CustomerSpender(
                        resultSet.getLong("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getFloat("Sum")
                ));
            }
            System.out.println("Spender statistics successfully selected");
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
        return customerSpenders;
    }
}
