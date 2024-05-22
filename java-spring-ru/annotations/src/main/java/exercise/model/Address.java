package exercise.model;

import exercise.annotation.Inspect;

public class Address {
    private String city;
    private int postalCode;

    public Address(String city, int postalCode) {
        this.city = city;
        this.postalCode = postalCode;
    }

    // BEGIN
    
    // END
    public String getCity() {
        @Inspect
        return city;
    }

    // BEGIN
    
    // END
    public int getPostalCode() {
        @Inspect
        return postalCode;
    }

    public String getFullAddress() {
        return city + " " + postalCode;
    }
}
