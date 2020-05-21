package passanger;
import java.util.ArrayList;
import java.util.HashMap;

import user.BleepDriver;

public class PassangerNode {
	
	public String  firstname;
	public String  lastname;
	public String  password;
	public String  email;
	public String  address;
	public String  drivers_licence;
	public int  _id;
	public double card;
	public String state;
	public String country;
	public String currentLocation;
	
	
	public int passanger_lis;
	public int number_of_requests;
	public HashMap<String, String> map;
	public PassangerNode nextNode;
	
	
	public PassangerNode(
			String firstname, 
			String lastname,
			String password ,
			String email,
			String address,
			String state,
			String country,
			String zipCode, 
			int _id2,
			String driver_licence,
			
			String currentLocation,
			HashMap<String, String> map
			
	)    {
		this.firstname =  firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email; 
		this.address = address;
		this.drivers_licence= driver_licence;
		this._id  =  _id2;
		this.state	= 	    state;
		this.country =  country;
		this.currentLocation =  currentLocation;
		this.map 	= map;
		this.nextNode = null;
		
	}
}
