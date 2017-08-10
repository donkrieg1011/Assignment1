package criminalProfile;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CriminalConnection {
	private Connection myConn;
	private StringBuilder list;
	
	public CriminalConnection() throws Exception {		
	    String hostName = "localhost";
		String user = "root";
		String password = "";
		String dbName = "ass1";
		String dburl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?autoReconnect=true&useSSL=false";;
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("Successful connection to: " + dburl);
	}
	
	public void addEntry(CriminalInfo newCriminal) throws Exception {
		PreparedStatement myStmt = null;

		myStmt = myConn.prepareStatement("insert into criminal_info"
				+ " (first_name, last_name, nationality, crime, date_of_birth)"
				+ " values (?, ?, ?, ?, ?)");
		
		try {
			String dateFormat = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date dateOfBirth = Date.valueOf(sdf.format(CriminalInfo.getDOB()));
			
			myStmt.setString(1, CriminalInfo.getFirstName());
			myStmt.setString(2, CriminalInfo.getLastName());
			myStmt.setString(3, CriminalInfo.getNationality());
			myStmt.setString(4, CriminalInfo.getCrime());
			myStmt.setDate(5, dateOfBirth);

			myStmt.executeUpdate();			
		}
		finally {
			myConn.close();
		}
	}
	
	public StringBuilder searchCriminals(String lastName) throws Exception {
		return list;
		
	}
	
}
