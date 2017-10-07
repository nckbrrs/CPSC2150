package cu.cs.cpsc2150.project2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Transaction class implements the Observer interface and represents
 * a single instance of a checkout or return transaction in the Checkout Tab.
 * @author nickbarrs
 *
 */
public class Transaction implements Observer {
	/**
	 * Account that is performing this Transaction
	 */
	private Account account;
	/**
	 * String the specifies the action being performed by the Transaction
	 */
	private String action;
	/**
	 * Book to which the Transaction's action is applied to
	 */
	private Book book;
	
	/**
	 * Constructor that assigns inputted Account, String, and Book to this instance's members
	 * @param a Account to be assigned to this Transaction instance's account
	 * @param s String to be assigned to this Transaction instance's action
	 * @param b Book to be assigned to this Transaction instance's book
	 */
	public Transaction(Account a, String s, Book b) {
		account = a;
		action = s;
		book = b;
	}

	/**
	 * @return this Transaction instance's action String
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * @return this Transaction instance's Book
	 */
	public Book getBook() {
		return book;
	}
	
	/**
	 * Implements the Observer interface's notify method appropriately depending on this Transaction instance's action.
	 * If the currently logged in Account is a Staff type, a dialog pops up prompting the user to enter the integer
	 * ID of a currently existing member account on whose behalf the following Transactions will be performed.
	 * Includes an error dialog pop up if the entered integer does not correspond to an existing member Account's ID.
	 * After these are taken care of, the method either:
	 * 1) performs a checkout of the Book--sets the Book's isCheckedOut boolean to true 
	 * 		and userID value to the ID of the Account checking it out, or
	 * 2) performs a return of the Book--sets the Book's isCheckedOut boolean to false
	 * 		and userID value to -1 to represent that it is no longer checked out
	 */
	@Override
	public void Notify() {
		if (account.isStaff()) {
			JDialog enterIDDialog = new JDialog();
			enterIDDialog.setModal(true);
			enterIDDialog.setResizable(false);
			enterIDDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			enterIDDialog.setSize(new Dimension(380, 100));
			enterIDDialog.setLocationRelativeTo(null);
			JPanel windowPanel = new JPanel();
			JLabel enterIDLabel1 = new JLabel("Staff cannot checkout books. Enter the ID of an existing");
			JLabel enterIDLabel2 = new JLabel("   member account for whom these transactions will be ");
			JLabel enterIDLabel3 = new JLabel("         performed on behalf of, and press Enter:");
			JTextField enterIDField = new JTextField();
			
			enterIDField.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {		
						JDialog idErrorDialog = new JDialog();
						idErrorDialog.setTitle("Error!");
						idErrorDialog.setModal(true);
						idErrorDialog.setResizable(false);
						idErrorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JPanel windowPanel2 = new JPanel(new FlowLayout());
						JLabel idErrorLabel = new JLabel();
						windowPanel2.add(idErrorLabel);
						idErrorDialog.setContentPane(windowPanel2);
						
						// check that value entered is an integer ID corresponding to an existing account
						boolean validID = false;
						try {
							for (Account a : AccountData.getAccountData().getAccounts()) {
								if (Integer.parseInt(enterIDField.getText()) == a.getID() && !a.isStaff()) {
									validID = true;
									account = a;
									enterIDDialog.setVisible(false);
								}
							}
							if (!validID) {
								idErrorLabel.setText("Only enter the ID of an existing member account!");
								idErrorDialog.setSize(idErrorLabel.getText().length() * 7, 50);
								idErrorDialog.setLocationRelativeTo(null);
								idErrorDialog.setVisible(true);
							}
							
						} catch (Exception ex) {
							idErrorLabel.setText("Enter an integer!");
							idErrorDialog.setSize(idErrorLabel.getText().length() * 7, 50);
							idErrorDialog.setLocationRelativeTo(null);
							idErrorDialog.setVisible(true);
						}
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {}
				
				@Override
				public void keyTyped(KeyEvent e) {}
				
			});
			
			windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
			windowPanel.add(enterIDLabel1);
			windowPanel.add(enterIDLabel2);
			windowPanel.add(enterIDLabel3);
			windowPanel.add(enterIDField);
			enterIDDialog.setContentPane(windowPanel);
			enterIDDialog.setVisible(true);
		}
		
		if (this.action.equals("Check Out")) {
			book.setUserID(account.getID());
			book.setCheckedOut(true);
			BookData.getBookData().save();
		}
		else {
			book.setUserID(-1);
			book.setCheckedOut(false);
			BookData.getBookData().save();
		}
	}
}
