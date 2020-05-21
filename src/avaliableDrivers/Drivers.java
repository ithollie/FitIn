package avaliableDrivers;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;

import user.BleepDriver;

public class Drivers {
	
	public DriverNode first;
	public int  countDrivers;

	public Drivers() {
		this.first = null;
		this.countDrivers = 0;
	}
	public int nodeCount() {
		return  countDrivers;
	}
	public void insertDriver(BleepDriver driver, HashMap<String, String> driver1Array) {
		DriverNode  current = first;
		if(current == null) {
			DriverNode newNode =  new DriverNode(driver.firstname,driver.lastname,driver.password,driver.email,driver.address,driver.country,driver.state ,driver.zipCode,driver.id,driver.licence,driver.carname,driver.make,driver.modal,driver.car_color,driver1Array);
			first  = newNode;
			countDrivers++;
		}
		if (current != null) {
			DriverNode newNode =  new  DriverNode(
					driver.firstname,
					driver.lastname,
					driver.password,
					driver.email,

					driver.address,
					driver.country,
					driver.state ,
					driver.zipCode,

					driver.id,
					driver.licence,
					
					driver.carname,
					driver.make,
					driver.modal,
					driver.car_color,
			
					driver1Array
					
			);
			newNode.nextNode =  first;
			first =  newNode;
			countDrivers++;
		}
	}
	
	public   void displayDrivers() {
		DriverNode  current = first;
		while(current != null) {
			
				System.out.println(" name is " + current.firstname+ " " + current.lastname);
				System.out.println(" password is " + current.password);
				System.out.println(" drive lis is " + current.driver_lic);
				System.out.println(" drive id " + current._id);
				System.out.println(" make is    "    + current.make);
				System.out.println("<----- ----------->");
				current = current.nextNode;
				
			}	
	}
	public   void displayCount() {
		System.out.println("count = "+countDrivers);
		
				
	
	}
}
