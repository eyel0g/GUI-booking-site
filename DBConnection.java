import java.sql.*;
import org.postgresql.*;
public class DBConnection {

	public static Connection dbconnect;
	private boolean status;
	
	
	
public Connection connect() {
	
	try {
		dbconnect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/344HW2", 
				"postgres", "admin");
		status=true;
	}
	catch(Exception e) {
		status=false;
	}
	
	return dbconnect;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}




	
}
