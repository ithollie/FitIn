package avaliableDrivers;

import java.sql.SQLException;

import Database.Application;

public class DriverLogin {
	
	public  Drivers driverObject;
	public  String  driver_id;
	public  String  driver_password;
	
	public DriverLogin(String  driver_id, String driver_password, Drivers driverObject) {
		this.driverObject = driverObject;
		this.driver_id = driver_id;
		this.driver_password  = driver_password;
		
	}

	public void access() {
		// TODO Auto-generated method stub
		System.out.println("Driver's access is granted. Thank you for login in");
		System.out.println("  ");
		System.out.println("Driver's information");
		System.out.println("  ");
		displayList();
		System.out.println("Home Page");
	}
	
	
	public boolean checkUser(String driver_id) {
		DriverNode current  =  driverObject.first;
		boolean value =  false;
		while(current != null) {
			if(current._id == Integer.parseInt(driver_id)) {
				if(value == false) {
					value = true;
					return value;
				}
			}
			
			current  = current.nextNode;
		}
		return value;
	}
	public boolean checkPass(String pass) {
		DriverNode current  =  driverObject.first;
		boolean value =  false;
		while(current != null) {
			if(pass.equals(current.password)){
				if(value ==  false){
					value = true;
					return  value;
				}
				
			}
			current  = current.nextNode;
		}
		return value;
	}
	public void avaliableDriver() {
		
	}
	
	public void displayList() {
	    DriverNode current  =  driverObject.first;
		while(current != null){
			if(current._id == Integer.parseInt(driver_id)) {
				System.out.println("First name" + current.firstname);
				System.out.println("Last name " +  current.lastname);
				System.out.println("Driver's licence " + current._id);
				System.out.println("Car make " + current.make);
				System.out.println("Car modal " + current.modal);
			}
			current = current.nextNode;
		}
		
		
	}
	public void displayListFromDatabase() throws ClassNotFoundException, SQLException {
		Application app  =  new  Application();
		
	    DriverNode current  =  driverObject.first;
		while(current != null){
			if(current._id == Integer.parseInt(driver_id)) {
				System.out.println("First name" + current.firstname);
				System.out.println("Last name " +  current.lastname);
				System.out.println("Driver's licence " + current._id);
				System.out.println("Car make " + current.make);
				System.out.println("Car modal " + current.modal);
			}
			current = current.nextNode;
		}
		
		
	}
}
