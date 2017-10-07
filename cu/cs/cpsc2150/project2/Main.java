package cu.cs.cpsc2150.project2;

import javax.swing.SwingUtilities;

/**
 * Main function that begins the execution of the entire library program
 * @author nickbarrs
 *
 */
public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Account admin = new Account("admin", "", true, "Admin", "", "");
				admin.setID(0);
				AccountData.getAccountData().addAccount(admin);
				MainFrame Library = new MainFrame();
				Library.setEnabled(true);
			}
		});
	}
}
