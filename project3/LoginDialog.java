package cu.cs.cpsc2150.project2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * LoginDialog contains the GUI elements of the Login Window for the library system as well as
 * methods used to validate inputted usernamd and password and return corresponding Account that is logged into.
 * @author nickbarrs
 *
 */
public class LoginDialog extends JDialog {
	/**
	 * Generated serialized ID
	 */
	private static final long serialVersionUID = -8253894352070350090L;
	/**
	 * local reference to Account instance corresponding to user-inputted username and password
	 */
	private static Account loggedInAccount;
	
	public LoginDialog(JFrame parent) {
		setTitle("Login");
		setEnabled(true);
		setSize(250, 160);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// declare and initialize panels
		JPanel windowPanel = new JPanel (new BorderLayout());
		JPanel loginPanels = new JPanel (new BorderLayout());
		JPanel userPanel = new JPanel (new FlowLayout());
		JPanel pwPanel = new JPanel(new FlowLayout());
		JPanel errorPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		// declare and initialize elements of panels
		JLabel unLabel = new JLabel("Username:");
		JLabel pwLabel = new JLabel("Password:");
		JTextField unField = new JTextField(10);
		JPasswordField pwField = new JPasswordField(10);
		
		// add elements to respective panels
		userPanel.add(unLabel);
		userPanel.add(unField);
		pwPanel.add(pwLabel);
		pwPanel.add(pwField);
		loginPanels.add(userPanel, BorderLayout.NORTH);
		loginPanels.add(pwPanel, BorderLayout.CENTER);
		loginPanels.add(errorPanel, BorderLayout.SOUTH);
			
		JButton cancelButton = new JButton("Cancel");
		JButton okayButton = new JButton("Okay");
		
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(cancelButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(okayButton);
		buttonPanel.add(Box.createHorizontalGlue());

		windowPanel.add(loginPanels, BorderLayout.NORTH);
		windowPanel.add(buttonPanel, BorderLayout.SOUTH);
		this.getContentPane().add(windowPanel);
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				parent.dispose();
				System.exit(0);
			}
		});
		
		okayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (authorized(unField.getText(), new String(pwField.getPassword()))) {
					parent.setVisible(true);
					MainFrame.initialize();
					setVisible(false);
				}
				else {
					errorPanel.removeAll();
					errorPanel.add(new JLabel("Username/Password is invalid!"));
					validate();
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		unField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (authorized(unField.getText(), new String(pwField.getPassword()))) {
						parent.setVisible(true);
						MainFrame.initialize();
						setVisible(false);
					}
					else {
						errorPanel.removeAll();
						errorPanel.add(new JLabel("Username/Password is invalid!"));
						validate();
					}
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		
		pwField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (authorized(unField.getText(), new String(pwField.getPassword()))) {
						parent.setVisible(true);
						MainFrame.initialize();
						setVisible(false);
					}
					else {
						errorPanel.removeAll();
						errorPanel.add(new JLabel("Username/Password is invalid!"));
						validate();
					}
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
	}
	
	/**
	 * Returns truth value of whether inputted username and password correspond to an existing Account in the system,
	 * and assigns corresponding Account instance to local loggedInAccount reference
	 * @param un String representing user-inputted username
	 * @param pw String representing user-inputted password
	 * @return boolean value of true if inputted username and password correspond to an existing Account in the system; false otherwise
	 */
	public boolean authorized(String un, String pw) {
		for (Account a : AccountData.getAccountData().getAccounts())
			if (a.getUsername().equals(un) && a.getPassword().equals(pw)) {
				loggedInAccount = a;
				return true;
			}
		
		return false;
	}
	
	/**
	 * @return local reference to Account instance corresponding to user-inputted username and password
	 */
	public static Account getLoggedInAccount() {
		return loggedInAccount;
	} 
	
}
