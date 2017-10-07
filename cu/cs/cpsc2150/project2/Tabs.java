package cu.cs.cpsc2150.project2;

import java.awt.*;
import javax.swing.*;

public class Tabs extends JTabbedPane {
	private static final long serialVersionUID = -5727367025530675363L;

	public Tabs(Account currentAccount) {
		setPreferredSize(new Dimension(800, 570));

		CatalogTab.catalogTab = new CatalogTab(currentAccount);
		addTab("Catalog", CatalogTab.catalogTab);
		
		AccountsTab.accountsTab = new AccountsTab(currentAccount);
		if (currentAccount.isStaff()) addTab("Accounts", AccountsTab.accountsTab);
		
		CheckoutTab.checkoutTab = new CheckoutTab(currentAccount);
		addTab("Checkout", CheckoutTab.checkoutTab);
	}
}
