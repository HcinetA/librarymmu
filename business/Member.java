package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private Address address;
    private List<CheckoutRecord> checkoutRecords; // List to store all checkout records for the member

    // Constructor
    public Member(String id, String firstName, String lastName, String password, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.checkoutRecords = new ArrayList<>(); // Initialize with an empty list of checkout records
    }

    // Getters and Setters for the fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CheckoutRecord> getCheckoutRecords() {
        return checkoutRecords;
    }

    public void setCheckoutRecords(List<CheckoutRecord> checkoutRecords) {
        this.checkoutRecords = checkoutRecords;
    }

    // Method to add a checkout record to the member's records
    public void addCheckoutRecord(CheckoutRecord record) {
        checkoutRecords.add(record);
    }

    // Method to retrieve the most recent checkout record
    public CheckoutRecord getMostRecentCheckoutRecord() {
        if (checkoutRecords.isEmpty()) {
            return null;
        }
        return checkoutRecords.get(checkoutRecords.size() - 1);
    }

    // Method to validate login by comparing ID and password
    public boolean validateLogin(String password) {
        return this.password.equals(password);
    }

    // Method to check if the member has any active checkout records
    public boolean hasActiveCheckout() {
        return checkoutRecords.stream()
                .anyMatch(record -> record.getEntries().stream()
                        .anyMatch(entry -> entry.getDueDate() != null && entry.getReturnDate() == null));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Member member = (Member) obj;
        return id != null && id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Member ID: " + id + ", Name: " + firstName + " " + lastName + ", Address: " + address;
    }
}
