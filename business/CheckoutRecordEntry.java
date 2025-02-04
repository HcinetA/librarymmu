package business;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecordEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    private BookCopy bookCopy;  // Book copy being checked out
    private Date checkoutDate;  // Date the book was checked out
    private Date dueDate;       // Date the book is due to be returned
    private Date returnDate;    // Date the book was actually returned

    // Constructor to initialize a CheckoutRecordEntry
    public CheckoutRecordEntry(BookCopy bookCopy, Date checkoutDate, Date dueDate) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returnDate = null;  // Initially, returnDate is null (book hasn't been returned)
    }

    // Getters and setters for the fields
    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    // Method to check if the book has been returned
    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "BookCopy: " + bookCopy.getBook().getTitle() +
                ", Checkout Date: " + checkoutDate +
                ", Due Date: " + dueDate +
                ", Return Date: " + (returnDate != null ? returnDate : "Not returned yet");
    }
}
