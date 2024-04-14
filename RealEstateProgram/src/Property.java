public class Property {
	
	private City city;
	private Street street;
	private int numberOfRooms;
	private int price;
	private String typeLand;
	private boolean forRent;
	private int houseNumber;
	private int floorNumber;
	private User owner;

	public static final String[] TYPE_FOR_LAND ={"Regular apartment", "Penthouse", "Private House" };
	
	public Property(User owner,City city, Street street,int floorNumber,int numberOfRooms, int houseNumber, boolean forRent, int price, String typeLand){
		this.owner = owner;
		this.city = city;
		this.street = street;
		this.floorNumber = floorNumber;
		this.numberOfRooms = numberOfRooms;
		this.houseNumber = houseNumber;
		this.forRent = forRent;
		this.price = price;
		this.typeLand = typeLand;
	}

	public String getTypeLand() {
		return typeLand;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public boolean isForRent() {
		return forRent;
	}

	public int getPrice() {
		return price;
	}
	public User getOwner() {
		return owner;
	}
	public String toString(){
		return getClass().getSimpleName() + "---> " + this.city + "|" +this.street + " " + this.houseNumber + "\n "
				+ this.typeLand + "|"  + "for rent? " + this.forRent + ": " + this.numberOfRooms + " rooms, floor " + this.floorNumber
				+ ".\n Price: " + this.price + "$."  + owner.toString();
	}

}
