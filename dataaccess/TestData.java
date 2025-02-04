package dataaccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import business.Address;
import business.Author;
import business.Book;
import business.LibraryMember;
import business.Person;
import dataaccess.Auth;

public class TestData {

	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
		td.libraryMemberData();
		td.userData();

		DataAccess da = new DataAccessFacade();
		System.out.println(da.readBooksMap());
		System.out.println(da.readUserMap());
	}

	// Create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccessFacade.saveToStorage(DataAccessFacade.StorageType.BOOKS, allBooks);
	}

	public void userData() {
		DataAccessFacade.saveToStorage(DataAccessFacade.StorageType.USERS, allUsers);
	}

	// Create library members
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4), "password123", Auth.MEMBER);
		members.add(libraryMember);

		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5), "password123", Auth.LIBRARIAN);
		members.add(libraryMember);

		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6), "password123", Auth.MEMBER);
		members.add(libraryMember);

		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbán", "641-472-2871", addresses.get(7), "password123", Auth.MEMBER);
		members.add(libraryMember);

		DataAccessFacade.saveToStorage(DataAccessFacade.StorageType.MEMBERS, members);
	}


	// Data Lists
	List<LibraryMember> members = new ArrayList<>();

	List<Address> addresses = new ArrayList<>(Arrays.asList(
			new Address("101 S. Main", "Fairfield", "IA", "52556","USA"),
			new Address("51 S. George", "Georgetown", "MI", "65434","USA"),
			new Address("23 Headley Ave", "Seville", "Georgia", "41234","USA"),
			new Address("1 N. Baton", "Baton Rouge", "LA", "33556","USA"),
			new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736","USA"),
			new Address("1435 Channing Ave", "Palo Alto", "CA", "94301","USA"),
			new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556","USA"),
			new Address("501 Central", "Mountain View", "CA", "94707","USA")
	));

	List<Author> allAuthors = new ArrayList<>(Arrays.asList(
			new Author("Joe", "Thomas", "641-445-2123", addresses.get(0),"1234",Auth.AUTHOR,"A happy man is he."),
			new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0),"1234", Auth.AUTHOR,"A happy wife is she."),
			new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1),"1234", Auth.AUTHOR,"Thinker of thoughts."),
			new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2),"1234", Auth.AUTHOR,"Author of children's books."),
			new Author("Sarah", "Connor", "123-422-2663", addresses.get(3),"1234",Auth.AUTHOR, "Known for her clever style.")
	));

	List<Book> allBooks = new ArrayList<>(Arrays.asList(
			new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))),
			new Book("28-12331", "Antarctica", 7, Arrays.asList(allAuthors.get(2))),
			new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))),
			new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4)))
	));

	List<Person> allUsers = new ArrayList<>(Arrays.asList(
			new Person("101", "xyz", "94141684", addresses.get(0), "1234",Auth.LIBRARIAN),
			new Person("102", "abc", "94141684", addresses.get(0),  "1234",Auth.ADMIN),
			new Person("103", "111", "94141684", addresses.get(0), "1234", Auth.BOTH)
	));
	public boolean validateLogin(String username, String password) {
		for (Person user : allUsers) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
