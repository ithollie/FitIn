package avaliableDrivers;

import drivers.DriveClass;
import helper.Helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Database.Application;
import Database.Database;

public class Register extends DriveClass{
	
	public 	Drivers registerDriversLinkedList;
	public  ArrayList<String> arrayList;
	public  ArrayList<Integer> list;
	
	public  String firstname;
	public  String lastname;
	public  String email;
	public  String password;
	public  String address;
	public  String country;
	public  String state;
	public  String zipCode;
	public  int _id;
	public  String make;
	public  String driver_lic;
	public  String modal;
	public  Scanner scan;
	
	public Register(int driver_id, String firstname, String lastname, String email, String password, String address,String state, String country, String zipCode, Drivers registerDriversLinkedList, ArrayList<Integer> list) {
		super(driver_id, registerDriversLinkedList);
		this.firstname = firstname;
		this.lastname  = lastname;
		this.email 	   = email;
		this.password  = password;
		this.address = address;
		this.state		 = state;
		this.country	 = country;
		this.zipCode 		 = zipCode;
		this._id  =  	driver_id;
		this.registerDriversLinkedList = registerDriversLinkedList;
		this.scan = new Scanner(System.in);
	}
	
	public void insert(
			String carname,
			String carmake,
			String modal,
			String car_color,
			String driver_lic,
			HashMap<String, String> ct) throws ClassNotFoundException, SQLException {
			Application app = new Application();
		
			DriverNode newNode = new DriverNode(
				firstname,
				lastname,
				password,
				email,
				address,
				country,
				state,
				zipCode,
				driver_id,
				
				driver_lic,
				carname, 
				carmake,
				modal,
				car_color,
				ct
		);
		newNode.nextNode = registerDriversLinkedList.first;
		registerDriversLinkedList.first = newNode;
		app.db.insertUser(
				firstname,
				lastname, 
				email, 
				password ,
				address ,
				country ,
				state, 
				zipCode,
				driver_lic
		);
		
		System.out.println("Thank  you for registering");
	}
	public void userRegistration(String message, String message1, String driver_lic) throws ClassNotFoundException, SQLException {
		
		System.out.println(message1  + " " + " please enter car details");
		System.out.println(message + " " + " thank  you");
		
		System.out.println("Enter car name");
		String carname = scan.nextLine();
		
		System.out.println("Enter car color");
		String car_color  = scan.nextLine();
		
		System.out.println("Enter car make");
		String carmake  = scan.nextLine();
		
		System.out.println("Enter   car modal");
		String modal = scan.nextLine();
		
		Application database = new  Application();
		database.db.insertCarDetails(carname, car_color,carmake, modal);
		
		Scanner scans = null;
		
		if(!carname.isEmpty() && !car_color.isEmpty() && !carmake.isEmpty() && !modal.isEmpty()) {
			try {
				scans  =  new Scanner(System.in);
				
				System.out.println("Enter card owner  name");
				String card_user_name = scans.nextLine();
				
				System.out.println("Enter card number");
				String CARD_NUMBER  = scans.nextLine();
				
				System.out.println("Enter expiration  date");
				String expiration_date  = scans.nextLine();
				
				System.out.println("Enter security code");
				String code  = scans.nextLine();
				
				
				HashMap<String , String> registerMap  =  new HashMap<String, String>();
				
				if(!card_user_name.isEmpty() &&  !CARD_NUMBER.isEmpty()  && !expiration_date.isEmpty() &&  !code.isEmpty()) {
					
					database.db.insertPayment(card_user_name, CARD_NUMBER,expiration_date,code);

					registerMap.put("fullName",firstname + " " +lastname);
					registerMap.put("card_number", CARD_NUMBER);
					registerMap.put("expiration_date",expiration_date);
					registerMap.put("code",code);
					registerMap.put("email", email);
					registerMap.put("id", 	Integer.toString(_id));
					insert(carname,  carmake, modal, car_color, driver_lic, registerMap);
					carinformation();
					Helper  helper = new Helper();
					helper.driver("1", registerDriversLinkedList,list);
				}else {
					System.out.println("One or two field  is empty");
				}
			}catch(Exception  e) {
				System.out.println(e.toString());
			}finally {
				if(scans !=null) {
					scan.close();
				}
			}
		}else {
			userRegistration(message, message1,driver_lic);
		}
		
	}
	
	public  void carinformation() {
		DriverNode  current = registerDriversLinkedList.first;
		while(current != null) {
			System.out.println(current.firstname);
			System.out.println(current.lastname);
			System.out.println(current.email);
			current = current.nextNode;
		}
	}

	public int userRegistrations(String woo, String message, String message1, String driver_lic2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
