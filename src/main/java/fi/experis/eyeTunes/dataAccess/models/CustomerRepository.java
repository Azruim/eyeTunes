package fi.experis.eyeTunes.dataAccess.models;

import fi.experis.eyeTunes.dataAccess.util.ConnectionHelper;

import java.sql.*;
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

    public Customer createNewCustomer(Customer customer) {
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement(
                            "INSERT INTO Customer (FirstName, LastName, Country, PostalCode, Phone, Email) VALUES (?, ?, ?, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.firstName);
            preparedStatement.setString(2, customer.lastName);
            preparedStatement.setString(3, customer.country);
            preparedStatement.setString(4, customer.postalCode);
            preparedStatement.setString(5, customer.phoneNumber);
            preparedStatement.setString(6, customer.email);

            // Execute Query
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                customer.setId(resultSet.getLong(1));
            }

            System.out.println("New customer successfully created");
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
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        ArrayList<String> valueArray = new ArrayList<>();

        if (customer.firstName != null) {
            valueArray.add("FirstName = '" + customer.firstName + "'");
        } if (customer.lastName != null) {
            valueArray.add("LastName = '"+ customer.lastName + "'");
        } if (customer.country != null) {
            valueArray.add("Country = '"+ customer.country + "'");
        } if (customer.postalCode != null) {
            valueArray.add("PostalCode = '"+ customer.postalCode + "'");
        } if (customer.phoneNumber != null) {
            valueArray.add("Phone = '"+ customer.phoneNumber + "'");
        } if (customer.email != null) {
            valueArray.add("Email = '"+ customer.email + "'");
        }
        String valuesToUpdate = String.join(", ", valueArray);

        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET "
                            + valuesToUpdate +
                            " WHERE CustomerId = " + customer.Id);
            // Execute Update
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return getCustomerById(customer.Id.toString());
    }
}
