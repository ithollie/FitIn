package passanger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Database.Application;
import avaliableDrivers.DriverNode;
import avaliableDrivers.Drivers;
import connect.passanger.connectionEstiblish;

public class PassangerLogin{
	
	public String passanger_id;
	public String passanger_password;
	public String location;
	
	int sent_requests;
	public Drivers driverLinkedList;
	public Passangers passangerLinkedListObject;
	
	public PassangerLogin(String  passanger_id, String  passanger_password, Drivers driverLinkedList,Passangers passangerLinkedList, String location) {
		this.passanger_id   = passanger_id;
		this.passanger_password  =  passanger_password;
		this.driverLinkedList = driverLinkedList;
		this.passangerLinkedListObject =  passangerLinkedList;
		this.sent_requests  = 0;
		this.location = location;	
	}
	public  ArrayList<String> searchPassanger(int passanger_id2) {
		ArrayList<String> list =  new  ArrayList<String>();
		PassangerNode current = passangerLinkedListObject.first;
		
		while(current != null) {
			if(current._id == passanger_id2) {
				list.add(current.firstname);
				list.add(current.lastname);
				list.add(Double.toString(current._id));
				
			}
			current  =  current.nextNode;
		}
		return list;
				
	}
	public boolean checkUser() {
		PassangerNode  current  =  passangerLinkedListObject.first;
		boolean value =  false;
		while(current != null) {
			if(current._id == Double.parseDouble(passanger_id)) {
				value =  true;
				break;
			}
			current  = current.nextNode;
		}
		return value;
	}
	public boolean checkPass() {
		PassangerNode  current  =  passangerLinkedListObject.first;
		boolean value =  false;
		while(current != null) {
			if(current.password.equals(passanger_password)) {
				value =  true;
				break;
			}
			current  = current.nextNode;
		}
		return value;
	}
	public boolean linkedListCheck() {
		PassangerNode  current  =  passangerLinkedListObject.first;
		boolean value =  false;
		while(current.nextNode != null) {
			if(current._id == Double.parseDouble(passanger_id)) {
				if(current.map.isEmpty()) {
					value =  true;
				}
			}
			current  = current.nextNode;
		}
		return value;
	}
	public void setterRequestCount() {
		sent_requests++;
	}
	
	public void access(Passangers passangesLinkedList,int passanger_id) throws FileNotFoundException, ClassNotFoundException, SQLException {
		System.out.println("Passager  access is granted and thank  you passanger  " + " " + passanger_id);
		
		System.out.println("<----------------------->");
		System.out.println("Avaliable Drivers or online drivers");
		displayDrivers();

		Scanner  scans =  null;
		scans = new  Scanner(System.in);
		
		System.out.println("Enter a driver's id to send a request");
		String  driver_id  = scans.nextLine();
		
		searchforDriverDisplay(driver_id);
		
		Application app  = new Application();
		int passanger_id_variable =  (app.db.findPayUserId(Integer.toString(passanger_id)) > 0)?app.db.findPayUserId(Integer.toString(passanger_id)):-1;
			
		System.out.println("passanger id is fetched and it is " +  passanger_id_variable);
			
		if(searchforDriver(driver_id) ==  Integer.parseInt(driver_id)  && passanger_id_variable == -1) {
					
					System.out.println("please enter bank information or credit card to pay  for the ride");

					System.out.println("Enter  name on  the card");
					String  username = scans.nextLine();
					
					System.out.println("Enter card number");
					String  card_number = scans.nextLine();
						
					System.out.println("Enter  expiration data on  the card");
					String  expiration_date = scans.nextLine();
						
					System.out.println("Enter security code");
					String  security_code = scans.nextLine();
						
					Double card_num = new Double(card_number);
						
					if(!username.isEmpty() && !card_number.isEmpty()  && !expiration_date.isEmpty() && !security_code.isEmpty()) {	
						if(card_num.isNaN() == true) {
								paymentDetails(passanger_id);
								setterRequestCount();
								System.out.println("you  are about to  send a request to driver  id number " + " "+ Integer.parseInt(driver_id));
								System.out.println("Request  sent ........." + " " +  sent_requests);
								sendRequest(driver_id.toString(), searchPassanger(passanger_id));
								connectionEstiblish connect =  new  connectionEstiblish(Integer.parseInt(driver_id), passanger_id);
								connect.connected();
						}
					}
		}else {
			paymentDetails(passanger_id);
			sendRequest(driver_id.toString(), searchPassanger(passanger_id));
			System.out.println("request sent to driver  id " + " "+ Integer.parseInt(driver_id));
			connectionEstiblish connect =  new  connectionEstiblish(Integer.parseInt(driver_id), passanger_id);
			connect.connected();
		}

		if(scans != null) {
			scans.close();
		}
		
	}
		
	public 	void paymentDetails(int passanger_id) {
		PassangerNode passangerCurrent =  passangerLinkedListObject.first;
		while(passangerCurrent  != null) {
			if(passangerCurrent._id == passanger_id) {
				System.out.println("<----------- passanger bank  details --------- >");
				System.out.println(passangerCurrent.map.get("fullName"));
				System.out.println(passangerCurrent.map.get("card_number"));
				System.out.println(passangerCurrent.map.get("expiration_date"));
				System.out.println(passangerCurrent.map.get("code"));
				System.out.println(passangerCurrent.map.get("id"));
				System.out.println("------------------------------------------------");
			}
			passangerCurrent = passangerCurrent.nextNode;
		}
	}
	public void sendRequest(String  driverKey, ArrayList<String> passangers) throws FileNotFoundException , SQLException, ClassNotFoundException {		
		Application  app  = new Application();
		DriverNode current = driverLinkedList.first;
	
		while(current != null) {
				if(driverKey.equals(Integer.toString(current._id))) {
					current.received_requests[0][0] = passangers.get(0);
					current.received_requests[0][1] = passangers.get(1);
					current.received_requests[0][2] = passangers.get(2);
					app.db.insertSertRequst(passangers.get(0), passangers.get(1), passangers.get(2));
					
				}
				current = current.nextNode;
		}
		
	}
	public int searchforDriver(String key){
		avaliableDrivers.DriverNode  current = driverLinkedList.first;
		int value = -1;
		while(current.nextNode != null) {
			if(current._id ==  Integer.parseInt(key)) {
				return  current._id;
			}
			current =  current.nextNode;
		}
		return value;
	}
	public  void   displayDrivers() {
		avaliableDrivers.DriverNode current  = driverLinkedList.first;
		while(current != null) {
			System.out.println("[");
			System.out.println("Driver's name " + current.firstname + " "+ current.lastname);
			System.out.println("Driver's email  address" + current.email); 
			System.out.println("Driver's id " + current._id);
			System.out.println("]");
			current =  current.nextNode;
		}
	}

	public void searchforDriverDisplay(String driverKey){
		avaliableDrivers.DriverNode  current = driverLinkedList.first;
		
		while(current != null) {
			if(Integer.parseInt(driverKey) == current._id) {
				System.out.println("___Driver is avaliable to pick you up at your current destination ___");
				System.out.println("Driver's name" + " " +current.firstname);
				System.out.println("Driver's lastname " + " " +current.lastname);
				System.out.println("Driver's car make " + " " +current.make);
				System.out.println("Driver's car modal " + " " +current.modal);
				System.out.println("Driver's state " + " " +current.state);
				System.out.println("Driver's car color " + " " +current.car_color);
				System.out.println("payment  information" + " " + current.arrayList.get("fullName"));
				System.out.println("___________________");
			}
			current =  current.nextNode;
		}
		
	}
}
