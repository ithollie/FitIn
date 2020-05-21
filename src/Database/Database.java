/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
/**
 *
 * @author Ibrahim
 */
public class Database {
        
   // JDBC driver name and database URL
   private final  String JDBC_DRIVER;
   private final  String DB_URL;

   //  Database credentials
 
   private  final  String   _host = "127.0.0.1";
   private  final  String  _user = "root";
   private  final  String  _password = "";
   private  final  String  _name = "ecommerce";
    
   private final  boolean  _conndb = false;
   public   String   _last_query = null;
   public  int      _affected_rows = 0;
    
   public String[]  $_insert_keys; 
   public String[]  $_insert_values;
   public String[]  $_update_sets;
    
    public int      _id;
    
   //JDBC register driver
   private static final String CLASS_NAME = "com.msql.jdbc.Driver";
   private Connection conn;
   private Statement stmt;
   private final String  last_query;
  
   
   public Database() throws ClassNotFoundException, SQLException {
       this.JDBC_DRIVER =  "com.mysql.jdbc.Driver";
       this.DB_URL      =  "jdbc:mysql://127.0.0.1/ecommerce";
      
       this.conn        = null;
       this.stmt        =  null;
       this.last_query  =  null;
       
        DriverLoaded();
       connecting();
   }
   private void  DriverLoaded() throws ClassNotFoundException{
       
       //STEP 2: Register JDBC driver
       Class.forName(this.JDBC_DRIVER);
       //System.out.println("Driver Loaded");
   }
   private void   connecting() throws SQLException{
       //STEP 3: Open a connection
       //System.out.println("Connecting to database...");
       conn = DriverManager.getConnection(DB_URL,this._user,this._password);
   }
   
