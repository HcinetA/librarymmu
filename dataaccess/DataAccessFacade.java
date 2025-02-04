package dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import business.Book;
import business.LibraryMember;
import business.Person;
public class DataAccessFacade implements DataAccess {

	private static final String STORAGE_PATH = "dataaccess/storage";  // Remove src from path


	// Method to get a member by their ID
	@Override
	public LibraryMember getMember(String memberId) {
		HashMap<String, LibraryMember> members = readMemberMap();
		return members.get(memberId);  // Retrieve member by memberId
	}

	// Method to get all members as a list
	@Override
	public List<LibraryMember> getAllMembers() {
		return new ArrayList<>(readMemberMap().values());  // Return all members as a list
	}

	@Override
	public HashMap<String, Person> readUserMap() {
		Object data = readFromStorage(StorageType.USERS);
		if (data == null) {
			return new HashMap<>();
		} else if (data instanceof ArrayList) {
			ArrayList<Person> userList = (ArrayList<Person>) data;
			HashMap<String, Person> userMap = new HashMap<>();
			for (Person person : userList) {
				userMap.put(person.getUsername(), person); // Use username as key
				System.out.println("Saving person: " + person.getUsername() + " with password: " + person.getPassword());

			}

			return userMap;
		} else if (data instanceof HashMap) {
			return (HashMap<String, Person>) data;
		} else {
			return new HashMap<>();
		}
	}



	// Method to get all books as a list
	@Override
	public List<Book> getAllBooks() {
		return new ArrayList<>(readBooksMap().values());  // Return all books as a list
	}

	// Method to add a new library member
	@Override
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> members = readMemberMap();
		members.put(member.getMemberId(), member);
		saveToStorage(StorageType.MEMBERS, members);
	}

	// Method to add a new book
	@Override
	public void saveNewBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		books.put(book.getIsbn(), book);
		saveToStorage(StorageType.BOOKS, books);
	}

	// Reads the member map from storage
	@Override
	public HashMap<String, LibraryMember> readMemberMap() {
		Object data = readFromStorage(StorageType.MEMBERS);

		// Check if data is null
		if (data == null) {
			return new HashMap<>();
		}

		// Check if data is an instance of ArrayList
		if (data instanceof ArrayList) {
			ArrayList<LibraryMember> memberList = (ArrayList<LibraryMember>) data;
			HashMap<String, LibraryMember> memberMap = new HashMap<>();

			// Assuming LibraryMember has a method getId() or similar to uniquely identify a member
			for (LibraryMember member : memberList) {
				memberMap.put(member.getMemberId(), member);  // Adjust to use the correct unique identifier for LibraryMember
			}

			return memberMap;
		}

		// If data is already a HashMap, return it directly
		if (data instanceof HashMap) {
			return (HashMap<String, LibraryMember>) data;
		}

		// Default return in case of unexpected data type
		return new HashMap<>();
	}


	// Reads the book map from storage
	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		Object data = readFromStorage(StorageType.BOOKS);
		if (data == null) {
			return new HashMap<>();
		} else if (data instanceof ArrayList) {
			ArrayList<Book> bookList = (ArrayList<Book>) data;
			HashMap<String, Book> bookMap = new HashMap<>();
			for (Book book : bookList) {
				bookMap.put(book.getIsbn(), book);  // assuming Book has a getId() method
			}
			return bookMap;
		} else if (data instanceof HashMap) {
			return (HashMap<String, Book>) data;
		} else {
			return new HashMap<>();
		}
	}

	// Saves data to storage
	public static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(STORAGE_PATH, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Reads data from storage
	public static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(STORAGE_PATH, type.toString());
			if (Files.exists(path)) {  // Ensure file exists before reading
				in = new ObjectInputStream(Files.newInputStream(path));
				retVal = in.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return retVal;
	}

	// Enum for storage types
    enum StorageType {
		BOOKS, MEMBERS,USERS;
	}
}
