package cu.cs.cpsc2150.project2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewAccountDialog extends JDialog {
	private static final long serialVersionUID = 5908988914286913100L;

	public ViewAccountDialog(Account a) {
		super();
		setTitle("Account Details");
		setModal(true);
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
	
		JPanel usernamePanel = new JPanel(new BorderLayout());
		JLabel usernameLabel = new JLabel("Username");
		JTextField usernameField = new JTextField(a.getUsername(), 15);
		usernamePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField, BorderLayout.EAST);
		
		JPanel passwordPanel = new JPanel(new BorderLayout());
		JLabel passwordLabel = new JLabel("Password");
		JTextField passwordField = new JTextField(a.getPassword(), 15);
		passwordPanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField, BorderLayout.EAST);

		JPanel namePanel = new JPanel(new BorderLayout());
		JLabel nameLabel = new JLabel("Name");
		JTextField nameField = new JTextField(a.getName(), 15);
		namePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		namePanel.add(nameLabel);
		namePanel.add(nameField, BorderLayout.EAST);
		
		JPanel emailPanel = new JPanel(new BorderLayout());
		JLabel emailLabel = new JLabel("Email\nAddress");
		JTextField emailField = new JTextField(a.getEmail(), 15);
		emailPanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField, BorderLayout.EAST);

		JPanel phonePanel = new JPanel(new BorderLayout());
		JLabel phoneLabel = new JLabel("Phone\nNumber");
		JTextField phoneField = new JTextField(a.getPhoneNumber(), 15);
		phonePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneField, BorderLayout.EAST);
		
		JPanel typePanel = new JPanel(new BorderLayout());
		JLabel typeLabel = new JLabel("Type");
		JCheckBox typeCheckBox = new JCheckBox("Staff?", a.isStaff() ? true : false);
		typePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		typePanel.add(typeLabel);
		typePanel.add(typeCheckBox, BorderLayout.EAST);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton, BorderLayout.EAST);
		
		if (a.getUsername().equals("admin")) {
			usernameField.setEditable(false);
			passwordField.setEditable(false);
			typeCheckBox.setEnabled(false);
			nameField.setEditable(false);
			emailField.setEditable(false);
			phoneField.setEditable(false);
		}
			
		windowPanel.add(usernamePanel);
		windowPanel.add(passwordPanel);
		windowPanel.add(namePanel);
		windowPanel.add(emailPanel);
		windowPanel.add(phonePanel);
		windowPanel.add(typePanel);
		windowPanel.add(buttonPanel);
		
		this.setContentPane(windowPanel);
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Account edited = new Account(usernameField.getText(),
											passwordField.getText(),
											typeCheckBox.isSelected() ? true : false,
											nameField.getText(), 
											emailField.getText(),
											phoneField.getText());
				if (ValidateData.validateAccountEdit(a, edited)) {
					a.setUsername(usernameField.getText());
					a.setPassword(passwordField.getText());
					a.setStaff((typeCheckBox.isSelected() ? true : false));
					a.setName(nameField.getText());
					a.setEmail(emailField.getText());
					a.setPhoneNumber(phoneField.getText());
					AccountData.getAccountData().save();
					AccountTableModel.getTable().updateTable();
					setVisible(false);
				}
			}
		});
	}
}
