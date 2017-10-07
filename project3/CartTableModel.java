package cu.cs.cpsc2150.project2;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * CartTableModel inherits from AbstractTableModel 
 * and represents the table model of the Cart JTable in the Checkout tab
 * @author nickbarrs
 *
 */

public class CartTableModel extends AbstractTableModel {	
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = 6797659544902064750L;
	/**
	 * Static instance of the CartTableModel
	 */
	private static CartTableModel cartTableModel = new CartTableModel();
	/**
	 * Array of Strings corresponding to the columns of the table
	 */
	private String[] columnNames = {"Action to Perform", "Book"};
	/**
	 * ArrayList of Transactions that represent each transaction currently in the cart
	 * waiting to be completed
	 */
	private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	/**
	 * Default constructor
	 */
	public CartTableModel() {
	
	}
	
	/**
	 * @return Static instance of CartTableModel
	 */
	public static CartTableModel getTable() {
		return cartTableModel;
	}
	
	/**
	 * @return Static instance of ArrayList of Transactions
	 */
	public static ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * Adds inputted Transaction to ArrayList and updates table
	 * @param t Transaction to be added to ArrayList of Transactions
	 */
	public void addTransaction(Transaction t) {
		transactions.add(t);
		updateTable();
	}
	
	/** 
	 * Removes inputted Transaction from ArrayList and updates table
	 * @param t Transaction to be removed from ArrayList of Transactions
	 */
	public void removeTransaction(Transaction t) {
		transactions.remove(t);
		updateTable();
	}
	
	/**
	 * Clears ArrayList of Transactions and updates table
	 */
	public void clearTable() {
		transactions.clear();
		updateTable();
	}
	
	/**
	 * @return boolean value of whether or not ArrayList of Transactions is empty
	 */
	public boolean isEmpty() {
		return transactions.isEmpty();
	}
	
	/**
	 * @return integer value of size of ArrayList of Transactions
	 */
	@Override
	public int getRowCount() {
		return transactions.size();
	}

	/**
	 * @return integer value of number of columns in table
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * @param index integer value of index of desired column name in table
	 * @return String name of desired column in table
	 */
	public String getColumnName(int index) {
		return columnNames[index];
	}
	
	/**
	 * @param rowIndex integer value of index of desired row in table
	 * @param columnIndex integer value of index of desired column in table
	 * @return String at location corresponding to inputted rowIndex and columnIndex
	 */
	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Transaction temp = transactions.get(rowIndex);
		if (columnIndex == 0) return temp.getAction();
		if (columnIndex == 1) return temp.getBook().getTitle();
		return null;
	}
	
	/**
	 * Overrides isCellEditable from AbstractTableModel to make all cells un-editable
	 */
	@Override	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * updates table
	 */
	public void updateTable() {
		fireTableDataChanged();
	}
	
	/**
	 * notifies Transactions that the order has been completed and clears cart table
	 */
	public void notifyObservers() {
		for (Transaction t : transactions) {
			t.Notify();
		}
		clearTable();
	}
}
