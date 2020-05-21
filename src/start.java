import user.BleepDriver;
import user.BleepPassanger;

import java.awt.List;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import Database.Application;
import avaliableDrivers.Drivers;
import passanger.Passangers;
import  helper.Helper;

public class start {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		System.out.println("The programming is starting please hold on");
		
		Scanner scan = null;
		Random  r  = new Random();
		
		Application app = new Application();
		Application setApp  = new Application();
		
		LinkedHashSet<Integer> set  = new LinkedHashSet<Integer>();

		while(set.size() <  100) {
	         set.add(r.nextInt(4000));
	     }
		ArrayList<Integer> list = new ArrayList<Integer>(set);
		
		if(set.size() == 100) {
			for(int i  = 0 ; i < set.size();  i++) {
				setApp.db.insertIds(list.get(i));
			}
		}
		
	    System.out.println("Random numbers with no duplicates = "+set);
	         
	    int firstDriver  = list.get(0);
	  
	    System.out.println("driver1  id " + firstDriver);
	    
		HashMap<String, String> driver1Array = new HashMap<String, String>();
		HashMap<String , String> driver1_car_data = new HashMap<String, String>();
	
		BleepDriver driver1 = new BleepDriver("Ibrahim", "Thollie", "password1","ithollie@yahoo.com" ,"2233 boyu street" , "United States","Pennsyhivain","19023","6941454", firstDriver,"Crysler 200 c", "Crysler ", "200","red");
		try {
			if(!driver1.firstname.isEmpty() && !driver1.lastname.isEmpty() && !driver1.password.isEmpty()) {
				
				if(driver1.check_id(firstDriver) != true  &&  driver1.check_first_and_last_name(driver1.firstname, driver1.lastname) != true) {
					
					boolean    driver_id  = app.db.fetchId(Integer.toString(firstDriver));
					
					System.out.println("driver1 id is equals " +  driver_id);
					app.db.insertDriver(driver1.firstname, driver1.lastname,driver1.email,driver1.password,driver1.address,driver1.country,driver1.state,driver1.zipCode, driver1.licence,driver1.id,driver1.modal,driver1.make,driver1.car_color);
					System.out.println("Thank you for registering you are a driver  and your name is   " + driver1.firstname + " "+ driver1.lastname);
					
					driver1_car_data.put("car_name", "bmw s 54ie");
					driver1_car_data.put("car_modal", "bmw_eu");
					driver1_car_data.put("year", "2019");
					driver1_car_data.put("car_color", "green");
					
					if(!driver1_car_data.isEmpty()) {
						
						System.out.println("car data" + driver1_car_data.get("car_name"));
						System.out.println("car data" + driver1_car_data.get("car_modal"));
						System.out.println("car data" + driver1_car_data.get("year"));
						System.out.println("car data" + driver1_car_data.get("car_color"));

					}
					
					if(driver1Array.isEmpty()) {
						
						driver1Array.put("fullname",driver1.firstname + " " + driver1.lastname);
						driver1Array.put("card_number", "353453454453");
						driver1Array.put("expiration_date","10/2020");
						driver1Array.put("code","6941");
						driver1Array.put("id", Integer.toString(driver1.id));
						driver1Array.put("email",driver1.email);
						
						if(!driver1Array.isEmpty()) {
							app.db.savePayment(driver1Array,  driver1.firstname,  driver1.lastname, driver1.id);
							System.out.println("Payment is successful inserted thank  you");
							System.out.println("   ");
						}else {
							System.out.println("CARD IS EMPTY");
						}
					}
				}else {
					System.out.println("User name has been  taken");
				}
		
			}else {
				System.out.println("Driver1's  name is empty please contact the adminstrator  for help");
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}

		// <------- insert driver (2) ---- >>
	
		int secondDriver  = list.get(1);
	 
	    System.out.println("driver1  id " + secondDriver);
	    
		HashMap<String , String> driver2Array = new HashMap<String, String>();
		HashMap<String  , String> driver2car_data 	  = new HashMap<String, String>();
		
		BleepDriver driver2 = new BleepDriver("mohamed","Tholliewow", "password11","ithollie@yahoo.com","2233 hhdh street" ,"United States","Pennsyhivain","19023","6941889",secondDriver,"Crysler car","cryler","Crysler 200 ","200");
		try {
			if(!driver2.firstname.isEmpty() &&  driver2.check_id(secondDriver) != true) {
				if(driver2.check_id(secondDriver) != true  &&  driver2.check_first_and_last_name(driver2.firstname, driver2.lastname) != true) {
								
						boolean id = app.db.fetchId(Integer.toString(secondDriver));
				
						System.out.println("driver2 id is equal  " + id);
						app.db.insertDriver(driver2.firstname, driver2.lastname,driver2.email,driver2.password, driver2.address,driver2.country,driver2.state, driver2.zipCode,driver2.licence,driver2.id,driver2.modal,driver2.make,driver2.car_color);
						System.out.println("Thank you for registering you are a driver  and your name is   " + driver2.firstname + " "+ driver2.lastname);
						
						if(driver2Array.isEmpty() && driver2car_data.isEmpty()) {
							
								driver2Array.put("fullName",driver2.firstname + " " + driver2.lastname);
								driver2Array.put("card_number", "4053453454453");
								driver2Array.put("expiration_date","10/2020");
								driver2Array.put("code","6991");
								driver2Array.put("id", Integer.toString(driver2.id));
								driver2Array.put("email", driver2.email);
								
								driver2car_data.put("car_name", "bmw s 54i");
								driver2car_data.put("car_modal", "bmw_e");
								driver2car_data.put("year", "2017");
								driver2car_data.put("car_color", "blue");
								
								if(!driver2car_data.isEmpty()) {
									System.out.println("car data" + driver2car_data.get("car_name"));
									System.out.println("car data" + driver2car_data.get("car_modal"));
									System.out.println("car data" + driver2car_data.get("year"));
									System.out.println("car data" + driver2car_data.get("car_color"));


								}
								if(!driver2Array.isEmpty()) {
									app.db.savePayment(driver2Array,  driver2.firstname,  driver2.lastname, driver2.id);
									System.out.println("Payment is successful inserted thank  you");
									System.out.println("   ");
								}
						}
					}else {
						System.out.println("User name has been taken");
					}
				}else {
					System.out.println("driver 2 is empty please contact the adminstrator for help");
				}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		//Beep drivers object insertion
		Drivers  driversLinkedList = new Drivers();
		driversLinkedList.insertDriver(driver1, driver1Array);
		driversLinkedList.insertDriver(driver2, driver2Array);
		// <<----  end of drivers  implementation ---- >>
		
		// <------- insert passenger (1) ---- >>
		int firstPassanger = list.get(2);
	   
	    System.out.println("Passanger1  id " + firstPassanger);
		HashMap<String, String> passangerHashMap = new HashMap<String, String>();
		BleepPassanger passanger1 = new BleepPassanger("james","kamara","ithollie@yahoo.com12","password13","2530  s lloyd  street" ,"United States","Pa","19143","",firstPassanger,passangerHashMap, "2530  s lloyd  street");
		
		if(!passanger1.firstname.isEmpty() &&  !passanger1.lastname.isEmpty()){
			
			if(passanger1.check_id_passanger(firstPassanger) != true  &&  passanger1.check_first_and_last_name(passanger1.firstname, passanger1.lastname) != true) {
					
				// <------- insert passenger ---- >>
					boolean passanger1_id = app.db.fetchIdPassanger(Integer.toString(firstPassanger));
					System.out.println("passanger1 id is equal  " + passanger1_id);
					app.db.insertPassanger(passanger1.firstname, passanger1.lastname,passanger1.email,passanger1.password, passanger1.address,passanger1.country,passanger1.state, passanger1.zipCode,passanger1.licence,passanger1._id);
					System.out.println("Thank you for registering you are a passanger  and your name is   " + passanger1.firstname + " "+ passanger1.lastname);
				
					if(!passanger1.firstname.isEmpty() && passangerHashMap.isEmpty()) {
						
						passangerHashMap.put("fullName",passanger1.firstname + " " + passanger1.lastname);
						passangerHashMap.put("card_number", "393453454453");
						passangerHashMap.put("expiration_date","10/2022");
						passangerHashMap.put("code","6942");
						passangerHashMap.put("id", Integer.toString(passanger1._id));
						passangerHashMap.put("email",passanger1.email);
						
						if(!passangerHashMap.isEmpty()) {
							passanger1.setMapArray(passangerHashMap);
							app.db.savePayment(passangerHashMap,  passanger1.firstname,  passanger1.lastname, passanger1._id);
							System.out.println("Payment is successful inserted thank  you");
							System.out.println("   ");
						}else {
							System.out.println("CARD IS  EMPTY");
						}
						
					}else {
						System.out.println("there  is a problem user name is  empty");
					}
			}else {
				System.out.println("User name has been taken");
			}
		}else {
			System.out.println("passanger1 object is empty");
		}
		
		//<------- insert passenger2 ---- >>	
			int secondPassanger = list.get(3);
		
			System.out.println("driver1  id " + secondPassanger);
	    
			
			HashMap<String, String> passanger2HashMap = new HashMap<String, String>();
			BleepPassanger passanger2 = new BleepPassanger("john","kamara","ithollie@yahoo.com12e","password1", "232 s gram street" ,"SierraLeone" ,"FreeTown" ,"12332","1828677",secondPassanger,passanger2HashMap,"232 s grams street");
			try {
					if(!passanger2.firstname.isEmpty() &&  !passanger2.lastname.isEmpty()) {
						if(passanger2.check_id_passanger(secondPassanger) != true  &&  passanger2.check_first_and_last_name(passanger2.firstname, passanger2.lastname) != true) {
							// <------- fetch passenger id ---- >>
							boolean id = app.db.fetchIdPassanger(Integer.toString(secondPassanger));
							
							System.out.println("passanger2 id is equal  " + id);
							app.db.insertPassanger(passanger2.firstname, passanger2.lastname,passanger2.email,passanger2.password, passanger2.address,passanger2.country,passanger2.state, passanger2.zipCode,passanger2.licence,passanger2._id);
							System.out.println("Thank you for registering you are a passanger  and your name is   " + passanger2.firstname + " "+ passanger2.lastname);
							
							if(passanger2HashMap.isEmpty()) {
								
									passanger2HashMap.put("fullName",passanger2.firstname + " " + passanger2.lastname);
									passanger2HashMap.put("card_number", "393473454453");
									passanger2HashMap.put("expiration_date","10/2023");
									passanger2HashMap.put("code","6949");
									passanger2HashMap.put("id", Integer.toString(passanger2._id));
									passanger2HashMap.put("email",passanger1.email);
									
									passanger2.setMapArray(passanger2HashMap);
									app.db.savePayment(passangerHashMap,passanger2.firstname,  passanger2.lastname, passanger2._id);
									System.out.println("Payment is successful inserted thank  you");
									System.out.println("   ");
								
							}else {
									System.out.println("There is a problem  inserting card information");
								}
						}else {
							System.out.println("User name is taken");
						}
					}else {
						System.out.println("There is a problem user name is Empty");
					}
		}catch(Exception  e) {
			System.out.println(e.toString());
		}
		// Beep object
		Passangers  passangesLinkedList = new Passangers();
		passangesLinkedList.insertPassager(passanger1, passangerHashMap);
		passangesLinkedList.insertPassager(passanger2, passanger2HashMap);
			
		System.out.println("<---- Drivers from  the  computer---->");
		driversLinkedList.displayDrivers();
		System.out.println("the count is " + driversLinkedList.countDrivers);
		
		System.out.println("<---- Drivers from  the  database ---->");
		while(app.db.getPassangers().size() >  0) {
			System.out.println(app.db.getPassangers().toString());
		}
		System.out.println(" |------ end of drivers list ----------|");
		System.out.println(" ");
		
		System.out.println("<---- Passangers from  the computer -- >");
		passangesLinkedList.displayPassangers();
		
		
		System.out.println("<---- Passangers from  the database -- >");
		while(app.db.getPassangers().size() > 0) {
			System.out.println(app.db.getPassangers().toString());
		}
		
		System.out.println("the count is " + passangesLinkedList.countPassangers);
		System.out.println(" |------ end of passangers list ----------|");
		System.out.println(" ");
		

		scan = new Scanner(System.in);
		
		System.out.println(" ");
		
		System.out.println("Enter  0 to login as a Driver or 1 as  a passager");
		try {
		String  cts = scan.nextLine();
			switch(cts){
					case "0":
						if(Integer.parseInt(cts) == 0){
								if((Integer.parseInt(cts) > -1 ) && (Integer.parseInt(cts) < 1) ) {
									Helper helper =  new  Helper();
									helper.driver(cts, driversLinkedList,list);
								}
							}
							break;
					case "1":		
						if (Integer.parseInt(cts) == 1) {
								if((Integer.parseInt(cts) > 0 ) && (Integer.parseInt(cts) < 2) ) {
									Helper  helper  =  new  Helper();
									helper.passanger(cts,passangesLinkedList,driversLinkedList,list);
								}
						}
						break;
						default:;
							System.out.println("please enter a number between  0  and 1");
							break;
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			if(scan != null) {
				scan.close();
			}
		}
		
	}

}
