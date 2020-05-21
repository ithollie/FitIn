package Database;

import java.sql.SQLException;

public class Application {
	public Database db;
	
	public Application() throws ClassNotFoundException, SQLException {
		this.db = new Database();
	}
}
