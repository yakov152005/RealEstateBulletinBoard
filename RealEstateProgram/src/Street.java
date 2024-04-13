public class Street {
	private  String nameStreet;
	public static final String[] streetsList ={
			"Herzl","Rothschild","David-remez","Ben-Gurion","Hananal","Menachem-Begin","Neve-Dekalim",
			"Yehuda-Levi","Ben-Zvi","Bialik","Yafa-nof","The-Reef"
	};
	public Street(String nameStreet){
		this.nameStreet = nameStreet;
	}

	public String toString(){
		return this.nameStreet;
	}

}