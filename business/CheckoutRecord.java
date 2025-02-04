package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Manages a record of all the books checked out by a member.
 */
public class CheckoutRecord implements Serializable {
    private List<CheckoutRecordEntry> entries = new ArrayList<>();

    // Add a new entry to the checkout record
    public void addEntry(CheckoutRecordEntry entry) {
        entries.add(entry);
    }

    // Remove an entry from the checkout record
    public void removeEntry(CheckoutRecordEntry entry) {
        entries.remove(entry);
    }

    // Find a specific entry based on the BookCopy
    public CheckoutRecordEntry findEntryByBookCopy(BookCopy bookCopy) {
        for (CheckoutRecordEntry entry : entries) {
            if (entry.getBookCopy().equals(bookCopy)) {
                return entry;
            }
        }
        return null; // Not found
    }

    // Get all entries in the checkout record
    public List<CheckoutRecordEntry> getEntries() {
        return entries;
    }

    // Get the total number of checked-out books (entries)
    public int getTotalCheckedOutBooks() {
        return entries.size();
    }

    // Get the number of overdue books
    public int getOverdueBooks(Date currentDate) {
        int overdueCount = 0;
        for (CheckoutRecordEntry entry : entries) {
            if (entry.getDueDate().before(currentDate)) {
                overdueCount++;
            }
        }
        return overdueCount;
    }

    // Get a string representation of the checkout record
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CheckoutRecordEntry entry : entries) {
            sb.append(entry.getBookCopy().getBook().getTitle())
                    .append(" (Copy ")
                    .append(entry.getBookCopy().getCopyNum())
                    .append(") Due on ")
                    .append(entry.getDueDate())
                    .append("\n");
        }
        return sb.toString();
    }

    private static final long serialVersionUID = 1L;
}
