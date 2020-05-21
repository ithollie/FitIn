package helper;

import java.util.Scanner;

import Database.Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

import avaliableDrivers.DriverLogin;
import avaliableDrivers.DriverNode;
import avaliableDrivers.Drivers;
import avaliableDrivers.Register;
import drivers.DriveClass;
import passanger.PassangerLogin;
import passanger.Passangers;


public class Helper{
	
	private static final int Integer = 0;

	public Helper() {
		
	}
	public boolean  condition(String reg) {
		boolean value = false;
		
		for(int  i  = 0; i  < reg.length() ;  i++) {
			if(reg.charAt(i) < 40) {
				value = true;
			}
			
		}
		return value;
	}
	
	public  void driver(String cts , Drivers registerDriversLinkedList, ArrayList<Integer> list) throws SQLException, ClassNotFoundException {
			boolean condition  = true;
			Application app = new Application();
			
			Scanner scan  = null;
			scan = new Scanner(System.in);
			
			if(java.lang.Integer.parseInt(cts) == 0  &&  condition ==  true) {
				
				System.out.println("Enter driver  id and password to login as a driver");
				
				System.out.println("Enter driver's  id");
				String id_driver = scan.nextLine();
				
				System.out.println("Enter driver's password");
				String driver_password = scan.nextLine();
				
				String currentLocation  =  id_driver + "location";
				
				DriverLocation location = new DriverLocation(currentLocation);
				location.setCurrentLocation();
				
				System.out.println("look here");
	
				if(location.locationSet == true ) {
					System.out.println("Location is Set and driver  is not available");
				}
				DriverLogin login =  new DriverLogin(id_driver.toString(),driver_password.toString(), registerDriversLinkedList);
				
				//boolean  true or false statements
				boolean  id_key =   (!id_driver.isEmpty())?true:false;
				boolean  password_key = (!driver_password.isEmpty())?true:false;
				
				boolean id_num = (id_driver.charAt(0) > 40)?true:false;
				boolean id_len = (id_driver.length() > 0 )?true:false;
				
				boolean password_len =  (driver_password.length() > 0)?true:false;
				//end of boolean  true or false statements
				
				if(id_num == true && (id_key && password_key) == true && id_len == true && password_len == true) {
					
					System.out.println("user id =" + login.checkUser(id_driver) + " " + "user password  =" + login.checkPass(driver_password));
					if(login.checkUser(id_driver) ==  true && login.checkPass(driver_password) ==  true) {
						login.access();
					}else {
						
						System.out.println("condition is " + login.checkUser(id_driver));
						
						Random  driver  = new Random();
						LinkedHashSet<Integer> newSet  = new LinkedHashSet<Integer>();
						newSet.add(driver.nextInt(4000));
						ArrayList<Integer> newList = new ArrayList<Integer>(newSet);
							
						id_checker(list,  newList);
						
						if(id_checker(list,  newList) != true) {
									
									int id = newList.get(0);
									System.out.println("new  generated id " +  id);
									
									System.out.println("You are not a valiable user please register");
									System.out.println(" ..................................");
			
									System.out.println("Enter user first name");
									String firstname = scan.nextLine();
									
									System.out.println("Enter  user lastname");
									String lastname  = scan.nextLine();
									
									System.out.println("Enter  user email");
									String email  = scan.nextLine();
									
									System.out.println("Enter  user password");
									String password  = scan.nextLine();
									 
									System.out.println("Enter user home address");
									String address = scan.nextLine();
									
									System.out.println("Enter  a country");
									String country  = scan.nextLine();
									
									System.out.println("Enter user  state");
									String state  =  scan.nextLine();          
									
									System.out.println("Enter zip code");
									String zip = scan.nextLine();
									
									System.out.println("Enter driver's lic");
									String driver_lic = scan.nextLine();
									
									
									DriveClass  extra  = new DriveClass(id,registerDriversLinkedList);
								
									if(!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty() &&  !address.isEmpty() && !state.isEmpty() && !zip.isEmpty() &&  !driver_lic.isEmpty()) {
										if(extra.checkUser(id) == false) {
											app.db.insertDriver(firstname, lastname,password,email, address,country,state,zip ,driver_lic,id);
											avaliableDrivers.Register  register = new Register(id, firstname ,  lastname, email, password,address, state, country,  zip,   registerDriversLinkedList,list);
											register.userRegistration("Enter car information","Thank you for completing the first section of the registration process",  driver_lic );
										}else {
											System.out.println("user already  taken");
										}
									}else {
										System.out.println("user id or password is empty or dont meet the critirial");
										
									}
								}
							
								if(scan !=  null) {
									scan.close();
								}
							
					}
					
			}
		}
	}
	public boolean id_checker(ArrayList<Integer> oldList, ArrayList<Integer> newList) throws ClassNotFoundException, SQLException{
		boolean  condition  =  false;
		Application app  = new Application();
		if(app.db.fetchAllId().size() > 0) {
			for(int i  = 0; i  <  app.db.fetchAllId().size();i++) {
				if(app.db.fetchAllId().get(i) ==  newList.get(0) ) {
					condition = true;
				}
			}
		}
		return condition;
	}
	
