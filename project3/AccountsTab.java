package cu.cs.cpsc2150.project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class AccountsTab extends JPanel {
	private static final long serialVersionUID = 4691502415101796718L;
	public static AccountsTab accountsTab;
	private ViewAccountDialog viewAccountDialog;
	private AddAccountDialog addAccountDialog;
	private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();

	public AccountsTab(Account currentAccount) {
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BorderLayout());
		JPanel accTablePanel = new JPanel(new BorderLayout());
		JPanel accButtonPanel = new JPanel(new FlowLayout());
		
		JTable accTable = new JTable(AccountTableModel.getTable());
		accTable.setPreferredScrollableViewportSize(new Dimension(750, 430));
		accTablePanel.add(new JScrollPane(accTable), BorderLayout.CENTER);
		for (int i=0; i<accTable.getModel().getColumnCount(); i++) {
			accTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			accTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
		
		JButton addAccButton = new JButton("Add Account");
		JButton removeAccButton = new JButton("Remove Account");
		
		accButtonPanel.add(removeAccButton);
		accButtonPanel.add(addAccButton);
		accButtonPanel.add(Box.createHorizontalStrut(490));
		
		this.add(accTablePanel, BorderLayout.NORTH);
		this.add(accButtonPanel, BorderLayout.SOUTH);
		
		accTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable selectedRow = (JTable) e.getSource();
					Account selectedAccount = AccountData.getAccountData().getAccount(selectedRow.getSelectedRow());
					viewAccountDialog = new ViewAccountDialog(selectedAccount);
					viewAccountDialog.setVisible(true);
				}
			}
		});
		
		addAccButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addAccountDialog = new AddAccountDialog();
				addAccountDialog.setVisible(true);
			}
		});
		
		removeAccButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (accTable.getSelectedRow() != -1) {
					Account selectedAccount = AccountData.getAccountData().getAccount(accTable.getSelectedRow());
					boolean hasBooksCheckedOut = false;
					for (Book b : BookData.getBookData().getBooks()) {
						if (selectedAccount.getID() == b.getUserID()) hasBooksCheckedOut = true;
					}

					if (hasBooksCheckedOut) {
						JDialog hasBooksDialog = new JDialog();
						hasBooksDialog.setModal(true);
						hasBooksDialog.setResizable(false);
						hasBooksDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JPanel hasBooksPanel = new JPanel(new FlowLayout());
						JLabel hasBooksLabel = new JLabel("Account cannot be removed because it has books checked out!");
						hasBooksPanel.add(hasBooksLabel);
						hasBooksDialog.setContentPane(hasBooksPanel);
						hasBooksDialog.setSize(hasBooksLabel.getText().length() * 7, 50);
						hasBooksDialog.setLocationRelativeTo(null);
						hasBooksDialog.setVisible(true);
					}
					
					if (!(selectedAccount.getUsername().equals("admin") || hasBooksCheckedOut)) {
						AccountData.getAccountData().removeAccount(selectedAccount);
					}
				}
			}
		});
	}
}
