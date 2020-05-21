package connect.passanger;

import java.sql.SQLException;

import Database.Application;
import payment.Payment;

public class connectionEstiblish {
	
	public  int passanger_id;
	public  int driver_id;
	
	public connectionEstiblish(int driver_id,  int passanger_id) {
		this.driver_id = driver_id;
		this.passanger_id =  passanger_id;
	}
	
	public  void connected() throws ClassNotFoundException, SQLException {
		System.out.println("passanger request accepted");
		System.out.println("driver " + driverUser() + " "+ "<-------- connected ------->" + " with "+ "passanger" + passangerUser());
		Payment  pay  = new  Payment(driver_id, passanger_id);
		
		if(pay.paymentSuccessful() ==  true) {
			System.out.println("Payment is successful");
		}else {
			System.out.println("Payment is not successful");
		}
		
	}
	
	
	public  String driverUser() throws ClassNotFoundException, SQLException {
		Application app = new Application();
		return app.db.driver_name(driver_id);
		
	}
	
	public  String passangerUser() throws ClassNotFoundException, SQLException {
		Application  app = new Application();
		return app.db.passanger_one(passanger_id);
	}
}
