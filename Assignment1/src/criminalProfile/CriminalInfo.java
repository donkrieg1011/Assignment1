package criminalProfile;

import java.sql.Date;
import java.sql.Time;

public class CriminalInfo {
	private static String lastName;
	private static String firstName;
	private static String nationality;
	private static String crime;
	private static Date dateOfBirth;

	
	public CriminalInfo(String criminalLastName, String criminalFirstName, String criminalNationality, String criminalCrime, Date criminalDOB)
	{
		this.lastName = criminalLastName;
		this.firstName = criminalFirstName;
		this.nationality = criminalNationality;
		this.crime = criminalCrime;
		this.dateOfBirth = criminalDOB;
	}
	
	public static String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	public static String getNationality() {
		return nationality;
	}

	public void setNationality(String newNationality) {
		this.nationality = newNationality;
	}
	
	public static String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}
	
	public static Date getDOB() {
		return dateOfBirth;
	}

	public void setDOB(Date newDOB) {
		this.dateOfBirth = newDOB;
	}
	
	public String toString() {
		return String.format("Criminal Information: \nLast Name: %s \nFirst Name: %s \nNationality: %s \nDate of Birth: %s \nCrime: %s\n] ",
						      lastName, firstName, nationality, dateOfBirth, crime);
	}
}
