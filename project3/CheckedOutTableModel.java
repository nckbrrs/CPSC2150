package cu.cs.cpsc2150.project2;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * CheckedOutTableModel inherits from AbstractTableModel
 * and represents the table model of the Checked Out JTable in the Checkout tab
 * @author nickbarrs
 *
 */

public class CheckedOutTableModel extends AbstractTableModel {
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = 2555528549658307217L;
	/**
	 * Static instance of CheckedOutTableModel
	 */
	private static CheckedOutTableModel checkedOutTableModel = new CheckedOutTableModel();
	/** Array of Strings corresponding to the names of the columns of the table
	 * 
	 */
	private String[] columnNames = {"Title", "Author", "Genre"};
	/**
	 * ArrayList of Books to represent the Books that are checked out by a specified user
	 */
	private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
	
	/**
	 * Default constructor
	 */
	public CheckedOutTableModel() {
		super();
	}

	/**
	 * @return Static instance of CheckedOutTableModel
	 */
	public static CheckedOutTableModel getTable() {
		return checkedOutTableModel;
	}
	
	/**
	 * fillTable(Account a) Fills the checkedOutBooks ArrayList with all Books that are currently
	 * checked out by the inputted Account. it also updates the table.
	 * @param a Account whose checked out books will fill CheckedOutTable's Book ArrayList 
	 */
	public void fillTable(Account a) {
		checkedOutBooks.clear();
		for (Book b : BookData.getBookData().getBooks())
			if (b.getUserID() == a.getID()) 
				checkedOutBooks.add(b);
		
		updateTable();
	}
	
	/**
	 * Adds inputted Book to checkedOutBooks and updates table
	 * @param b Book to be added to checkedOutBook
	 */
	public void addBook(Book b) {
		checkedOutBooks.add(b);
		updateTable();
	}
	
	/**
	 * Removes inputted Book from checkedOutBooks and updates table
	 * @param b Book to be removed from checkedOutTable
	 */
	public void removeBook(Book b) {
		checkedOutBooks.remove(b);
		updateTable();
	}
	
	/**
	 * Clears checkedOutBooks and updates table
	 */
	public void clearTable() {
		checkedOutBooks.clear();
		updateTable();
	}
	
	/**
	 * @return integer value of number of Books in checkedOutBooks
	 */
	@Override
	public int getRowCount() {
		return checkedOutBooks.size();
	}

	/**
	 * @return integer value of number of columns in table
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * @param index integer value of index of desired column in table
	 * @return name of desired column in table
	 */
	public String getColumnName(int index) {
		return columnNames[index];
	}
	
	/**
	 * @param rowIndex integer value of index of desired row in CheckedOutTableModel
	 * @param columnIndex integer value of index of desired column in CheckedOutTableModel
	 * @return String at location corresponding to inputted rowIndex and columnIndex
	 */
	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Book temp = checkedOutBooks.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return temp.getTitle();
			case 1:
				return temp.getAuthor();
			case 2:
				return temp.getGenre();
			default:
				return null;
		}
	}
	
	/**
	 * Overrides isCellEditable from AbstractModel to make all cells un-editable
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * updates CheckedOutTableModel
	 */
	public void updateTable() {
		fireTableDataChanged();
	}
}
