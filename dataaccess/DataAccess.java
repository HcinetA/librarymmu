package dataaccess;

import java.util.HashMap;
import java.util.List;
import business.Book;
import business.LibraryMember;
import business.Person;

public interface DataAccess {
	HashMap<String, Book> readBooksMap();
	HashMap<String, Person> readUserMap(); // Updated to use Person
	HashMap<String, LibraryMember> readMemberMap();

	void saveNewMember(LibraryMember member);
	void saveNewBook(Book book);

	LibraryMember getMember(String memberId);
	List<LibraryMember> getAllMembers();
	List<Book> getAllBooks();
}
