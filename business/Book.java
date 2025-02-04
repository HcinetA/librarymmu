package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final public class Book implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;

	private BookCopy[] copies;
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;

	// Constructor
	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("ISBN cannot be null or empty");
		}
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
		if (authors == null || authors.isEmpty()) {
			throw new IllegalArgumentException("At least one author is required");
		}

		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(new ArrayList<>(authors));  // Make authors list immutable
		this.copies = new BookCopy[]{new BookCopy(this, 1, true)};  // Start with a default copy
	}

	// Update the book copy (if needed)
	public void updateCopies(BookCopy copy) {
		for (int i = 0; i < copies.length; ++i) {
			BookCopy c = copies[i];
			if (c.equals(copy)) {
				copies[i] = copy;
			}
		}
	}

	// Get the copy numbers for all copies of the book
	public List<Integer> getCopyNums() {
		List<Integer> retVal = new ArrayList<>();
		for (BookCopy c : copies) {
			retVal.add(c.getCopyNum());
		}
		return retVal;
	}

	// Add a new copy to the book
	public void addCopy() {
		BookCopy[] newArr = new BookCopy[copies.length + 1];
		System.arraycopy(copies, 0, newArr, 0, copies.length);
		newArr[copies.length] = new BookCopy(this, copies.length + 1, true);  // New copy is added
		copies = newArr;
	}

	// Check if the book is available (any copy is available)
	public boolean isAvailable() {
		if (copies == null) {
			return false;
		}
		return Arrays.stream(copies)
				.map(BookCopy::isAvailable)
				.reduce(false, (x, y) -> x || y);
	}

	@Override
	public boolean equals(Object ob) {
		if (this == ob) return true;
		if (ob == null || getClass() != ob.getClass()) return false;
		Book b = (Book) ob;
		return isbn.equals(b.isbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	// Get a string representation of the book
	@Override
	public String toString() {
		String authorsList = authors.stream()
				.map(Author::getFullName)
				.reduce((a, b) -> a + ", " + b)
				.orElse("Unknown Author");
		return "Book [ISBN: " + isbn + ", Title: " + title + ", Authors: " + authorsList +
				", Max Checkout Length: " + maxCheckoutLength + ", Copies Available: " + getNumAvailableCopies() + "]";
	}

	// Get the number of copies available
	public int getNumAvailableCopies() {
		return (int) Arrays.stream(copies)
				.filter(BookCopy::isAvailable)
				.count();
	}

	public int getNumCopies() {
		return copies.length;
	}

	public String getTitle() {
		return title;
	}

	public BookCopy[] getCopies() {
		return copies;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public BookCopy getNextAvailableCopy() {
		Optional<BookCopy> optional = Arrays.stream(copies)
				.filter(BookCopy::isAvailable)
				.findFirst();
		return optional.orElse(null);
	}

	public Optional<BookCopy> getCopy(int copyNum) {
		return Arrays.stream(copies)
				.filter(c -> c.getCopyNum() == copyNum)
				.findFirst();
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
}
