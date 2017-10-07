package cu.cs.cpsc2150.project2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddAccountDialog extends JDialog {
	private static final long serialVersionUID = -1217457112781633859L;

	public AddAccountDialog() {
		super();
		setTitle("New Account");
		setModal(true);
		setSize(300, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
		
		JPanel usernamePanel = new JPanel(new BorderLayout());
		JLabel usernameLabel = new JLabel("Username");
		JTextField usernameField = new JTextField(15);
		usernamePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField, BorderLayout.EAST);
		
		JPanel passwordPanel = new JPanel(new BorderLayout());
		JLabel passwordLabel = new JLabel("Password");
		JTextField passwordField = new JTextField(15);
		passwordPanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField, BorderLayout.EAST);
		
		JPanel namePanel = new JPanel(new BorderLayout());
		JLabel nameLabel = new JLabel("Name");
		JTextField nameField = new JTextField(15);
		namePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		namePanel.add(nameLabel);
		namePanel.add(nameField, BorderLayout.EAST);
		
		JPanel emailPanel = new JPanel(new BorderLayout());
		JLabel emailLabel = new JLabel("Email\nAddress");
		JTextField emailField = new JTextField(15);
		emailPanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField, BorderLayout.EAST);

		JPanel phonePanel = new JPanel(new BorderLayout());
		JLabel phoneLabel = new JLabel("Phone\nNumber");
		JTextField phoneField = new JTextField(15);
		phonePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneField, BorderLayout.EAST);
		
		JPanel typePanel = new JPanel(new BorderLayout());
		JLabel typeLabel = new JLabel("Type");
		JCheckBox typeCheckBox = new JCheckBox("Staff?");
		typePanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
		typePanel.add(typeLabel);
		typePanel.add(typeCheckBox, BorderLayout.EAST);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);
		
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
				Account newAccount = new Account(usernameField.getText(),
												passwordField.getText(),
												typeCheckBox.isSelected() ? true : false,
												nameField.getText(),
												emailField.getText(),
												phoneField.getText());
				if(AccountData.getAccountData().addAccount(newAccount)) setVisible(false);
			}
		});
	}
}
