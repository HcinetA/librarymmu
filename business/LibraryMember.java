package business;

import java.io.Serializable;
import java.util.Objects;
import dataaccess.Auth;
public final class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkoutRecord; // Single checkout record for each member
	private String password; // Password for authentication

	private static final long serialVersionUID = -2226197306790714013L;

	public LibraryMember(String memberId, String fname, String lname, String tel, Address add,String password,Auth role ) {
		super(fname, lname, tel, add,password,role);
		this.memberId = memberId;
		this.checkoutRecord = new CheckoutRecord(); // Initialize with an empty record
		this.password = password; // Set initial password
	}

	// Getters and Setters
	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	// Checkout a book by adding an entry
	public void checkoutBook(CheckoutRecordEntry entry) {
		if (entry == null) {
			throw new IllegalArgumentException("Checkout entry cannot be null.");
		}
		checkoutRecord.addEntry(entry); // Add book to the checkout record
	}

	// Overriding equals() and hashCode() to compare LibraryMember objects
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		LibraryMember other = (LibraryMember) obj;
		return Objects.equals(memberId, other.memberId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(memberId);
	}

	// Readable member info
	@Override
	public String toString() {
		return "LibraryMember {" +
				"ID='" + memberId + '\'' +
				", Name='" + getFirstName() + " " + getLastName() + '\'' +
				", Phone='" + getTelephone() + '\'' +
				", Address=" + getAddress() +
				'}';
	}
}
