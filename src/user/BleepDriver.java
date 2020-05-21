package user;

import java.sql.SQLException;

import Database.Application;

public class BleepDriver {
	public String  firstname;
	public String  lastname;
	public String  email;
	public String  password;
	
	public String address;
	public String country;
	public String state;
	public String zipCode;
	
	public int  id;
	public String carname; 
	public String make;
	public String modal;
	
	
	public String car_color;
	
	public String licence;
	
	public  BleepDriver(
			String name,
			String lastname,
			String password,
			String email, 
			String address,
			String country,
			String state,
			String zipCode,
			String driver_lic,
			int id ,
			String carname ,
			String make,
			String modal,
			String car_color
	) {

		this.firstname = name;
		this.lastname = lastname;
		this.password = password;
		this.email    = email;

		this.address = address;
		this.country = country;
		this.state	 = state;
		this.zipCode = zipCode;

		this.licence = driver_lic;
		this.id			= id;
		this.carname 	= carname;
		this.make 		= make;
		this.modal		= modal;
		
		this.car_color  = car_color;
	}
	
	public BleepDriver(String name, String lastname2, String password2, String email2, String address2, String country2,
			String state2, String zipCode2, String driver_lic, double id2_driver_one, String carname2, String make2,
			String modal2, String car_color2) {
		
	}

	public int  insert(int num) {
		return 0;
	}
	public boolean  check_id(int user_id) throws ClassNotFoundException, SQLException {
		Application app  = new  Application();
		return app.db.fetchId(Integer.toString(id));
	}
	
	public boolean check_user() throws ClassNotFoundException, SQLException {
		Application app  = new  Application();
		return app.db.fetchId(Integer.toString(id));
		
	}
	public   void display() {
		System.out.println("[");
		
		System.out.println( firstname);
		System.out.println( lastname);
		System.out.println( email);
		System.out.println( address);
		System.out.println( country);
		System.out.println( licence);
		
		System.out.println( make);
		System.out.println( modal);
		System.out.println(  state);
		System.out.println("]");
	}

	public boolean check_first_and_last_name(String firstname, String lastname) throws ClassNotFoundException, SQLException {
		Application app  = new  Application();
		return app.db.fetch_firstname_lastname(firstname, lastname);
	}


}
