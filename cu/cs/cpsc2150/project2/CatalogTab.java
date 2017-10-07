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

public class CatalogTab extends JPanel {
	private static final long serialVersionUID = -6050901552639388705L;
	public static CatalogTab catalogTab;
	private ViewBookDialog viewBookDialog;
	private AddBookDialog addBookDialog;
	private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
	public CatalogTab(Account currentAccount) {
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BorderLayout());
		JPanel catTablePanel = new JPanel(new BorderLayout());
		JPanel catButtonPanel = new JPanel(new FlowLayout());
				
		JTable catTable = new JTable(CatalogTableModel.getTable());
		catTable.setPreferredScrollableViewportSize(new Dimension(750, 430));
		catTablePanel.add(new JScrollPane(catTable), BorderLayout.CENTER);
		for (int i=0; i<catTable.getModel().getColumnCount(); i++) {
			catTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			catTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
	
		JButton addBookButton = new JButton("Add Book");
		JButton removeBookButton = new JButton("Remove Book");
		JButton addToCheckoutButton = new JButton("Add to Checkout");
		
		if (currentAccount.isStaff()) {
			catButtonPanel.add(removeBookButton);
			catButtonPanel.add(addBookButton);
			catButtonPanel.add(Box.createHorizontalStrut(375));
		}
		
		catButtonPanel.add(addToCheckoutButton);
		this.add(catTablePanel, BorderLayout.NORTH);
		this.add(catButtonPanel, BorderLayout.SOUTH);
		
		catTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable selectedRow = (JTable) e.getSource();
					Book selectedBook = BookData.getBookData().getBook(selectedRow.getSelectedRow());
					if (currentAccount.isStaff()) viewBookDialog = new ViewBookDialog(selectedBook, true);
					else viewBookDialog = new ViewBookDialog(selectedBook, false);
					viewBookDialog.setVisible(true);
				}
			}
		});
		
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBookDialog = new AddBookDialog();
				addBookDialog.setVisible(true);
			}
		});
		
		removeBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (catTable.getSelectedRow() != -1) {
					Book selectedBook = BookData.getBookData().getBook(catTable.getSelectedRow());
					boolean canRemove = true;
					for (Transaction t : CartTableModel.getTransactions()) {
						if (t.getBook().equals(selectedBook)) {
							canRemove = false;
						}
					}
					if (selectedBook.isCheckedOut()) canRemove = false;
					if (canRemove) {
						BookData.getBookData().removeBook(selectedBook);
					}
					else {
						JDialog cantRemoveDialog = new JDialog();
						cantRemoveDialog.setModal(true);
						cantRemoveDialog.setResizable(false);
						cantRemoveDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JPanel cantRemovePanel = new JPanel(new FlowLayout());
						JLabel cantRemoveLabel = new JLabel("Book is in cart or checked out!");
						cantRemovePanel.add(cantRemoveLabel);
						cantRemoveDialog.setContentPane(cantRemovePanel);
						cantRemoveDialog.setSize((cantRemoveLabel.getText().length() * 7), 50);
						cantRemoveDialog.setLocationRelativeTo(null);
						cantRemoveDialog.setVisible(true);
					}
				}
			}
		});
		
		addToCheckoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (catTable.getSelectedRow() != -1) {
					Book selectedBook = BookData.getBookData().getBook(catTable.getSelectedRow());
					boolean canCheckout = true;
					for (Transaction t : CartTableModel.getTransactions()) {
						if (t.getBook().equals(selectedBook)) {
							canCheckout = false;
						}
					}
					if (selectedBook.isCheckedOut()) canCheckout = false;
					if (canCheckout) {
						CartTableModel.getTable().addTransaction(new Transaction(currentAccount, "Check Out", selectedBook));
					}
					else {
						JDialog cantCheckoutDialog = new JDialog();
						cantCheckoutDialog.setModal(true);
						cantCheckoutDialog.setResizable(false);
						cantCheckoutDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						JPanel cantCheckoutPanel = new JPanel(new FlowLayout());
						JLabel cantCheckoutLabel = new JLabel("Book is checked out or already in cart!");
						cantCheckoutPanel.add(cantCheckoutLabel);
						cantCheckoutDialog.setContentPane(cantCheckoutPanel);
						cantCheckoutDialog.setSize((cantCheckoutLabel.getText().length() * 7), 50);
						cantCheckoutDialog.setLocationRelativeTo(null);
						cantCheckoutDialog.setVisible(true);
					}
				}
			}
		});
	}
}
