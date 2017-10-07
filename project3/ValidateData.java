package cu.cs.cpsc2150.project2;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The ValidateData class contains methods that check whether the data for the Account or Book being 
 * added or edited are valid, and corresponding JDialogs that pop up and tell the user of any
 * errors present
 * @author nickbarrs
 *
 */

public abstract class ValidateData {
	private static JDialog errorDialog = new JDialog();
	private static JPanel windowPanel = new JPanel(new FlowLayout());
	private static JLabel errorLabel = new JLabel();
	
	public static boolean validateAccountAdd(Account accountToAdd) {
		errorDialog.setTitle("Error!");
		errorDialog.setModal(true);
		errorDialog.setResizable(false);
		errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		windowPanel.add(errorLabel);
		errorDialog.setContentPane(windowPanel);
		
		// username is already taken
		for (Account acc : AccountData.getAccountData().getAccounts()) {
			if (accountToAdd.getUsername().equals(acc.getUsername())) {
				errorLabel.setText("Username is already taken!");
				errorDialog.setSize(errorLabel.getText().length() * 7, 50);
				errorDialog.setLocationRelativeTo(null);
				errorDialog.setVisible(true);
				return false;
			}
		}
		
		// username is too long or short
		if (accountToAdd.getUsername().length() > 20 || accountToAdd.getUsername().length() < 1) {
			errorLabel.setText("Username should be 1-20 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// username is not correctly formatted
		if (!accountToAdd.getUsername().matches("([0-9A-z]+)")) {
			errorLabel.setText("Username should only contain letters and numbers!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// password is too long or short
		if (accountToAdd.getPassword().length() > 20 || accountToAdd.getPassword().length() < 1) {
			errorLabel.setText("Password should be 1-20 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// password has a space
		boolean pwHasSpace = false;
		for (int i=0; i<accountToAdd.getPassword().length(); i++) {
			if (accountToAdd.getPassword().charAt(i) == ' ') pwHasSpace = true;
		}
		
		if (pwHasSpace) {
			errorLabel.setText("Password cannot contain a space!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// name is too long or short
		if (accountToAdd.getName().length() > 30 || accountToAdd.getName().length() < 1) {
			errorLabel.setText("Name should be 1-30 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// name is not correctly formatted
		if (!accountToAdd.getName().matches("([A-z]+[ ])+([A-z]+)")) {
			errorLabel.setText("Name should only contain letters and necessary spaces!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// email is not standard form
		if (!accountToAdd.getEmail().matches("[0-9A-z]+[@][A-z]+[.][A-z]+")) {
			errorLabel.setText("Email should be of the form eml@domain.xyz!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// phone is not in standard form
		if (!accountToAdd.getPhoneNumber().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
			errorLabel.setText("Phone # should be of the form ###-###-####!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		return true;
	}

	public static boolean validateAccountEdit(Account original, Account edited) {
		errorDialog.setTitle("Error!");
		errorDialog.setModal(true);
		errorDialog.setResizable(false);
		errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		windowPanel.add(errorLabel);
		errorDialog.setContentPane(windowPanel);
		
		// username is already taken (don't trigger this error if "account that already exists" is self)
		for (Account acc : AccountData.getAccountData().getAccounts()) {
			if (edited.getUsername().equals(acc.getUsername())) {
				if (!(edited.getUsername().equals(original.getUsername()))) {
					errorLabel.setText("Username is already taken!");
					errorDialog.setSize(errorLabel.getText().length() * 7, 50);
					errorDialog.setLocationRelativeTo(null);
					errorDialog.setVisible(true);
					return false;
				}
			}
		}
		
		// username is too long or short
		if (edited.getUsername().length() > 20 || edited.getUsername().length() < 1) {
			errorLabel.setText("Username should be 1-20 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// username is not correctly formatted
		if (!edited.getUsername().matches("([0-9A-z]+)")) {
			errorLabel.setText("Username should only contain letters and numbers!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// password is too long or short
		if (edited.getPassword().length() > 20 || edited.getPassword().length() < 1) {
			errorLabel.setText("Password should be 1-20 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// password has a space
		boolean pwHasSpace = false;
		for (int i=0; i<edited.getPassword().length(); i++) {
			if (edited.getPassword().charAt(i) == ' ') pwHasSpace = true;
		}
		
		if (pwHasSpace) {
			errorLabel.setText("Password cannot contain a space!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// name is too long or short
		if (edited.getName().length() > 30 || edited.getName().length() < 1) {
			errorLabel.setText("Name should be 1-30 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// name is not correctly formatted
		if (!edited.getName().matches("([A-z]+[ ])+([A-z]+)")) {
			errorLabel.setText("Name should only contain letters and necessary spaces!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// email is not standard form
		if (!edited.getEmail().matches("[0-9A-z]+[@][A-z]+[.][A-z]+")) {
			errorLabel.setText("Email should be of the form eml@domain.xyz!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// phone is not in standard form
		if (!edited.getPhoneNumber().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
			errorLabel.setText("Phone # should be of the form ###-###-####!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		return true;
	}
	
	public static boolean validateBookAdd(Book bookToAdd) {		 
		errorDialog.setTitle("Error!");
		errorDialog.setModal(true);
		errorDialog.setResizable(false);
		errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		windowPanel.add(errorLabel);
		errorDialog.setContentPane(windowPanel);
		
		// Book already exists in catalog
		for (Book bk : BookData.getBookData().getBooks()) {
			if (bookToAdd.getTitle().equals(bk.getTitle()) && (bookToAdd.getAuthor().equals(bk.getAuthor()))) {
				errorLabel.setText("Book already exists!");
				errorDialog.setSize(errorLabel.getText().length() * 7, 50);
				errorDialog.setLocationRelativeTo(null);
				errorDialog.setVisible(true);
				return false;
			}
		}
		
		// Title is empty
		if (bookToAdd.getTitle().length() < 1) {
			errorLabel.setText("Title field cannot be empty!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Title is not in standard form
		if (!bookToAdd.getTitle().matches("([^ ]+[ ]{1})*([^ ]+)")) {
			errorLabel.setText("Title should not begin/end with or contain un-needed spaces!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Author has an integer
		boolean authorHasInteger = false;
		for (int i=0; i<bookToAdd.getAuthor().length(); i++) {
			if (Character.isDigit(bookToAdd.getAuthor().charAt(i))) authorHasInteger = true;
		}
		
		if (authorHasInteger) {
			errorLabel.setText("Author cannot contain an integer!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Author is too short or long
		if (bookToAdd.getAuthor().length() > 50 || bookToAdd.getAuthor().length() < 1) {
			errorLabel.setText("Author should be 1-50 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// Genre is too short or too long
		if (bookToAdd.getGenre().length() > 30 || bookToAdd.getGenre().length() < 1) {
			errorLabel.setText("Genre should be 1-30 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// Genre is not in standard form
		if (!bookToAdd.getGenre().matches("[A-z]+([ ][A-z]+)*")) {
			errorLabel.setText("Genre should only contain letters and spaces!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Tags are not in standard form
		if (!bookToAdd.getTags().matches("(([A-z]+([ ][A-z]+)*[,][ ])*(([A-z]+([ ][A-z]+)*))*)")) {
			errorLabel.setText("Tags should be in a comma-separated list!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		return true;
	}

	public static boolean validateBookEdit(Book original, Book edited) {		 
		errorDialog.setTitle("Error!");
		errorDialog.setModal(true);
		errorDialog.setResizable(false);
		errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		windowPanel.add(errorLabel);
		errorDialog.setContentPane(windowPanel);
		
		// Book already exists in catalog (don't trigger this error if "book that already exists" is self)
		for (Book bk : BookData.getBookData().getBooks()) {
			if ((edited.getTitle().equals(bk.getTitle()) && (edited.getAuthor().equals(bk.getAuthor())))) {
				if (!(edited.getTitle().equals(original.getTitle()))) {
					errorLabel.setText("Book already exists!");
					errorDialog.setSize(errorLabel.getText().length() * 7, 50);
					errorDialog.setLocationRelativeTo(null);
					errorDialog.setVisible(true);
					return false;
				}
			}
		}
		
		// Title is empty
		if (edited.getTitle().length() < 1) {
			errorLabel.setText("Title field cannot be empty!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Title is not in standard form
		if (!edited.getTitle().matches("([^ ]+[ ]{1})*([^ ]+)")) {
			errorLabel.setText("Title should not begin/end with or contain un-needed spaces!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Author has an integer
		boolean authorHasInteger = false;
		for (int i=0; i<edited.getAuthor().length(); i++) {
			if (Character.isDigit(edited.getAuthor().charAt(i))) authorHasInteger = true;
		}
		
		if (authorHasInteger) {
			errorLabel.setText("Author cannot contain an integer!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Author is too short or long
		if (edited.getAuthor().length() > 50 || edited.getAuthor().length() < 1) {
			errorLabel.setText("Author should be 1-50 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// Genre is too short or too long
		if (edited.getGenre().length() > 30 || edited.getGenre().length() < 1) {
			errorLabel.setText("Genre should be 1-30 characters!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;		
		}
		
		// Genre is not in standard form
		if (!edited.getGenre().matches("[A-z]+([ ][A-z]+)*")) {
			errorLabel.setText("Genre should only contain letters and spaces!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		// Tags are not in standard form
		if (!edited.getTags().matches("(([A-z]+([ ][A-z]+)*[,][ ])*(([A-z]+([ ][A-z]+)*))*)")) {
			errorLabel.setText("Tags should be in a comma-separated list!");
			errorDialog.setSize(errorLabel.getText().length() * 7, 50);
			errorDialog.setLocationRelativeTo(null);
			errorDialog.setVisible(true);
			return false;
		}
		
		return true;
	}
	
}
