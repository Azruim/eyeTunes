package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerRepository {
    private final String URL = ConnectionHelper.CONNECTION_URL;
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
        return customers;
    }

    public Customer getCustomerByName(String name) {
        Customer customer = null;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ? OR LastName LIKE ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
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
        return customer;
    }

    public ArrayList<Customer> getNumberOfCustomers(String limit, String offSet) {
        ArrayList<Customer> customers = new ArrayList<>();
        if (limit == null) limit = "10";
        if (offSet == null) offSet = "10";

        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName , Country, PostalCode, Phone, Email FROM Customer LIMIT ? OFFSET ?");
            preparedStatement.setString(1, limit);
            preparedStatement.setString(2, offSet);
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
        return customers;
    }

    public Customer updateCustomer(Customer customer) {
        Customer updateCustomer = null;
        String valuesToUpdate = "";

        if (customer.firstName != null) {
            valuesToUpdate += "FirstName = ? ";
        } else if (customer.lastName != null) {
            valuesToUpdate += "LastName = ? ";
        } else if (customer.country != null) {
            valuesToUpdate += "Country = ? ";
        } else if (customer.postalCode != null) {
            valuesToUpdate += "PostalCode = ? ";
        } else if (customer.phoneNumber != null) {
            valuesToUpdate += "PhoneNumber = ? ";
        } else if (customer.email != null) {
            valuesToUpdate += "Email = ? ";
        }
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET \n"
                            + valuesToUpdate +
                            "OUTPUT Customer.CustomerId Customer.FirstName, \n" +
                            "Customer.LastName, Customer.Country, Customer.PostalCode, \n" +
                            "Customer.Phone, Customer.Email WHERE CustomerId = "+ customer.Id);
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                updateCustomer = new Customer(
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
        return updateCustomer;
    }
}
