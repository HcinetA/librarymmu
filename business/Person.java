package business;

import java.io.Serializable;
import dataaccess.Auth;
public class Person implements Serializable {
	private static final long serialVersionUID = 3665880920647848288L;
	private String firstName;
	private String lastName;
	private String telephone;
	private Address address;
	private Auth role; // Use the Auth enum here
	private String password;
	// Constructor to initialize Person object with role
	public Person(String f, String l, String t, Address a,String password, Auth role) {
		firstName = f;
		lastName = l;
		telephone = t;
		address = a;
		this.password = password;
		this.role = role; // Set the role
	}

	// Getters for the fields
	public String getFirstName() {
		return firstName;
	}

	public String getPassword() {
		return password;
	}
	public Auth getAuthorization() {  // Add this getter method
		return role;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public Address getAddress() {
		return address;
	}

	public Auth getRole() {
		return role; // Get the role
	}

	// Setters for the fields
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Auth role) {
		this.role = role; // Set the role
	}

	// toString method to represent the Person object as a string
	@Override
	public String toString() {
		return "Person [First Name: " + firstName + ", Last Name: " + lastName +
				", Telephone: " + telephone + ", Address: " + address + ", Role: " + role + "]";
	}

	public String getUsername() {
		return firstName;
	}
}
