package user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Database.Application;

public class BleepPassanger {
	public String  firstname;
	public String  lastname;
	public String  email;
	public String  password;
	public String  address;
	public String  licence;
	public String  country;
	public String  state;
	public String  zipCode;
	
	public String currentLocation;
	public int  _id;
	public HashMap<String, String> hashMapArray;
	
	public  BleepPassanger(
			String firstname,
			String lastname,
			String email,
			String password,
			String address,
			String country,
			String state,
			String zipCode,
			String licence,
			int passanger_id1,
			HashMap<String, String> passanger1Array,
			String currentLocation
			
			) {
		
		this.firstname = firstname;
		this.lastname =  lastname;
		this.password =  password;
		this.email    =  email;
	
		this.address  =  address;
		this.country  =  country;
		this.state	  =  state;
		this.zipCode  =  zipCode;
		
		this._id 	  =  passanger_id1;
		this.currentLocation = currentLocation;
		this.hashMapArray  = passanger1Array;
		
	}
	
	public boolean check_first_and_last_name(String firstname, String lastname) throws ClassNotFoundException, SQLException {
		Application app  = new  Application();
		return app.db.fetch_firstname_lastname(firstname, lastname);
	}
	public void setMapArray(HashMap<String, String> hashmap) {
		hashMapArray =  hashmap;
	}
	public int  insert(int num) {
		return 0;
	}
	public boolean  check_id(int user_id) throws ClassNotFoundException, SQLException {
		Application app  = new  Application();
		return app.db.fetchIdPassanger(Integer.toString(user_id));
	}
	public boolean  check_id_passanger(int user_id) throws ClassNotFoundException, SQLException {
		Application app  = new  Application();
		return app.db.fetchIdPassanger(Integer.toString(user_id));
	}
	public   void display() {
		System.out.println("[");
		
		System.out.println( firstname);
		System.out.println( lastname);
		System.out.println( email);
		System.out.println( licence);
		System.out.println(  state);
		System.out.println("]");
	}
	
	public boolean isEmptyLinkedList() {
		boolean value =  true;
		HashMap<String, String> current = hashMapArray;
		if (current.isEmpty()) {
			value = false;
		}
		return value;
	}
}
