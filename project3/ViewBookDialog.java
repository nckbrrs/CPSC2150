package cu.cs.cpsc2150.project2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewBookDialog extends JDialog {
	private static final long serialVersionUID = 7376533332600110856L;

	public ViewBookDialog(Book b, boolean staff) {
		super();
		setTitle("Book Details");
		setSize(300, 210);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
		
		JPanel titlePanel = new JPanel(new FlowLayout());
		JLabel titleLabel = new JLabel("Title");
		JTextField titleField = new JTextField(b.getTitle(), 10);
		if (!staff) titleField.setEditable(false);
		titlePanel.add(titleLabel);
		titlePanel.add(titleField);
		
		JPanel authorPanel = new JPanel(new FlowLayout());
		JLabel authorLabel = new JLabel("Author");
		JTextField authorField = new JTextField(b.getAuthor(), 10);
		if (!staff) authorField.setEditable(false);
		authorPanel.add(authorLabel);
		authorPanel.add(authorField);
		
		JPanel genrePanel = new JPanel(new FlowLayout());
		JLabel genreLabel = new JLabel("Genre");
		JTextField genreField = new JTextField(b.getGenre(), 10);
		if (!staff) genreField.setEditable(false);
		genrePanel.add(genreLabel);
		genrePanel.add(genreField);
		
		JPanel tagsPanel = new JPanel(new FlowLayout());
		JLabel tagsLabel = new JLabel("Tags");
		JTextField tagsField = new JTextField(b.getTags().toString(), 10);
		if (!staff) tagsField.setEditable(false);
		tagsPanel.add(tagsLabel);
		tagsPanel.add(tagsField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton saveButton = new JButton(staff ? "Save" : "Close");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);
		
		windowPanel.add(titlePanel);
		windowPanel.add(authorPanel);
		windowPanel.add(genrePanel);
		windowPanel.add(tagsPanel);
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
				Book edited = new Book (titleField.getText(), 
										authorField.getText(), 
										genreField.getText(), 
										tagsField.getText());
				if (ValidateData.validateBookEdit(b, edited)) {
					b.setTitle(titleField.getText());
					b.setAuthor(authorField.getText());
					b.setGenre(genreField.getText());
					b.setTags(tagsField.getText());
					BookData.getBookData().save();
					CatalogTableModel.getTable().updateTable();
					setVisible(false);
				}
			}
		});
	}
}
