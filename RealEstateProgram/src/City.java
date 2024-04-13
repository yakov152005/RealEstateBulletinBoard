public class City {
	private String nameCity;
	public final String[] district = {"Negev", "South", "Center","Sharon", "North"};
	private Street[] streets = new Street[0];
	public City(String nameCity){
		this.nameCity = nameCity;
	}

	public String getNameCity() {
		return nameCity;
	}
	public void addStreets(Street street){
		Street[] temp = new Street[streets.length + 1];
		for (int i = 0; i < streets.length; i++) {
			temp[i] = streets[i];
		}
		temp[streets.length] = street;
		streets = temp;
	}

	public Street[] getStreets() {
		return streets;
	}

	public String toString(){
		return this.nameCity;
	}
}