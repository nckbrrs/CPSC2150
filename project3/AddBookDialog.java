package cu.cs.cpsc2150.project2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddBookDialog extends JDialog {
	private static final long serialVersionUID = -3957955686512726289L;

	public AddBookDialog() {
		super();
		setTitle("New Book");
		setSize(300, 210);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
		
		JPanel titlePanel = new JPanel(new FlowLayout());
		JLabel titleLabel = new JLabel("Title");
		JTextField titleField = new JTextField(10);
		titlePanel.add(titleLabel);
		titlePanel.add(titleField);
		
		JPanel authorPanel = new JPanel(new FlowLayout());
		JLabel authorLabel = new JLabel("Author");
		JTextField authorField = new JTextField(10);
		authorPanel.add(authorLabel);
		authorPanel.add(authorField);
		
		JPanel genrePanel = new JPanel(new FlowLayout());
		JLabel genreLabel = new JLabel("Genre");
		JTextField genreField = new JTextField(10);
		genrePanel.add(genreLabel);
		genrePanel.add(genreField);
		
		JPanel tagsPanel = new JPanel(new FlowLayout());
		JLabel tagsLabel = new JLabel("Tags");
		JTextField tagsField = new JTextField(10);
		tagsPanel.add(tagsLabel);
		tagsPanel.add(tagsField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton saveButton = new JButton("Save");
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
				Book newBook = new Book(titleField.getText(),
										authorField.getText(),
										genreField.getText(),
										tagsField.getText());
				if(BookData.getBookData().addBook(newBook)) setVisible(false);
			}
		});
	}
}
