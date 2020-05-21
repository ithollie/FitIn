package passanger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Database.Application;
import avaliableDrivers.DriverNode;
import user.BleepPassanger;

public class Passangers {
	
	public PassangerNode first;
	public BleepPassanger passanger;
	public ArrayList<String> arrayList;
	public int  countPassangers;
	
	public Passangers() {
		this.first = null;
		this.countPassangers = 0;
	}
	
	public void insertPassager(BleepPassanger passanger, HashMap<String, String> passanger1Array) {
		PassangerNode  current = first;
		if(current == null) {
			PassangerNode newNode =  new PassangerNode(
					passanger.firstname,
					passanger.lastname,
					passanger.password,
					passanger.email,
					passanger.address,  
					passanger.state,
					passanger.country,
					passanger.zipCode,
					///<------  orgination  is key when programming ---->
					passanger._id,
					passanger.licence,
					
					passanger.currentLocation,
					passanger.hashMapArray
					
			);
			first  = newNode;
			countPassangers++;
		}
		if (current != null) {
			PassangerNode newNode =  new  PassangerNode(
					passanger.firstname,
					passanger.lastname,
					passanger.password,
					passanger.email,
					passanger.address,  
					passanger.state,
					passanger.country,
					passanger.zipCode,
					///<------  orgination  is key when programming ---->
					passanger._id,
					passanger.licence,
					
					passanger.currentLocation,
					passanger.hashMapArray
					);
			newNode.nextNode =  first;
			first =  newNode;
			countPassangers++;
		}
	}
	
	public   void displayPassangers() {
		PassangerNode  current = first;
		while(current != null) {
				System.out.println(" ");
				System.out.println("[ " +" First name " + "  " + current.firstname);
				System.out.println("Lastname is " + " " + current.lastname);
	
				System.out.println("passanger's  licence "+ " " + current.passanger_lis);
				System.out.println("passanger's  id " +  " " + current._id);
				System.out.println("passanger's  password " + " " + current.password + " ]");
				
				current = current.nextNode;
				
			}	
	}
	public   void displayCount() {
		System.out.println("count = "+countPassangers);
		
				
	
	}
	public boolean find(String passanger_id) throws ClassNotFoundException, SQLException {
		Application app = new Application();
		return app.db.fetchId(passanger_id);
	}
	public boolean checkUser(String passanger_id) throws ClassNotFoundException, SQLException {
		Application app = new  Application();
		return app.db.fetchId(passanger_id);
	}

	public boolean checkPass(String passanger_password) {
		// TODO Auto-generated method stub
		return false;
	}
}
