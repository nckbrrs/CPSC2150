package cu.cs.cpsc2150.project2;

import javax.swing.table.AbstractTableModel;

/**
 * AccountTableModel inherits from AbstractTableModel 
 * and represents the table model of the JTable in the Accounts tab
 * @author nickbarrs
 *
 */

public class AccountTableModel extends AbstractTableModel {
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = -4696745072158954543L;
	/**
	 * Static instance of the AccountTableModel
	 */
	private static AccountTableModel accountTableModel = new AccountTableModel();
	/**
	 * Array of Strings corresponding to the names of the columns of the table model
	 */
	private String[] columnNames = {"ID", "Username", "Name", "Type"};
	/**
	 * Local reference to the static AccountData
	 */
	private AccountData accountData = AccountData.getAccountData();
	
	/**
	 * Default constructor
	 */
	private AccountTableModel() {
		super();
	}

	/**
	 * @return Static instance of AccountTableModel
	 */
	public static AccountTableModel getTable() {
		return accountTableModel;
	}
	
	/**
	 * @return integer number of Accounts in AccountData
	 */
	@Override
	public int getRowCount() {
		return accountData.getNumAccounts();
	}

	/**
	 * @return integer number of columns in AccountTableModel
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	/**
	 * @param index integer value of index of desired column in AccountTableModel
	 * @return name of desired column in AccountTableModel
	 */
	public String getColumnName(int index) {
		return columnNames[index];
	}
	
	/**
	 * @param rowIndex integer value of index of desired row in AccountTableModel
	 * @param columnIndex integer value of index of desired column in AccountTableModel
	 * @return String at location corresponding to inputted rowIndex and columnIndex
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Account temp = accountData.getAccount(rowIndex);
		switch (columnIndex) {
		case 0:
			return temp.getID();
		case 1:
			return temp.getUsername();
		case 2:
			return temp.getName();
		case 3:
			return temp.isStaff() ? "Staff" : "Member";
		case 4:
			return temp.getPassword();
		case 5:
			return temp.getEmail();
		case 6:
			return temp.getPhoneNumber();
		default:
			return null;
		}
	}
	
	/**
	 * Overrides isCellEditable from AbstractTableModel to make all cells un-editable
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	/**
	 * updates AccountTableModel
	 */
	public void updateTable() {
		fireTableDataChanged();
	}
	
}
