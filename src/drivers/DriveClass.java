package drivers;

import avaliableDrivers.Drivers;
import avaliableDrivers.DriverNode;


public class DriveClass {
	public int driver_id;
	public Drivers driverObject;
	
	public DriveClass(int driver_id,Drivers driverObject) {
		this.driver_id =  driver_id;
		this.driverObject = driverObject;
	}


	public boolean checkUser(int driver_id) {
		DriverNode current  =  driverObject.first;
		boolean value =  false;
		while(current != null) {
			if(current._id == driver_id) {
				if(value == false) {
					value = true;
					break;
				}
			}
			
			current  = current.nextNode;
		}
		return value;
	}

}