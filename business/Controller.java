package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import dataaccess.Auth;
public class Controller implements ControllerInterface {

    private DataAccess dataAccess;

    // Constructor that initializes the DataAccess instance
    public Controller() {
        this.dataAccess = new DataAccessFacade(); // Assuming DataAccessFacade is the implementation of DataAccess
    }




    public boolean login(String username, String password) throws LoginException {
        Person person = getPersonByUsername(username); // Retrieve user by username
        if (person == null) {
            throw new LoginException("Username not found");
        }
        System.out.println("Found person: " + person.getUsername());
        System.out.println("Stored password: " + person.getPassword());
        System.out.println("Entered password: " + password);
        // Check the password is correct
        String storedPassword = person.getPassword(); // Retrieve the password from the person object
        if (storedPassword == null || !storedPassword.equals(password)) {
            throw new LoginException("Invalid password");
        }

        return true; // Return true if login is successful
    }



    private Person getPersonByUsername(String username) {
            // Fetch the map of users
            HashMap<String, Person> userMap = dataAccess.readUserMap();

            // Check if user exists in the map using username as the key
            return userMap.get(username);  // Returns null if not found
        }

    @Override
    public List<String> allMemberIds() {
        // Return all member IDs from the data source
        List<LibraryMember> members = dataAccess.getAllMembers(); // Adjusted to use LibraryMember
        List<String> memberIds = new ArrayList<>();
        for (LibraryMember member : members) {
            memberIds.add(member.getMemberId()); // Get memberId from LibraryMember
        }
        return memberIds;
    }


    @Override
    public List<String> allBookIds() {
        // Return all book IDs from the data source
        List<Book> books = dataAccess.getAllBooks(); // Assuming DataAccess has a method to get all books
        List<String> bookIds = new ArrayList<>();
        for (Book book : books) {
            bookIds.add(book.getIsbn()); // Get ISBN from Book
        }
        return bookIds;
    }
}
