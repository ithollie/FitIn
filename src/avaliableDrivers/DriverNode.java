package avaliableDrivers;

import java.util.ArrayList;
import java.util.HashMap;

import passanger.PassangerNode;

public class DriverNode {
	
	public String  firstname;
	public String  lastname;
	public String  password;
	public String  email;
	public String  address;
	public String  country;
	public String  state;
	public String  zipCode;
	
	public int  _id;
	public String driver_lic;
	
	public String  carname;
	public String  make;
	public String  modal;
	public String  car_color;

	public DriverNode nextNode;
	
	int number_of_requests;
	String currentLocation;
	
	public HashMap<String, String> arrayList;
	public String[][] received_requests;
	
	
	public DriverNode(
			String firstname,
			String lastname,
			String password,
			String email,
			
			String address,
			String country,
			String  state,
			String  zipCode,
			
			int id,
			String driver_lic,

			String carname ,
			String carmake,
			String modal,
			String car_color,
			
			HashMap<String, String> driver1Array
			)   {
			this.firstname =  firstname;
			this.lastname = lastname;
			this.email 	  = email;
			this.password = password;
			this.address  = address;
			this.country     =  country;
			this.state		 =  state;
		
			this.carname 	 =  carname;
			this.make 		 =  carmake;
			this.modal 		 =  modal;
			this.driver_lic  = driver_lic;
			this._id   =  		id;
			this.car_color 	 =  car_color;
	
			this.zipCode 		 =  zipCode;
			this.nextNode = null;
			this.currentLocation = "226  Wright Avenue  Darby pa 19023";
		
			this.arrayList  = driver1Array;
			this.number_of_requests = 0;
		
		
		this.received_requests = new String[1][3];
	}
	public String getterFirstName() {
		return firstname;
	}
}
