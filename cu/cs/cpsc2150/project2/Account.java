package cu.cs.cpsc2150.project2;

import java.io.Serializable;

/**
 * The Account class represents a single Account in the Library System.
 * @author nickbarrs
 */

public class Account implements Serializable{
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = 4343745235494650634L;
	/**
	 * Represents the unique integer ID of this Account instance
	 */
	private int ID;
	/**
	 * Represent the username, password, name, email address, and phone number
	 * of this Account instance, respectively
	 */
	private String username, password, name, email, phoneNumber;
	/**
	 * Represents this Account instances type:
	 * A value of true indicates that it is of type Staff, 
	 * and false indicates it is of type Member.
	 */
	private boolean staff;
	
	/**
	 *  Account(String un, String pw, boolean staff, String name, String eml, String nmbr)
	 *  takes in a series of strings and a boolean that are assigned to the 
	 *  username, password, staff, name, email address, and phone number
	 *  members of the created Account instance.
	 *  @param un String that is assigned to this Account instance's username via a setter method
	 *  @param pw String that is assigned to this Account instance's password via a setter method
	 *  @param staff boolean whose value is assigned to this Account instance's staff via a setter method
	 *  @param name String that is assigned to this Account instance's name via a setter method
	 *  @param eml String that is assigned to this Account instance's email via a setter method
	 *  @param nmbr String that is assigned to this Account instance's phoneNumber via a setter method
	 */
	public Account(String un, String pw, boolean staff, String name, String eml, String nmbr) {
		this.setUsername(un);
		this.setPassword(pw);
		this.setStaff(staff);
		this.setName(name);
		this.setEmail(eml);
		this.setPhoneNumber(nmbr);
	} 

	/**
	 * @return this Account instance's ID integer value
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD integer whose value is assigned to this Account instance's ID
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return this Account instance's username String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username String that is assigned to this Account instance's username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return this Account instance's password String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password String that is assigned to this Account instance's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return this Account instance's name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name String that is assigned to this Account instance's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return this Account instance's email String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email String that is assigned to this Account instance's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return this Account instance's phone number String
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber String that is assigned to this Account instance's email
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return this Account instance's staff boolean value
	 */
	public boolean isStaff() {
		return staff;
	}

	/**
	 * @param staff boolean that is assigned to this Account instance's staff
	 */
	public void setStaff(boolean staff) {
		this.staff = staff;
	}
}
