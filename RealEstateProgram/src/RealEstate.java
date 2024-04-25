import java.util.Scanner;
public class RealEstate {

	private City[] cities;
	private User[] users;
	private Property[] properties;

	public static final int LENGTH_PHONE = 10;
	public static final int CHOICE_SIGN_UP = 1;
	public static final int CHOICE_LOGIN = 2;
	public static final int CHOICE_EXIT = 3;
	public static final int LOG_OUT = 6;
	public static Scanner s = new Scanner(System.in);

	//O(n)
	public void addUser(User user) {
		User[] temp = new User[this.users.length + 1];
		for (int i = 0; i < this.users.length; i++) {
			temp[i] = this.users[i];
		}
		temp[temp.length - 1] = user;
		this.users = temp;
	}
	//O(1)
	public void mainMenu() {
		System.out.println("|Welcome to the menu|");
		System.out.println("Please select the desired option:");
		System.out.println("""
				1 – Create a New account
				2 - Log in to existing account
				3 - Finish the program\s""");
	}
	//O(1)
	public void menuUserLogin() {
		System.out.println("""
				|Are you interested|-->
				1 - publish a new property
				2 - remove advertising on a property
				3 - display all assets in the system
				4 - Show all properties published by the user
				5 - Search for a property by parameters
				6 - Log out and return to the main menu\s""");
	}
	//O(n)
	public void setInitialBuildOnStart(){
		properties = new Property[0];

		cities = new City[]{
				new City("Tel-Aviv"),
				new City("Holon"),
				new City("Ashkelon"),
				new City("Eilat"),
				new City("Tiberias"),
				new City("Kiryat-Shmona"),
				new City("Beer-Sheva"),
				new City("Ashdod"),
				new City("Be'er-Ya'akov"),
				new City("Ramat-Gan")
		};
		Street[] streets ={
				new Street(Street.streetsList[0]),
				new Street(Street.streetsList[1]),
				new Street(Street.streetsList[2]),
				new Street(Street.streetsList[3]),
				new Street(Street.streetsList[4]),
				new Street(Street.streetsList[5]),
				new Street(Street.streetsList[6]),
				new Street(Street.streetsList[7]),
				new Street(Street.streetsList[8]),
				new Street(Street.streetsList[9]),
				new Street(Street.streetsList[10]),
				new Street(Street.streetsList[11])
		};

		for (int i : new int[]{1, 2, 3,}) {
			cities[0].addStreets(streets[i]);
		}

		for (int i : new int[]{3, 4, 5,}) {
			cities[1].addStreets(streets[i]);
		}
		for (int i : new int[]{5, 6, 7,}) {
			cities[2].addStreets(streets[i]);
		}
		for (int i : new int[]{8, 2, 3,}) {
			cities[3].addStreets(streets[i]);
		}
		for (int i : new int[]{1, 7, 4,}) {
			cities[4].addStreets(streets[i]);
		}
		for (int i : new int[]{6, 8, 5,}) {
			cities[5].addStreets(streets[i]);
		}
		for (int i : new int[]{0, 9,10,}) {
			cities[6].addStreets(streets[i]);
		}
		for (int i : new int[]{0, 8, 11,}) {
			cities[7].addStreets(streets[i]);
		}
		for (int i : new int[]{2, 4, 9,}) {
			cities[8].addStreets(streets[i]);
		}
		for (int i : new int[]{1, 5,10,}) {
			cities[9].addStreets(streets[i]);
		}

		this.users = new User[0];
	}
	//O(n)
	public RealEstate() {
		setInitialBuildOnStart();
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
					if (user != null) {
						boolean logout = false;
						menuUserLogin();
						while (!logout) {
							int choiceUser = s.nextInt();
							switch (choiceUser) {
								case 1: {
									boolean res = postNewProperty(user);
									if (res){
										System.out.println("The property has been saved successfully. ");
									}else {
										System.out.println("The property is not saved. ");
									}
									break;
								}
								case 2: {
									removeProperty(user);
									break;
								}
								case 3: {
									printAllProperties();
									break;
								}
								case 4: {
									printProperties(user);
									break;
								}
								case 5: {
									Property[] searchResult = search();
									if (searchResult.length == 0) {
										System.out.println("No properties found matching the specified criteria.");
									} else {
										System.out.println("Properties found: ");
										for (int i = 0; i < searchResult.length; i++) {
											System.out.println(searchResult[i].toString());
										}
									}
									break;
								}
								case LOG_OUT: {
									System.out.println("Logout...");
									logout = true;
									break;
								}
								default:
									System.out.println("You entered a wrong number, try again.");
									break;
							}
							if (!logout) {
								menuUserLogin();
							}
						}
					} else {
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
	//O(n)
	private User signUp() {
		String username = null;
		do {
			System.out.println("Enter a username: ");
			username = s.next();
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
				System.out.println("The password must be at least 5 characters long, contain at least one digit and at least one of the following characters: $, % or _. ");
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

		System.out.println("Are you a broker user? (Y/N) ");
		char ch = s.next().charAt(0);
		boolean result = areYouBrokerUser(ch);

		User newUser = new User(username, password, phoneNumber, result);
		System.out.println("You have successfully entered a new user");
		return newUser;
	}
	//O(n)
	public User login() {
		System.out.println("Enter a username: ");
		String username = s.next();
		System.out.println("Enter a password: ");
		String password = s.next();
		User found = null;
		for (int i = 0; i < this.users.length; i++) {
			if (this.users[i].checkCredentials(username, password)) {
				found = this.users[i];
				break;
			}
		}
		return found;
	}
	//O(n)
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
	//O(n)
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
	//O(n)
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
	//O(1)
	public boolean isHardPassword(String password) {
		boolean hardPassword = false;
		if (password.length() >= 5 && checkIfPasswordContainDigits(password) && checkIfPasswordContainCharacter(password)) {
			hardPassword = true;
		}
		return hardPassword;
	}
	//O(n)
	public boolean itsAPhoneNumberWithOnlyDigits(String phoneNumber) {
		boolean res = true;
		String digit = "0123456789";
		for (int i = 0; i < phoneNumber.length(); i++) {
			char currentChar = phoneNumber.charAt(i);
			if (!digit.contains(currentChar + "")) {
				res = false;
				break;
			}
		}
		return res;
	}
	//O(1)
	public boolean isCorrectCellPhoneNumber(String phoneNumber) {
		boolean correct = false;
		if (phoneNumber.length() == LENGTH_PHONE
				&& phoneNumber.startsWith("05") &&
				itsAPhoneNumberWithOnlyDigits(phoneNumber)) {
			correct = true;
		}
		return correct;
	}
	//O(n)
	public boolean areYouBrokerUser(char ch) {
		boolean isBroker = false;
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
	//O(n)
	public boolean postNewProperty(User user) {
		boolean res = true;
		int count = user.getPropertyCount();
		int countProperty = 0;

		if (!user.isBroker() && count >= 2) { // אם המשתמש רגיל והגיע למגבלה של שני נכסים
			System.out.println("You have reached the maximum limit for publishing properties.");
			countProperty++;
			res= false;
		} else if (user.isBroker() && count >= 5) { // אם המשתמש הוא מתווך והגיע למגבלה של חמישה נכסים
			System.out.println("You have reached the maximum limit for publishing properties.");
			countProperty++;
			res = false;
		}

		if (res) {
			System.out.println("List of cities--->");
			for (int i = 0; i < cities.length; i++) {
				System.out.println(this.cities[i].getNameCity());
			}

			System.out.println("Enter a city name from the list: ");
			String nameOfCity = s.next();

			int index = 0;
			int counter = 0;
			for (int i = 0; i < cities.length; i++) {
				if (nameOfCity.equalsIgnoreCase(cities[i].getNameCity())) {
					index = i;
					counter++;
					res = true;
					break;
				}else {
					res = false;
				}
			}


			int counter2 = 0;
			int index2 = 0;
			if (counter == 1 ) {
				System.out.println("List of streets in this city --->");
				for (int i = 0; i < cities[index].getStreets().length; i++) {
					System.out.println(cities[index].getStreets()[i]);
				}

				System.out.println("Enter a street name from the list: ");
				String nameOfStreet = s.next();

				for (int i = 0; i < cities[index].getStreets().length; i++) {
					if (nameOfStreet.equalsIgnoreCase(String.valueOf(cities[index].getStreets()[i]))) {
						index2 = i;
						counter2++;
						res = true;
						break;
					}else {
						res = false;
					}
				}
			}

			if (counter2 == 1 ) {
				propertyMenu();
				System.out.println("Enter choice: ");
				int choice = s.nextInt();
				boolean checkNumberValid = checkNumberValid(choice);
				if (!checkNumberValid) {
					System.out.println("You entered an incorrect number.");
					countProperty++;
					res = false;
				} else {
					int floor = 0;
					if (choice == 1 || choice == 2) {
						System.out.println("what is the floor? ");
						floor = s.nextInt();
					}

					System.out.println("how many rooms? ");
					int rooms = s.nextInt();
					System.out.println("What is the property number? ");
					int propertyNumber = s.nextInt();
					System.out.println("Is the property for rent/sale? (Y/N) ");
					char c = s.next().charAt(0);
					boolean forRent = false;
					if (c == 'y' || c == 'Y') {
						forRent = true;
					}
					System.out.println("What is the price for the property? ");
					long priceForProperty = s.nextLong();

					Property[] temp = new Property[properties.length + 1];
					for (int i = 0; i < properties.length; i++) {
						temp[i] = properties[i];
					}

					String typeForLand = typeForLand(choice);

					temp[properties.length] = new Property(user,this.cities[index],cities[index].getStreets()[index2],floor, rooms, propertyNumber, forRent, priceForProperty,typeForLand);
					properties = temp;
					user.incrementPropertyCount(); // עדכון מספר הנכסים שפורסמו על ידי המשתמש
				}
			} else {
				res = false;
			}
		}
		if (!res && countProperty == 0){
			System.out.println("The name typed is not in the list.");
		}

		return res;
	}
	//O(1)
	public String typeForLand(int choice){
		String typeForLand;
		if (choice == 1){
			typeForLand = "Regular apartment";
		} else if (choice ==2) {
			typeForLand = "Penthouse";
		}else {
			typeForLand = "Private House";
		}
		return typeForLand;
	}
	//O(n)
	public void propertyMenu(){
		System.out.println("What is the property type ---> ");
		for (int i = 0; i < Property.TYPE_FOR_LAND.length; i++) {
			System.out.println((i+1) + " - " + Property.TYPE_FOR_LAND[i]);
		}
	}
	//O(1)
	private boolean checkNumberValid(int choice){
		if (choice >=1 && choice <= 3){
			return true;
		}
		return false;
	}
	//O(n)
	public void removeProperty(User user) {

		if (user.getPropertyCount() == 0) {
			System.out.println("You have not published any property yet.");
			return;
		}

		System.out.println("Your properties:");
		Property[] userProperties = new Property[properties.length];
		int userPropertyIndex = 0;
		for (int i = 0; i < properties.length; i++) {
			if (properties[i] != null && properties[i].getOwner().equals(user)) { // בדיקה שהנכס לא ריק ושהוא שייך למשתמש
				System.out.println((userPropertyIndex + 1) + " - " + properties[i].toString());
				userProperties[userPropertyIndex++] = properties[i]; // ודא שנעשה שימוש ב-post-increment
			}
		}

		if (userPropertyIndex == 0) {
			System.out.println("You do not have any properties to remove.");
			return;
		}

		System.out.println("Which property do you want to remove?");
		int choice = s.nextInt() - 1;
		if (choice < 0 || choice >= userPropertyIndex) {
			System.out.println("Invalid choice.");
			return;
		}

		Property[] newProperties = new Property[properties.length - 1];
		int newIndex = 0;
		for (int i = 0; i < properties.length; i++) {
			if (!properties[i].equals(userProperties[choice])) {
				newProperties[newIndex++] = properties[i];
			} else {
				System.out.println("Removing property: " + properties[i].toString());
				user.reducePropertyCount(); // מקטין את ספירת הנכסים
			}
		}

		properties = newProperties;
		System.out.println("Properties deletion was successful.");
	}
	//O(n)
	public void printAllProperties() {
		boolean res = true;
		if (properties.length == 0) {
			System.out.println("No properties available.");
			res = false;
		}
		if (res) {
			System.out.println("All existing properties:");
			for (int i = 0; i < properties.length; i++) {
				if (properties[i] != null) { // בדיקה שהנכס לא ריק
					System.out.println((i + 1) + " - " + this.properties[i].toString());
				}
			}
		}
	}
	//O(n)
	public void printProperties(User user){
		if (properties.length == 0 || user.getPropertyCount() == 0){
			System.out.println("You have not published any property yet. ");
		}else {
			System.out.println("Properties published by you ---> ");
			int index = 1;
			for (int i = 0; i < properties.length; i++) {
				if (properties[i] != null && properties[i].getOwner().equals(user)) {
					System.out.println(index + " - " + properties[i].toString());
					index++;
				}
			}
		}
	}
	//O(n)
	public Property[] search(){
		System.out.println("Answer the following questions: ");
		System.out.println("Is it for rent/sale?(y/n) ");
		boolean isRent = false;
		String rentOrSale = s.next();
		if (rentOrSale.equalsIgnoreCase("y")){
			isRent = true;
		}
		System.out.println("What type of property do you want? ");
		propertyMenu();
		int choice = s.nextInt();
		String typeForLand = typeForLand(choice);

		System.out.println("What is the desired number of rooms? ");
		int numberRoom = s.nextInt();

		System.out.println("What is the desired price range (minimum and maximum)?" );
		int min = s.nextInt();
		int max = s.nextInt();

		int countForPropertiesArr = 0;
		for (int i = 0; i < properties.length; i++) {
			if (properties[i].isForRent() == isRent && properties[i].getTypeLand().equals(typeForLand) &&
					properties[i].getNumberOfRooms() == numberRoom &&
					properties[i].getPrice() >= min && properties[i].getPrice() <= max){
				countForPropertiesArr++;
			}
		}

		Property[] temp = new Property[countForPropertiesArr];
		int indexForTemp = 0;
		for (int i = 0; i < properties.length; i++) {
			if (properties[i].isForRent() == isRent && properties[i].getTypeLand().equals(typeForLand) &&
					properties[i].getNumberOfRooms() == numberRoom &&
					properties[i].getPrice() >= min && properties[i].getPrice() <= max){
				temp[indexForTemp] = properties[i];
				indexForTemp++;
			}
		}
		return temp;
	}
}
