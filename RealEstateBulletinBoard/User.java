import java.util.Scanner;

public class User {
	private String username;
	private String password;
	private String phoneNumber;
	private boolean isBroker;

	public User (String username, String password, String phoneNumber, boolean isBroker){
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.isBroker = isBroker;
	}
	public boolean isUserNameEquals(String username){
		return this.username.equals(username);
	}
	public boolean checkCredentials (String username, String password) {
		boolean success = false;
		if (this.username.equals(username) && this.password.equals(password)) {
			success = true;
		}
		return success;
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
		return "Username: " + this.username +"\nPassword: " + this.password +
				"\nPhone number: " + phoneNumber + "\nIs Broker user? " + isBroker;
	}
	public static Scanner s = new Scanner(System.in);
}
