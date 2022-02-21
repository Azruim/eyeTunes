package fi.experis.eyeTunes.dataAccess.models;

public class CustomerSpender extends Customer {
    private float totalSpending;

    public CustomerSpender(Long id, String firstName, String lastName, String country, String postalCode, String phoneNumber, String email, float totalSpending) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.totalSpending = totalSpending;
    }

    public float getTotalSpending() {
        return totalSpending;
    }
}
