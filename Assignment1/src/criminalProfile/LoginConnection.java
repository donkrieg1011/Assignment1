package criminalProfile;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LoginConnection {
	private Connection myConn;
	private StringBuilder list;
	
	public LoginConnection() throws Exception {
		try {
			String hostName = "localhost";
			String user = "root";
			String password = "";
			String dbName = "ass1";
			String dburl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?autoReconnect=true&useSSL=false";;
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			myConn = DriverManager.getConnection(dburl, user, password);
			System.out.println("Successful connection to: " + dburl);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean searchUsers(String username, String password) throws Exception {
		 
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("select * from login_info");
			
			myRs = myStmt.executeQuery();
			myRs.beforeFirst();
			while (myRs.next()) {
				String myUsername = myRs.getString("username");
	            String myPassword = myRs.getString("password");
	            
	            if (myUsername.equals(username) && myPassword.equals(password))
	            {
	            	return true;
	            }
	            else {
	            	return false;
	            }
	    		
			}
		}
		finally {
			myConn.close();
		}
		return false;
	
	}
}
