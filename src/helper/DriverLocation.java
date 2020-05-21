package helper;

public class DriverLocation {
	public String currentLocation;
	public boolean locationSet;
	
	
	public DriverLocation(String currentLocation) {
		this.currentLocation =  currentLocation ;
		this.locationSet  =  false;
	}

	public void setCurrentLocation() {
		// TODO Auto-generated method stub
		locationSet =  true;
	}

}
