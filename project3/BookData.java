package cu.cs.cpsc2150.project2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The BookData class holds an ArrayList of every currently existing Book instance in the library,
 * and contains methods to add/remove/retrieve specific Book instances as well as
 * methods to save and load its data.
 * @author nickbarrs
 */

public class BookData implements Serializable {
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = -9213652839034056649L;
	/**
	 * Save path to where the BookData data is saved
	 */
	private static final String save_path = "book_data.dat";
	/**
	 * Static instance of the BookData class
	 */
	private static BookData bookData = new BookData();
	/**
	 * ArrayList of currently existing Book instances in the library
	 */
	private ArrayList<Book> books;
	
	/**
	 * BookData() constructs the static BookData by calling the load() method.
	 */
	public BookData() {
		load();
	}
	
	/**
	 * @return the static instance of BookData
	 */
	public static BookData getBookData() {
		return bookData;
	}
	
	/**
	 * @return the ArrayList of Books belonging to BookData
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}
	
	/**
	 * addBook(Book b) Adds the sent in Book instance to the ArrayList of Books belonging to BookData.
	 * it validates the sent in Book instance's data before adding it to the ArrayList. After the Book is
	 * added, BookData's data is saved by calling the save() method. 
	 * The corresponding CatalogTableModel is updated.
	 * 
	 * @param b Book to be added to the Books ArrayList belonging to BookData
	 * @return true boolean if the add was a sucess, and false boolean if not
	 */
	public boolean addBook(Book b) {
		if (ValidateData.validateBookAdd(b)) {
			books.add(b);
			save();
			CatalogTableModel.getTable().updateTable();
			return true;
		}
		return false;
	}
	
	/**
	 * removeBook(Book b) removes the sent in Book instance from the Book ArrayList belonging to BookData.
	 * After removal, the BookData data is saved by calling the save() method. 
	 * The corresponding CatalogTableModel is updated.
	 * 
	 * @param b Book to be removed from the Book ArrayList belonging to BookData
	 */
	public void removeBook(Book b) {
		books.remove(b);
		save();
		CatalogTableModel.getTable().updateTable();
	}
	
	/**
	 * @param index integer index in the Books ArrayList of the desired Book instance
	 * @return instance of the desired Book
	 */
	public Book getBook(int index) {
		return books.get(index);
	}
	
	/**
	 * @param title String title of the desired Book instance
	 * @param author String author of the desired Book instance
	 * @return instance of the desired Book with title and author corresponding
	 *  to the sent in parameter. returns null if Book instance is not found in the Books ArrayList
	 */
	public Book getBook(String title, String author) {
		for (Book b: books)
			if (b.getTitle().equals(title) && b.getAuthor().equals(author))
				return b;
		return null;
	}
	
	/**
	 * @return integer size of Books ArrayList
	 */
	public int getNumBooks() {
		return books.size();
	}
	
	/**
	 * save() saves BookData's ArrayList of Books to the save path
	 */
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save_path));
			oos.writeObject(books);
			oos.close();
		} catch (Exception e) {
			System.out.println("book save error: " + e.getMessage());
		}
	}
	
	/**
	 * load() loads BookData's ArrayList of Books from the save path
	 * if the file is null or not found, a new Books ArrayList is initialized
	 */
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(save_path));
			books = (ArrayList<Book>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			books = new ArrayList<Book>();
		}
	}
}
