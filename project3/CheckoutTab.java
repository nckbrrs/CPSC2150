package cu.cs.cpsc2150.project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class CheckoutTab extends JPanel {
	private static final long serialVersionUID = 7665498319503956316L;
	public static CheckoutTab checkoutTab;
	private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
	
	public CheckoutTab(Account currentAccount) {
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new BorderLayout());
		JPanel chkTablesPanel = new JPanel(new FlowLayout());
		JPanel chkButtonPanel = new JPanel(new FlowLayout());
		
		// left table
		JPanel checkedOutPanel = new JPanel();
		JTable chkCheckedOutTable = new JTable(CheckedOutTableModel.getTable());
		CheckedOutTableModel.getTable().fillTable(currentAccount);
		chkCheckedOutTable.setPreferredScrollableViewportSize(new Dimension(350, 380));
		for (int i=0; i<chkCheckedOutTable.getModel().getColumnCount(); i++) {
			chkCheckedOutTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			chkCheckedOutTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
		checkedOutPanel.add(new JScrollPane(chkCheckedOutTable));
		checkedOutPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
																	"Your Checked Out Books", TitledBorder.CENTER,
																	TitledBorder.TOP));
		
		chkCheckedOutTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable selectedRow = (JTable) e.getSource();
					String selectedBookTitle = CheckedOutTableModel.getTable().getValueAt(selectedRow.getSelectedRow(), 0);
					String selectedBookAuthor = CheckedOutTableModel.getTable().getValueAt(selectedRow.getSelectedRow(), 1);
					Book selectedBook = BookData.getBookData().getBook(selectedBookTitle, selectedBookAuthor);
					CartTableModel.getTable().addTransaction(new Transaction(currentAccount, "Return", selectedBook));
				}
			}
		});
		
		// right table
		JPanel cartPanel = new JPanel();		
		JTable chkTransactionTable = new JTable(CartTableModel.getTable());
		chkTransactionTable.setPreferredScrollableViewportSize(new Dimension(350, 380));
		for (int i=0; i<chkTransactionTable.getModel().getColumnCount(); i++) {
			chkTransactionTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			chkTransactionTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
		cartPanel.add(new JScrollPane(chkTransactionTable));
		cartPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
																	"Cart", TitledBorder.CENTER,
																	TitledBorder.TOP));

		chkTablesPanel.add(checkedOutPanel);
		chkTablesPanel.add(cartPanel);
		
		// buttons
		JButton chkCancelButton = new JButton("Cancel");
		JButton chkOkayButton = new JButton("Complete Transaction");
		chkButtonPanel.add(chkCancelButton);
		chkButtonPanel.add(Box.createHorizontalStrut(490));
		chkButtonPanel.add(chkOkayButton);
		
		chkOkayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!CartTableModel.getTable().isEmpty()) { 
					CartTableModel.getTable().notifyObservers();
					CatalogTableModel.getTable().updateTable();
					CheckedOutTableModel.getTable().fillTable(currentAccount);
				}
				
			}
		});
		
		chkCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CartTableModel.getTable().clearTable();
			}
		});
		
		// add everything to tab
		this.add(chkTablesPanel, BorderLayout.NORTH);
		this.add(chkButtonPanel, BorderLayout.SOUTH);
	}
}
