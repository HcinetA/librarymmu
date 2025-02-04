package business;

import java.io.Serializable;
import java.util.Objects;
import dataaccess.Auth;

final public class Author extends Person implements Serializable {
	private static final long serialVersionUID = 7508481940058530471L; // Ensure consistency

	private String bio;

	// Constructor
	public Author(String f, String l, String t, Address a,String password,Auth role, String bio) {
		super(f, l, t, a,password,role);  // Calling the superclass (Person) constructor
		if (bio == null || bio.isEmpty()) {
			throw new IllegalArgumentException("Bio cannot be null or empty");
		}
		this.bio = bio;
	}

	// Getter for bio
	public String getBio() {
		return bio;
	}

	// Setter for bio (if you want to allow updating it)
	public void setBio(String bio) {
		if (bio == null || bio.isEmpty()) {
			throw new IllegalArgumentException("Bio cannot be null or empty");
		}
		this.bio = bio;
	}
	public String getFullName() {
		return getFirstName() + " " + getLastName();  // Assuming Person has getFirstName() and getLastName()
	}
	// Override toString for a more informative string representation
	@Override
	public String toString() {
		return "Author: " + getFirstName() + " " + getLastName() + ", Bio: " + bio;
	}

	// Override equals and hashCode for proper comparison and storing in collections
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Author author = (Author) obj;
		return Objects.equals(getFirstName(), author.getFirstName()) &&
				Objects.equals(getLastName(), author.getLastName()) &&
				Objects.equals(bio, author.bio) &&
				Objects.equals(getAddress(), author.getAddress());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFirstName(), getLastName(), bio, getAddress());
	}
}
