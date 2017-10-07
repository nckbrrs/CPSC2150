package cu.cs.cpsc2150.project2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -101278534411889226L;
	private static JFrame mainFrame;
	private static LoginDialog loginDialog;
	private static Account account;
	private static JPanel windowPanel = new JPanel();
	private static JLabel userNameLabel = new JLabel();
	private static JLabel userTypeLabel = new JLabel();
	private static JPanel userInfoPanel = new JPanel();
	private static JButton logoutButton = new JButton();
	private static Tabs tabs;
	
	public MainFrame() {
		mainFrame = new JFrame("Barrs Library System");
		loginDialog = new LoginDialog(mainFrame);
		loginDialog.setVisible(true);
		
		mainFrame.setSize(800, 600);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));

		userInfoPanel.setPreferredSize(new Dimension(800, 30));
		userInfoPanel.setLayout(new FlowLayout());
		userInfoPanel.add(userNameLabel);
		userInfoPanel.add(userTypeLabel);
		userInfoPanel.add(logoutButton);
		
		mainFrame.setContentPane(windowPanel);

		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				mainFrame.getContentPane().removeAll();
				mainFrame.dispose();
				loginDialog.setVisible(false);
				loginDialog.getContentPane().removeAll();
				loginDialog.dispose();
				BookData.getBookData().save();
				AccountData.getAccountData().save();
				
				mainFrame = new JFrame("Barrs Library System");
				loginDialog = new LoginDialog(mainFrame);
				loginDialog.setVisible(true);
				mainFrame.setSize(800, 600);
				mainFrame.setResizable(false);
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
				mainFrame.setContentPane(windowPanel);
			} 
		});
	}
	
	public static void initialize() {
		account = LoginDialog.getLoggedInAccount();
		
		tabs = new Tabs(account);

		userNameLabel.setText("Username: " + account.getUsername());
		userTypeLabel.setText("| Account Type: " + (account.isStaff() ? "Staff" : "Member"));
		logoutButton.setText("Logout");
		
		windowPanel.add(tabs);
		windowPanel.add(userInfoPanel);
		windowPanel.add(Box.createRigidArea(new Dimension(0, 15)));
	} 
}


