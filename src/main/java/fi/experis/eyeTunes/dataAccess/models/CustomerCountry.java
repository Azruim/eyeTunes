package fi.experis.eyeTunes.dataAccess.models;

public class CustomerCountry {
    private final String country;
    private final int customerCount;

    public CustomerCountry(String country, int customerCount) {
        this.country = country;
        this.customerCount = customerCount;
    }

    public String getCountry() {
        return country;
    }

    public int getCustomerCount() {
        return customerCount;
    }
}
