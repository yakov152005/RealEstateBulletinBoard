import java.util.Scanner;
public class User {

	private String username;
	private String password;
	private String phoneNumber;
	private boolean isBroker;
	private int propertyCount; // מספר הנכסים שפורסמו על ידי המשתמש

	public static Scanner s = new Scanner(System.in);

	public User (String username, String password, String phoneNumber, boolean isBroker){
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.isBroker = isBroker;
	}
	
	//O(1)
	public boolean isBroker() {
		return isBroker;
	}
	//O(1)
	public boolean isUserNameEquals(String username){
		return this.username.equals(username);
	}
	//O(1)
	public boolean checkCredentials (String username, String password) {
		boolean success = false;
		if (this.username.equals(username) && this.password.equals(password)) {
			success = true;
		}
		return success;
	}
	public void setBroker(boolean broker) {
		isBroker = broker;
	}
	public void incrementPropertyCount() {
		propertyCount++; // עדכון מספר הנכסים שפורסמו על ידי המשתמש
	}
	public void reducePropertyCount() {
		propertyCount--; // עדכון מספר הנכסים שפורסמו על ידי המשתמש
	}
	public int getPropertyCount() {
		return propertyCount;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}

	public String toString(){
		return "\n " + getClass().getSimpleName() + "---> Contact info: " + this.username + " " + this.phoneNumber + ", Its real estate broker? " + this.isBroker;
	}
}
