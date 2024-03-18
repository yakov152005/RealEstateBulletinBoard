import java.util.Scanner;
public class RealEstate {
	private User[] users;
	private Property[] properties;
	private City[] cities;
	public final int LENGTH_PHONE = 10;
	public static final int CHOICE_SIGN_UP = 1;
	public static final int CHOICE_LOGIN = 2;
	public static final int CHOICE_EXIT = 3;
	public static final int LOG_OUT = 6;
	public static Scanner s = new Scanner(System.in);
	public void addUser(User user) {
		User[] temp = new User[this.users.length + 1];
		for (int i = 0; i < this.users.length; i++) {
			temp[i] = this.users[i];
		}
		temp[temp.length - 1] = user;
		this.users = temp;
	}
	public void mainMenu(){
		System.out.println("|Welcome to the menu|");
		System.out.println("Please select the desired option:");
		System.out.println("""
				1 – Create a New account
				2 - Log in to existing account
				3 - Finish the program\s""");
	}
	public void menuUserLogin(){
		System.out.println("""
				|Are you interested|-->
				1 - publish a new property
				2 - remove advertising on a property
				3 - display all assets in the system
				4 - Show all properties published by the user
				5 - Search for a property by parameters
				6 - Log out and return to the main menu\s""");
	}

	public RealEstate() {
		this.users = new User[0];

		mainMenu();
		while (true) {
			int choice = s.nextInt();

			if (choice == CHOICE_EXIT) {
				break;
			}
			switch (choice) {
				case CHOICE_SIGN_UP: {
					User user = this.signUp();
					this.addUser(user);
					break;
				}
				case CHOICE_LOGIN: {
					User user = this.login();
					if (user!=null){
						boolean logout = false;
						menuUserLogin();
						while (!logout){
							int choiceUser = s.nextInt();
							switch (choiceUser){
								case 1:{

								}
								case 2:{

								}
								case 3:{

								}
								case 4:{

								}
								case 5:{

								}
								case 6:{
									System.out.println("Logout...");
									logout = true;
									break;
								}
								default:
									System.out.println("You entered a wrong number, try again.");
									break;
							}
							menuUserLogin();
						}
					}else {
						System.out.println("Incorrect username/password.");
						break;
					}
					break;
				}
				default:
					System.out.println("You entered a wrong number, try again.");
					break;
			}
			mainMenu();
		}
		System.out.println("|GOOD BYE|");
	}

	private User signUp () {

		boolean res = false;
		String username = null;
		do {
			System.out.println("Enter a username: ");
			username = s.nextLine();
			if (!isAvailableUserName(username)) {
				System.out.println("Username already taken. Please choose another username.");
			}
		} while (!isAvailableUserName(username));

		s.nextLine();
		String password = null;
		do {
			System.out.println("Enter a password: ");
			password = s.next();
			if (!isHardPassword(password)) {
				System.out.println("You entered a weak password, please enter a strong password!");
			}
		} while (!isHardPassword(password));

		s.nextLine();
		String phoneNumber = null;
		do {
			System.out.println("Enter a phone number: ");
			phoneNumber = s.next();
			if (!isCorrectCellPhoneNumber(phoneNumber)) {
				System.out.println("You entered an invalid cell phone number, please enter a valid cell phone number!");
			}
		} while (!isCorrectCellPhoneNumber(phoneNumber));

		System.out.println("Are you a regular user? (Y/N) ");
		char ch = s.next().charAt(0);
		boolean result = areYouBrokerUser(ch);

		User newUser = new User(username, password, phoneNumber, result);
		System.out.println("You have successfully entered a new user");
		return newUser;
	}
	public User login(){
		System.out.println("Enter a username: ");
		String username = s.next();
		System.out.println("Enter a password: ");
		String password = s.next();
		User found = null;
		for (int i = 0; i < this.users.length; i++) {
			if (this.users[i].checkCredentials(username, password)){
				found = this.users[i];
				break;
			}
		}
		return found;
	}
	public boolean isAvailableUserName(String username) {
		boolean available = true;
		for (int i = 0; i < this.users.length; i++) {
			if (this.users[i].getUsername().equals(username)) {
				available = false;
				break;
			}
		}
		return available;
	}
	public boolean checkIfPasswordContainCharacter(String password) {
		boolean res = false;
		final String[] CHARS = {"_", "$", "%"};
		for (int i = 0; i < CHARS.length; i++) {
			if (password.contains(CHARS[i])) {
				res = true;
				break;
			}
		}
		return res;
	}
	public boolean checkIfPasswordContainDigits(String password) {
		boolean res = false;
		for (int i = 0; i < password.length(); i++) {
			char currentChar = password.charAt(i);
				if (currentChar >= '0' && currentChar <= '9') {
					res = true;
					break;

			}
		}
		return res;
	}
	public boolean isHardPassword(String password) {
		boolean hardPassword = false;
		if (password.length() >= 5 && checkIfPasswordContainDigits(password) && checkIfPasswordContainCharacter(password)) {
			hardPassword = true;
		}
		return hardPassword;
	}
	public boolean itsAPhoneNumberWithOnlyDigits(String phoneNumber){
		boolean res = true;
		String digit ="0123456789";
		for (int i = 0; i < phoneNumber.length(); i++) {
			char currentChar = phoneNumber.charAt(i);
			if (!digit.contains(currentChar+ "")){
				res = false;
				break;
			}
		}
		return res;
	}
	public boolean isCorrectCellPhoneNumber(String phoneNumber){
		boolean correct = false;
		if (phoneNumber.length() == LENGTH_PHONE
				&& phoneNumber.startsWith("05") &&
				itsAPhoneNumberWithOnlyDigits(phoneNumber)){
			correct = true;
		}
		return correct;
	}
	public boolean areYouBrokerUser(char ch){
		boolean isBroker= false;
		boolean temp = false;
		while (!temp) {
			if (ch == 'Y' || ch == 'y') {
				isBroker = true;
				temp = true;
			} else if (ch == 'N' || ch == 'n') {
				temp = true;
			} else {
				System.out.println("You entered an incorrect character, your not broker.");
				break;
			}
		}
		return isBroker;
	}
	public boolean postNewProperty(User user){

		return false;
	}

}
