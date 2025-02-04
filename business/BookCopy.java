package business;

import java.io.Serializable;
import java.util.Objects;

/**
 * Immutable class
 */
final public class BookCopy implements Serializable {

	private static final long serialVersionUID = -63976228084869815L;

	private final Book book;
	private final int copyNum;
	private boolean isAvailable;

	// Constructor to create a BookCopy with availability
	public BookCopy(Book book, int copyNum, boolean isAvailable) {
		if (book == null) {
			throw new IllegalArgumentException("Book cannot be null");
		}
		if (copyNum <= 0) {
			throw new IllegalArgumentException("Copy number must be a positive integer");
		}
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}

	// Constructor for BookCopy without availability set
	public BookCopy(Book book, int copyNum) {
		this(book, copyNum, true); // Default to available
	}

	// Check availability of the copy
	public boolean isAvailable() {
		return isAvailable;
	}

	// Get the copy number
	public int getCopyNum() {
		return copyNum;
	}

	// Get the associated book
	public Book getBook() {
		return book;
	}

	// Change the availability status
	public void changeAvailability() {
		isAvailable = !isAvailable;
	}

	// Override equals to compare book and copy number
	@Override
	public boolean equals(Object ob) {
		if (this == ob) return true;
		if (ob == null || getClass() != ob.getClass()) return false;
		BookCopy copy = (BookCopy) ob;
		return copyNum == copy.copyNum && book.equals(copy.book);
	}

	// Override hashCode to maintain consistency with equals
	@Override
	public int hashCode() {
		return Objects.hash(book, copyNum);
	}

	// Override toString for better readability
	@Override
	public String toString() {
		return "BookCopy{" +
				"book=" + book.getTitle() +
				", copyNum=" + copyNum +
				", available=" + isAvailable +
				'}';
	}
}
