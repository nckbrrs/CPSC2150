package cu.cs.cpsc2150.project2;

import java.io.Serializable;

/**
 * The Book class represents a single Book in the Library system.
 * @author nickbarrs
 *
 */

public class Book implements Serializable {
	/**
	 * Generated serializable ID
	 */
	private static final long serialVersionUID = -316763998222913610L;
	/**
	 * Represents the title, author, genre, and tags of this Book instance, respectively
	 */
	private String title, author, genre, tags;
	/**
	 * A value of true indicates that this Book instance is checked out, a value of false indicates that it is not
	 */
	private boolean checkedOut;
	/**
	 * Represents the ID of the Account who has this Book instance checked out.
	 */
	private int userID;
			
	/**
	 * Book constructor that sets the inputted Strings appropriately and also initializes the checkedOut and userID
	 * members to false and -1, respectively, to represent that this Book instance is not checked out yet.
	 * 
	 * @param title String that is assigned to this Book instance's title via a setter method
	 * @param author String that is assigned to this Book instance's author via a setter method
	 * @param genre String that is assigned to this Book intance's genre via a setter method
	 * @param tags String that is assigned to this Book isntance's tags via a setter method
	 */
	public Book(String title, String author, String genre, String tags) {
		this.setTitle(title);
		this.setAuthor(author);
		this.setGenre(genre);
		this.setTags(tags);
		this.setCheckedOut(false);
		this.setUserID(-1);
	}

	/**
	 * @return this Book instance's title string
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title String to be assigned to this Book instance's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return this Book instance's author String
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author String to be assigned to this Book instance's author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return this Book instance's genre String
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre String to be assigned to this Book instance's genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * @return this Book instance's tags String
	 */
	public String getTags() {
		return tags;
	}
	
	/**
	 * @param tags String to be assigned to this Book instance's tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return this Book instance's checkedOut boolean value
	 */
	public boolean isCheckedOut() {
		return checkedOut;
	}

	/**
	 * @param checkedOut boolean value to be assigned to this Book instance's checkedOut
	 */
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	/**
	 * @return this Book instance's userID integer value
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param iD integer value to be assigned to this Book instance's userID
	 */
	public void setUserID(int iD) {
		this.userID = iD;
	}
}
