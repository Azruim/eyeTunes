package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerRepository {
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public Customer getCustomerById(String Id){
        Customer customer = null;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName , Country, PostalCode, Phone, Email FROM customer WHERE CustomerId = ?");
            preparedStatement.setString(1,Id);
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getLong("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }
            System.out.println("Select specific customer successful");
        }
        catch (Exception exception){
            System.out.println(exception);
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception);
            }
        }
        return customer;
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName , Country, PostalCode, Phone, Email FROM customer");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getLong("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                ));
            }
            System.out.println("Select specific customer successful");
        }
        catch (Exception exception){
            System.out.println(exception);
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception);
            }
        }
        return customers;
    }
}
