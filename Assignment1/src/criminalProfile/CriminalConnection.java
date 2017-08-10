
package criminalProfile;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CriminalConnection {
	private Connection myConn;
	private StringBuilder list;
	
	public CriminalConnection() throws Exception {		
	}
	
	public void addCriminal(CriminalInfo newCriminal) throws Exception {
		
	}
	
	public StringBuilder searchCriminals(String lastName) throws Exception {
		list = new StringBuilder();
		 
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("select * from criminal_info where last_name like ?  order by last_name");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			if(!myRs.next()){
				list.append("NONE");
			}
			myRs.beforeFirst();
			while (myRs.next()) {
				String myFirstName = myRs.getString("first_name");
	            String myLastName = myRs.getString("last_name");
	            String myNationality = myRs.getString("nationality");
	            String myCrime = myRs.getString("crime");
	            Date myDOB = myRs.getDate("date_of_birth");
	            
	            CriminalInfo tempCriminal = new CriminalInfo(myLastName, myFirstName, myNationality, myCrime, myDOB);
	    		
	    		list.append(tempCriminal.toString());
	    		
			}
			return list;
		}
		finally {
			myConn.close();
		}
	}
}
