package cu.cs.cpsc2150.project2;

import javax.swing.table.AbstractTableModel;

/**
 * CatalogTableModel inherits from AbstractTableModel
 * and represents the table model of the JTable in the Catalog tab
 * @author nickbarrs
 *
 */

public class CatalogTableModel extends AbstractTableModel {
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = -4395919313694620946L;
	/**
	 * Static instance of CatalogTableModel
	 */
	private static CatalogTableModel catalogTableModel = new CatalogTableModel();
	/**
	 * Array of Strings corresponding to the names of the columns of the table
	 */
	private String[] columnNames = {"Title", "Author", "Genre", "Checked Out"};
	/**
	 * Local reference to the static BookData
	 */
	private BookData myBookData = BookData.getBookData();
	
	/**
	 * Default constructor
	 */
	private CatalogTableModel() {
		super();
	}
	
	/**
	 * @return Static instance of CatalogTableModel
	 */
	public static CatalogTableModel getTable() {
		return catalogTableModel;
	}
	
	/**
	 * @return integer number of Books in BookData
	 */
	@Override
	public int getRowCount() {
		return myBookData.getNumBooks();
	}

	/**
	 * @return integer number of columns in CatalogTableModel
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	/**
	 * @param index integer value of index of desired column in CatalogTableModel
	 * @return name of desired column in CatalogTableModel
	 */
	public String getColumnName(int index) {
		return columnNames[index];
	}
	
	/**
	 * @param rowIndex integer value of index of desired row in CatalogTableModel
	 * @param columnIndex integer value of index of desired column in CatalogTableModel
	 * @return String at location corresponding to inputted rowIndex and columnIndex of CatalogTableModel
	 */
	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Book temp = myBookData.getBook(rowIndex);
		switch (columnIndex) {
		case 0:
			return temp.getTitle();
		case 1:
			return temp.getAuthor();
		case 2:
			return temp.getGenre();
		case 3:
			return temp.isCheckedOut() ? ("Yes - Account ID: " + temp.getUserID()) : "No";
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
	 * updates CatalogTableModel
	 */
	public void updateTable() {
		fireTableDataChanged();
	}

}
