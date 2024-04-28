public class City {
	private String nameCity;
	private Street[] streets = new Street[0];

	public static final String[] citiesList = {"Tel-Aviv","Holon","Ashkelon","Eilat","Tiberias","Kiryat-Shmona",
			"Beer-Sheva","Ashdod","Be'er-Ya'akov","Ramat-Gan" };
	public final String[] district = {"Negev", "South", "Center","Sharon", "North"};

	public City(String nameCity){
		this.nameCity = nameCity;
	}

	//O(n)
	public void addStreets(Street street){
		Street[] temp = new Street[streets.length + 1];
		for (int i = 0; i < streets.length; i++) {
			temp[i] = streets[i];
		}
		temp[streets.length] = street;
		streets = temp;
	}

	public String getNameCity() {
		return nameCity;
	}
	public Street[] getStreets() {
		return streets;
	}

	public String toString(){
		return this.nameCity;
	}
}
