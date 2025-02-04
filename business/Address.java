package business;

import java.io.Serializable;
import java.util.Objects;

/* Immutable */
final public class Address implements Serializable {

	private static final long serialVersionUID = -891229800414574888L;
	private final String street;
	private final String city;
	private final String state;
	private final String zip;
	private final String country; // Added country field

	// Constructor with country
	public Address(String street, String city, String state, String zip, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	// Getters
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	public String getCountry() {
		return country;
	}

	// Override toString for better display
	@Override
	public String toString() {
		return street + ", " + city + ", " + state + " " + zip + ", " + country;
	}

	// Override equals and hashCode for proper comparison and storing in collections
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Address address = (Address) obj;
		return Objects.equals(street, address.street) &&
				Objects.equals(city, address.city) &&
				Objects.equals(state, address.state) &&
				Objects.equals(zip, address.zip) &&
				Objects.equals(country, address.country);
	}

	@Override
	public int hashCode() {
		return Objects.hash(street, city, state, zip, country);
	}
}
