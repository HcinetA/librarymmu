package business;

import java.util.List;

public interface ControllerInterface {
	public boolean login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
}