	public void passanger(String  cts, Passangers passangesLinkedList, Drivers driversLinkedList, ArrayList<Integer> listSet ) throws ClassNotFoundException, SQLException {
			boolean   condition = true;
			Scanner scans  = null;
			Application  app =  new Application();
			
			if (java.lang.Integer.parseInt(cts) == 1 && condition == true && driversLinkedList.nodeCount() > 0) {
					scans = new  Scanner(System.in);
					try {
			
						System.out.println("Enter passanger id and password to   login  as a passanger");
						
						System.out.println("Enter user id");
						String passanger_id = scans.nextLine();
						
						System.out.println("Enter user password");
						String passanger_password = scans.nextLine();
						
						String  currentLocation = "philly" +  passanger_id;
					
						DriverLocation location = new DriverLocation(currentLocation);
						location.setCurrentLocation();
						
						Passangers  passanger = new Passangers();
						
						System.out.println("boolean  value is " + " " + passanger.checkUser(passanger_id));
						if(passanger.checkUser(passanger_id) == true ) {
							PassangerLogin  passangerLogin = new PassangerLogin(passanger_id,passanger_password, driversLinkedList, passangesLinkedList, currentLocation);	
							passangerLogin.access(passangesLinkedList,  java.lang.Integer.parseInt(passanger_id));
						
						}else {
							
								System.out.println("You are not a valiable user please register");
								System.out.println(" ..................................");
								
								Application insert_new_id =  new Application();
								Application check_id  	  =  new Application();
								
								Random  driver  = new Random();
								LinkedHashSet<Integer> newSet  = new LinkedHashSet<Integer>();
								newSet.add(driver.nextInt(4000));
								ArrayList<Integer> newList = new ArrayList<Integer>(newSet);
								
								
	
								if(id_checker(listSet,  newList) !=true && check_id.db.fetchId(passanger_id) != true) {
									
									insert_new_id.db.insertIds(newList.get(0));

									int id = newList.get(0);
									
									System.out.println("Enter user first name");
									String firstname = scans.nextLine();
									
									System.out.println("Enter  user lastname");
									String lastname  = scans.nextLine();
									
									System.out.println("Enter  user email");
									String email  = scans.nextLine();
									
									System.out.println("Enter  user password");
									String password  = scans.nextLine();
									 
									System.out.println("Enter user home address");
									String address = scans.nextLine();
									
									System.out.println("Enter  a country");
									String country  = scans.nextLine();
									
									System.out.println("Enter user  state");
									String state  =  scans.nextLine();          
									
									System.out.println("Enter zip code");
									String zip = scans.nextLine();
									
									System.out.println("Enter driver's licence");
									String driver_lic = scans.nextLine();
								
									if(!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty() &&  !address.isEmpty() && !state.isEmpty() && !zip.isEmpty() && driver_lic.isEmpty()) {
										if(driver_lic.isEmpty() == true) {
											app.db.insertPassanger(firstname,lastname, email,password,address, country, state,zip ,null,id);
											PassangerLogin  passangerLogin = new PassangerLogin(java.lang.Integer.toString(id),password, driversLinkedList, passangesLinkedList, currentLocation);	
											passangerLogin.access(passangesLinkedList,  java.lang.Integer.parseInt(passanger_id));
									
										}else {app.db.insertPassanger(firstname,lastname, email,password,address, country, state,zip ,driver_lic,id);
											PassangerLogin  passangerLogin = new PassangerLogin(java.lang.Integer.toString(id),passanger_password, driversLinkedList, passangesLinkedList, currentLocation);	
											passangerLogin.access(passangesLinkedList,  java.lang.Integer.parseInt(passanger_id));
										
										}
									}
								}
									
							}
							if(scans !=  null) {
									scans.close();
							}
				}catch(Exception  e) {
					System.out.println(e.toString());
				}finally {
					if(scans != null) {
						scans.close();
					}
					
				}
			}	
	}
	
}
