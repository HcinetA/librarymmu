package business;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Main {

	public static void main(String[] args) {
		System.out.println("Members with Zip Code containing '3': " + allWhoseZipContains3());
		System.out.println("Members with Overdue Books: " + allHavingOverdueBook());
		System.out.println("Books with Multiple Authors: " + allHavingMultipleAuthors());
	}

	// Returns a list of all IDs of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> libraryMembers = da.readMemberMap().values();

		return libraryMembers.stream()
				.filter(member -> member.getAddress().getZip().contains("3"))  // Check if the zip code contains "3"
				.map(LibraryMember::getMemberId)  // Get the member ID
				.collect(Collectors.toList());
	}

	// Returns a list of all IDs of LibraryMembers that have an overdue book

	public static List<String> allHavingOverdueBook() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> libraryMembers = da.readMemberMap().values();

		return libraryMembers.stream()
				.filter(member -> member.getCheckoutRecord().getEntries().stream()
						.anyMatch(entry -> {
							// Convert due date from Date to LocalDate
							LocalDate dueDate = entry.getDueDate().toInstant()
									.atZone(ZoneId.systemDefault())
									.toLocalDate();
							return dueDate.isBefore(LocalDate.now()) && entry.getReturnDate() == null; // Check for overdue
						}))
				.map(LibraryMember::getMemberId) // Get the member ID
				.collect(Collectors.toList());
	}

	// Returns a list of all ISBNs of Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.readBooksMap().values();

		return books.stream()
				.filter(book -> book.getAuthors().size() > 1)  // Check if the book has more than 1 author
				.map(Book::getIsbn) // Get the ISBN of the book
				.collect(Collectors.toList());
	}
}
