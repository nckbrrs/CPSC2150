package cu.cs.cpsc2150.project2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The AccountData class holds an ArrayList of every currently existing Account instance in the library system,
 * and contains methods to add/remove/retrieve specific Account instances as well as
 * methods to save and load its data.
 * @author nickbarrs
 */

public class AccountData implements Serializable{
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = -985835309102450884L;
	/**
	 * Save path to where the AccountData data is saved
	 */
	private static final String save_file = "acc_data.dat";
	/**
	 * Static instance of the AccountData class
	 */
	private static AccountData accountData = new AccountData();
	/**
	 * ArrayList of currently existing Account instances in the library
	 */
	private ArrayList<Account> accounts;
	/**
	 * Represents the current ID value of the library--the number of Accounts that have been added so far.
	 * This is used rather than accounts.size() because if there were, for example, 4 Accounts, 
	 * and thus the most recently added Account had an ID value of 3, and the third was deleted,
	 * accounts.size()'s value would decrease to 3. Then, if another Account was added, a value of 3
	 * would be assigned to its ID as well, which is a problem because the previously-most-recently-added
	 * Account already has an ID of 3. Using a value that only increments with each add ensures that no 
	 * Account has the same unique ID as another.
	 */
	private int currentID = 0;
	
	/**
	 * AccountData() constructs the static AccountData by calling the load() method.
	 */
	private AccountData() {
		load();
	}
	
	/**
	 * @return the static instance of AccountData
	 */
	public static AccountData getAccountData() {
		return accountData;
	}
	
	/**
	 * @return the ArrayList of Accounts belonging to AccountData
	 */
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	/**
	 * addAccount(Account a) Adds the sent in Account instance to the ArrayList of Accounts belonging to AccountData.
	 * if the account being added is the initial admin account, and it is being added for the first time, add it.
	 * otherwise, validate the sent in Account instance's data before adding it to the ArrayList. After the Account is
	 * added, AccountData's data is saved by calling the save() method. 
	 * The corresponding AccountTableModel is updated.
	 * 
	 * @param a Account to be added to the Accounts ArrayList belonging to AccountData
	 * @return true boolean if the add was a sucess, and false boolean if not
	 */
	public boolean addAccount(Account a) {
		if ((a.getUsername().equals("admin") && a.getID() == 0)) {
			if (currentID == 0) {
				accounts.add(a);
				a.setID(currentID);
				currentID++;
				save();
				AccountTableModel.getTable().updateTable();
				return true;
			}
		}
		
		else {
			if (ValidateData.validateAccountAdd(a)) {
				accounts.add(a);
				a.setID(currentID);
				currentID++;
				save();
				AccountTableModel.getTable().updateTable();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * removeAccount(Account a) removes the sent in Account instance from the Accounts ArrayList belonging to AccountData.
	 * After removal, the AccountData data is saved by calling the save() method. 
	 * The corresponding AccountTableModel is updated.
	 * 
	 * @param a Account to be removed from the Accounts ArrayList belonging to AccountData
	 */
	public void removeAccount(Account a) {
		accounts.remove(a);
		save();
		AccountTableModel.getTable().updateTable();
	}
	
	/**
	 * @param index integer index in the Accounts ArrayList of the desired Account instance
	 * @return instance of the desired Account
	 */
	public Account getAccount(int index) {
		return accounts.get(index);
	}
	
	/**
	 * @param username String username of the desired Account instance
	 * @return instance of the desired Account with username corresponding to the sent in parameter. returns null
	 * if Account instance is not found in the Accounts ArrayList
	 */
	public Account getAccount(String username) {
		for (Account a : accounts) 
			if (a.getUsername().equals(username)) 
				return a;
			
		return null;
	}
	
	/**
	 * @return integer size of Accounts ArrayList
	 */
	public int getNumAccounts() {
		return accounts.size();
	}
	
	/**
	 * @param id integer whose value is assigned to AccountData's currentID
	 */
	public void setCurrentID(int id) {
		currentID = id;
	}
	
	/**
	 * @return integer value of AccountData's currentID
	 */
	public int getCurrentID() {
		return currentID;
	}
	
	/**
	 * save() saves AccountData's currentID and ArrayList of Accounts to the save path
	 */
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save_file));
			oos.writeInt(currentID);
			oos.writeObject(accounts);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	/**
	 * load() loads AccountData's currentID and ArrayList of Accounts from the save path
	 * if the file is null or not found, currentID is set to 0 and a new Accounts ArrayList is initialized
	 */
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(save_file));
			currentID = ois.readInt();
			accounts = (ArrayList<Account>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			currentID = 0;
			accounts = new ArrayList<Account>();
		}
	}
	
}