   @SuppressWarnings("unused")
private void ExecuteQuery(String sqli) throws SQLException{
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT id, first_name, last_name,email, password FROM admins";
        ResultSet rs = stmt.executeQuery(sql);
        
        //STEP 5: Extract data from result set
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");

            String first = rs.getString("first_name");
            String last = rs.getString("last_name");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);
        }
        rs.close();
        stmt.close();
        conn.close(); 
      
        
   }
   public String fine_one(String name) throws ClassNotFoundException, SQLException{
       String result  =  "";
       String  sql = "SELECT first_name FROM admins WHERE  first_name = '"+name+"' ";
       String  sqlResult  =  this.fetchUser(sql);
       if(sql.length() >  10 &&  sqlResult.length() > 3){
           result  = sqlResult;        
       }
       return  result;
   }
   public String passanger_one(int id) throws ClassNotFoundException, SQLException{
       String result  =  "";
       String  sql = "SELECT first_name FROM clients WHERE  driver_id = '"+id+"' ";
       stmt  =   conn.createStatement();
       ResultSet  resultSet  = stmt.executeQuery(sql);
       while(resultSet.next()) {
    	   result = resultSet.getString("first_name");
       }
       return  result;
   }
   public ArrayList<String>  getDrivers() throws SQLException {
	   
	   ArrayList<String>  arrayList = new ArrayList<String>();
	   int increment = 0;
       String  sql = "SELECT * FROM drivers";
       ResultSet  results  =  stmt.executeQuery(sql);
       while(results.next()){ 
           if(!"".equals(results.getString(increment))){
              arrayList.add(results.getString(increment));       
            }
           increment++;
            
        }
       return arrayList;
   }
   public ArrayList<String>  getPassangers() throws SQLException {
	   ArrayList<String> arrayList  =  new ArrayList<String>();
	   int increment = 0;
       String  sql = "SELECT * FROM passanger";
       ResultSet  results  =  stmt.executeQuery(sql);
       
       while(results.next()){
           if(!"".equals(results.getString(increment))){
             arrayList.add(results.getString(increment));       
            }
            increment++;

        }
       return arrayList;
   }
   
   
   
   public ResultSet findAll(String email) throws ClassNotFoundException, SQLException{
       ResultSet result = null;
       String  sql = "SELECT * FROM admins WHERE email = '"+email+"' ";
       ResultSet  results  =  stmt.executeQuery(sql);
       while(results.next()){
           
           if(!"".equals(results.getString(1))){
             result =  results;       
            }
            
        }
       return result;
   }
   public int findPayUserId(String passanger_id) throws SQLException, NumberFormatException{
	   int condition  =  0; 
	   try{
		   String  sql   = "SELECT user_id FROM payments  WHERE user_id = '"+passanger_id+"'";
	       stmt  =  this.conn.createStatement();
	       ResultSet results  =  stmt.executeQuery(sql);
	       while(results.next()){
		       	int id  = results.getInt("user_id");
		       	if(id ==  Integer.parseInt(passanger_id)) {
		       		if(id >  0) {
		       			condition =  id;
		       		}
		       	}	
	       }
   } catch (NumberFormatException | SQLException e) {
	e.printStackTrace();
   }
	return condition;
}
    public String query(String sql) throws ClassNotFoundException, SQLException{
      stmt  =   conn.createStatement();
      ResultSet  set  = stmt.executeQuery(sql);
      displayQuery(set);
      return set.toString();
      
   }
   public  void display(String sentQuery) throws SQLException, ClassNotFoundException{
       
       
    }
    public  void displayQuery(ResultSet result) throws SQLException{
        if(result.next()){
            System.out.println("good");
        }else{
            System.out.println("There is no problem");
        }
    }
    
    public  String fetchUser(String sql) throws ClassNotFoundException, SQLException{
        String person  = "";
        stmt  =  this.conn.createStatement();
        ResultSet result =  stmt.executeQuery(sql);
        while(result.next()){
          
           if(!"".equals(result.getString(1))){
              person =  result.getString(1);       
            }
            
        }
        
        return person; 
    }
    public  String fetchUserLastName(String sql) throws ClassNotFoundException, SQLException{
        String p  = "";
        stmt  =  this.conn.createStatement();
        ResultSet result =  stmt.executeQuery(sql);
        while(result.next()){
                p =  result.getString(1);
        }
        return p; 
    }
    public  String fetchUserEmail(String sql) throws ClassNotFoundException, SQLException{
        String p  = "";
        stmt  =  this.conn.createStatement();
        ResultSet result =  stmt.executeQuery(sql);
        while(result.next()){
                p =  result.getString(1);
        }
        return p; 
    }
    public  String fetchUserPassword(String sql) throws ClassNotFoundException, SQLException{
        String p  = "";
        stmt  =  this.conn.createStatement();
        ResultSet result =  stmt.executeQuery(sql);
        while(result.next()){
                p =  result.getString(1);
        }
        return p; 
    }
    public  ResultSet fetchAll(String email) throws SQLException{
       String  find_user = "SELECT email  From  comsumer WHERE email = email";
       String  sql = "SELECT id, first_name, last_name,email, password FROM admins";
      
       stmt  =  this.conn.createStatement();
       ResultSet resultss = stmt.executeQuery(find_user);
       if(resultss  !=  null){
          ResultSet results = stmt.executeQuery(find_user);
       }
       return resultss;
    }
    public  String[] getItems(String name) throws SQLException{
        String[]  h = new String[4];
        String  sql  = "SELECT * FROM admins WHERE first_name  ='"+name+"'";
        stmt = this.conn.createStatement();
        ResultSet  result =  stmt.executeQuery(sql);
        while(result.next()){
          
                h[0] =  result.getNString("first_name");
                h[1] =  result.getNString("last_name");
                h[2] =  result.getNString("email");
        
        }
        return h;
    }
    
       
    public ResultSet   fetchOne(String name) throws SQLException, ClassNotFoundException{
        String  sql   = "SELECT id, first_name, last_name,email, password FROM admins";
        stmt  =  this.conn.createStatement();
        ResultSet results  =  stmt.executeQuery(sql);
        return results;
    }
   
    public Statement checkUser(String name) throws SQLException {
        String sql = "SELECT * FROM `{this._table}`ORDER BY `{name}` ASC";
        return stmt =  this.conn.createStatement();        
    }

    public void insert(long accountnumber,Date date ,String name, String lastname, String email, String password) throws SQLException {
        String  sql  = "INSERT INTO admins(`first_name`, `last_name`, `email`,`password`,  `date`, `accountnumber`)VALUES('"+name+"','"+lastname+"', '"+email+"', '"+password+"', '"+date+"','"+accountnumber+"')";             
        stmt =  conn.createStatement();
        stmt.executeUpdate(sql);
    }
    
    public  void  insertMoney(double money , String name, String lastname,  String email) throws SQLException{
        String  sql = "INSERT INTO money(`first_name`, `last_name`, `email`,`,amount)VALUES('"+name+"','"+lastname+"', '"+email+"', '"+money+"')";             
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
    public void insertUser(String name, String lastname, String email, double trans) throws SQLException {
        double value = trans;
        if(name !=  null) {
	        String  sql  = "INSERT INTO checking(`first_name`, `last_name`, `email`,amount)VALUES('"+name+"','"+lastname+"', '"+email+"', '"+value+"')";             
	        stmt =  conn.createStatement();
	        stmt.executeUpdate(sql);
        }
    }
    public void insertUserChecking(String name, String lastname, String email, double trans) throws SQLException {
        double value = trans;
        String  sql  = "INSERT INTO checking(`first_name`, `last_name`, `email`,amount)VALUES('"+name+"','"+lastname+"', '"+email+"', '"+value+"')";             
        stmt =  conn.createStatement();
        stmt.executeUpdate(sql);
    }
    // insertDrivers in the database < ------ used ---- > 
    public void insertDriver(
    		
    		String firstname,
    		String lastname,
    		String email,
    		String password,
    		
    		String address,
    		String country,
    		String state, 
    		String zip, 
    		
    		String licence,
    		int id2,
    		
    		String  modal,
    		String  maker,
    		String  color
    		
    		) throws SQLException {
    	
    		String  sql  = "INSERT INTO clients("
    				+ "`first_name`,"
    				+ " `last_name`,"
    				+ "`email`,"
    				+ "`password`,"
    				+ "`address`,"
    				+ "`country`,"
    				+ "`state`,"
    				+ "`post_code`,"
    				+ "`licence`,"
    				+ "`driver_id`,"
    				+ "`active`)VALUES("
    				+ "'"+firstname+"',"
    				+ "'"+lastname+"',"
    				+ "'"+email+"',"
    				+ "'"+password+"', '"+address+"','"+country+"','"+state+"','"+zip+"','"+Integer.parseInt(licence)+"','"+id2+"','"+1+"')";             
    		
    		stmt =  conn.createStatement();
    		stmt.executeUpdate(sql);
    	
    }
    // insertPassanger in the database < ------ used ---- > 
    public void insertPassanger( String firstname,String lastname,String email, String password,String address, String country, String state,String zipCode,String licence, double _id2) throws SQLException {
    	
    	
    		String  sql  = "INSERT INTO passangers("
    				+ "`first_name`,"
    				+ " `last_name`,"
    				+ " `email`,"
    				+ " `password`,"
    				+ " `address`,"
    				+ " `country`,"
    				+ "  `state`,"
    				+ "  `post_code` , "
    				+ " `licence`,"
    				+ " `driver_id`,"
    				+ "`active`"
    				+ ")VALUES('"+firstname+"','"+lastname+"', '"+email+"', '"+password+"', '"+ address + "' , '"+country+"', '" + state + "', '"+zipCode+"', '"+0+"', '"+_id2+"', '"+1+"' )";             
    				stmt =  conn.createStatement();
    				stmt.executeUpdate(sql);
    	
    }
    public  String  isthere(String name) throws SQLException{
        String user = "";
        String  sql  = "SELECT firstname FROM requests  WHERE firstname = '"+name+"'";             
        stmt =  conn.createStatement();
        ResultSet  result  = stmt.executeQuery(sql);
        while(result.next()){
            user = result.getString("firstname");
        }
        return  user;
    }
    
    public  String  driver_name(int driver_id) throws SQLException{
        String user = "";
        String  sql  = "SELECT first_name FROM clients WHERE  driver_id = '"+driver_id+"'";             
        stmt =  conn.createStatement();
        ResultSet  result  = stmt.executeQuery(sql);
        while(result.next()) {
        	user = result.getString("first_name");
        }
        return user;
    }
    public double  sqlDouble(String sql) throws SQLException{
    	double  convert  = 0;
    	if(sql != null) {
		    stmt =  conn.createStatement();
		    ResultSet  result  = stmt.executeQuery(sql);
		    while(result.next()){
		            convert = result.getDouble("amount");
		    }
    	}
	    return  convert;
    }
    public void update(double amount, String name, String  sqli) throws SQLException {
        
        if(!sqli.isEmpty()){
            if(isthere(name).equals(name)){
                if(sqlDouble(sqli) >= 0){
                    double  total =  amount+= sqlDouble(sqli);
                    String  sql  = "UPDATE checking  SET amount = '"+total+"' WHERE first_name = '"+name+"'";             
                    stmt =  conn.createStatement();
                    stmt.executeUpdate(sql);
                }
            }else{
                System.out.println("user does not  exist update");
            }
        }
    }
     public void updateMinus(double amount, String username, String sendTo, String  sqli) throws SQLException {
        System.out.println("you are subtracting");
        if(!sqli.isEmpty()){
            if(isthere(username).equals(username)){
                if(sqlDouble(sqli) > 0){
                    double  total =  (amount-= sqlDouble(sqli));
                    String  sql  = "UPDATE checking  SET amount = '"+total+"' WHERE first_name = '"+sendTo+"'";             
                    stmt =  conn.createStatement();
                    stmt.executeUpdate(sql);
                }
            }else{
                System.out.println("user does not  exist update");
            }
        }
    }
    public Object query() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	public void insertCarDetails(String carname, String car_color, String carmake, String modal) {
		// TODO Auto-generated method stub
		
	}
	public void insertPayment(String card_name, String card_number,String expiration_date,String code) throws SQLException {
        	String  sql  = "INSERT INTO payments(`card_user_name`, `card_number`, `expiration_date`)VALUES('"+card_name+"','"+card_number+"', '"+expiration_date+"', '"+code+"')";             
        	stmt =  conn.createStatement();
        	stmt.executeUpdate(sql);
	}
	
	public void insertUser(String firstname,String lastname, String email,String password,String address,String country, String state,String zipCode,String driver_licence) throws SQLException {	
        String  sql  = "INSERT INTO checking("
        		+ "`first_name`,"
        		+ "`last_name`, "
        		+ "`email`,"
        		+ "`password`,"
        		+ "`address`,"
        		+ "`country`,"
        		+ "`state`,"
        		+ "`zipCode`,"
        		+ "driver_licence"
        		+ ""
        		+ ")VALUES("
        		+ "'"+firstname+"',"
        			+ "'"+lastname+"',"
        			+ " '"+email+"',"
        			+ " '"+password+"'"
        			+ " '"+address+"',"
        			+ " '"+country+"',"
        			+ "'"+state+"',"
        			+ "'"+zipCode+"',"
        			+ "'"+driver_licence+"',"
        			+ ")"
        			+ "";             
        		stmt =  conn.createStatement();
        		stmt.executeUpdate(sql);
	}
	
	public void insertDriver(String firstname, String lastname,String password,String email,String address,String country,String state,String zip,String driver_lic,int id) throws SQLException {
    		String  sql  = "INSERT INTO clients("
    				+ "`first_name`,"
    				+ "`last_name`,"
    				+ "`password`,"
    				+ "`email`,"
    				+ "`address`,"
    				+ "`country`,"
    				+ "`state`,"
    				+ "`post_code`,"
    				+ "`active`, "
    				+ "`licence`,"
    				+ "`driver_id`"
    				+ ")VALUES('"+firstname+"',"
    						+ "'"+lastname+"',"
    						+ "'"+password+"',"
    						+ "'"+email+"',"
    						+ "'"+address+"',"
    						+ "'"+country+"',"
    						+ "'"+state+"',"
    						+ "'"+zip+"',"
    						+ "'"+1+"',"
    						+ "'"+driver_lic+"',"
    						+ "'"+id+"'"
    						+ ")";
    		stmt  =  this.conn.createStatement();
			stmt.executeUpdate(sql);
	}
	
	// payment function
	public void savePayment(HashMap<String, String> driver1Array, String firstname, String lastname, int  id) throws SQLException {
		Random  r  = new Random();
		LinkedHashSet<Integer> set  = new  LinkedHashSet<Integer>();
		
		set.add(r.nextInt(4000));
		
		ArrayList<Integer> list 	= new  ArrayList<Integer>(set);
		String  sql = "SELECT *  FROM  ids";
		stmt   = this.conn.createStatement();
		ResultSet  results  = stmt.executeQuery(sql);
		
		while(results.next()) {
			
			if(results.getInt("index_id") != id) {
				
				String  expiration_date = driver1Array.get("expiration_date").toString();
				String  card_number 	= driver1Array.get("card_number").toString();
				String  email 			= driver1Array.get("email").toString();
				
				int		code 			= Integer.parseInt(driver1Array.get("code"));
				
				int     user_id  		=   id;
				
				if(findPayId(user_id) != true) {
					String  sql_payments  = "INSERT INTO payments(`firstname`, `lastname`,`email`, `card_number` , `expiration_date`,`code`, `user_id`)VALUES('"+firstname+"','"+lastname+"','"+email+"','"+card_number+"','"+expiration_date+"','"+code+"','"+user_id+"')";
					stmt  =  this.conn.createStatement();
					stmt.executeUpdate(sql_payments);
				}
			}
		}
	}
	public  boolean findPayId(int paymentId) throws SQLException {
		boolean condition  =  false; 
		String  sql   = "SELECT user_id FROM payments  WHERE user_id = '"+paymentId+"'";
        stmt  =  this.conn.createStatement();
        ResultSet results  =  stmt.executeQuery(sql);
        while(results.next()){
        	int id  = results.getInt("user_id");
        	if(id == paymentId) {
        		condition =  true;
        	}
        }
        return condition;
	}
	public boolean condition(double _id2, String firstname) {
		// TODO Auto-generated method stub
		return false;
	}
	public void savePayment(HashMap<String, String> passangerHashMap, String firstname, String lastname, double _id2) {
		System.out.println("Saving payment");
		
	}
	public boolean fetchId(String passanger_id) throws SQLException {
		boolean condition  =  false; 
		String  sql   = "SELECT driver_id FROM clients  WHERE driver_id = '"+passanger_id+"'";
        stmt  =  this.conn.createStatement();
        ResultSet results  =  stmt.executeQuery(sql);
        while(results.next()){
        	int id  = results.getInt("driver_id");
        	if(id ==  Integer.parseInt(passanger_id)) {
        		condition =  true;
        	}
        }
        return condition;
	}
	public boolean fetchIdPassanger(String passanger_id) throws SQLException {
		boolean condition  =  false; 
		String  sql   = "SELECT passannger_id FROM passanger  WHERE driver_id = '"+passanger_id+"'";
        stmt  =  this.conn.createStatement();
        ResultSet results  =  stmt.executeQuery(sql);
        while(results.next()){
        	int id  = results.getInt("driver_id");
        	if(id ==  Integer.parseInt(passanger_id)) {
        		condition =  true;
        	}
        }
        return condition;
	}
	public boolean fetch_firstname_lastname(String first_name, String last_name) throws SQLException {
		boolean condition  =  false; 
		String  sql   = "SELECT first_name,last_name FROM clients  WHERE first_name = '"+first_name+"' AND last_name ='"+last_name+"'";
        stmt  =  this.conn.createStatement();
        ResultSet results  =  stmt.executeQuery(sql);
        while(results.next()){
        	
        	String database_first_name  = results.getString("first_name");
        	String database_last_name   = results.getString("last_name");
        	
        	if(database_first_name.equals(first_name)  &&  database_last_name.equals(last_name)) {
        		condition =  true;
        	}
        }
        return condition;
	}
	public boolean check_user(String user) throws SQLException {
		boolean condition  =  false; 
		String  sql   = "SELECT first_name FROM clients  WHERE first_name = '"+user+"'";
        stmt  =  this.conn.createStatement();
        ResultSet results  =  stmt.executeQuery(sql);
        while(results.next()){
        	String name  = results.getString("first_name");
        	if(name == user) {
        		condition =  true;
        	}
        }
        return condition;
	}
	public void insertIds(Integer integer) throws SQLException {
		String  sql  = "INSERT INTO ids(`index_id`)VALUES('"+integer+"')";             
    	stmt =  conn.createStatement();
    	stmt.executeUpdate(sql);
	}
	public int getRandomNumber() throws SQLException {
		int firstResult = 0;
		
		String  sql = "SELECT * FROM ids";
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(sql);
		while(results.next()) {
				System.out.println(results);
		}
		
		return firstResult;
		
	}
	public void insertSertRequst(String firstname, String lastname, String _id) throws SQLException {
		String requests = "requests";
		String  sql  = "INSERT INTO requests(`firstname`, `lastname`, `_id`)VALUES('"+firstname+"', '"+lastname+"', '"+_id+"')";             
    	stmt =  conn.createStatement();
    	stmt.executeUpdate(sql);
	}
	
	public ArrayList<Integer> fetchAllId() throws SQLException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		String  sql   = "SELECT * FROM ids";
        stmt  =  this.conn.createStatement();
        ResultSet results  =  stmt.executeQuery(sql);
        
        while(results.next()){
        	list.add(results.getInt("index_id"));
        	
        }
        return list;
	}
}
