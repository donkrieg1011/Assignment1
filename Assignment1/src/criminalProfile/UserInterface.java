package criminalProfile;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterface extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextArea txtDisplay;

	private CriminalInfo newCriminal;
	private static CriminalConnection newConnection;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserInterface() {
		setTitle("Criminal Profile System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearch = new JLabel("Search (Last Name):");
		lblSearch.setBounds(22, 48, 139, 14);
		contentPane.add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(171, 45, 157, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFound = false;
				txtDisplay.setText("");

				if ( txtSearch.getText().isEmpty() ) {
					txtDisplay.setText("The search field is empty. Please fill the field.");
				}
				else {
					try {
						newConnection = new CriminalConnection();
						String searchResult = newConnection.searchCriminals(txtSearch.getText().replaceAll("^\\s+|\\s+$", "")).toString();
						txtDisplay.setText(searchResult);
						if(searchResult.length() > 19){
							String criminalResult = searchResult.substring(22);
							String lastNameResult = criminalResult.split(" \n")[0];
							
							String[] criminals = searchResult.split("] ");
							if (txtSearch.getText().toLowerCase().equals(lastNameResult.toLowerCase())) {
								try {
									isFound = true;
									for(int i = 0; i < criminals.length ; i++){
										String result = criminals[i];

										txtDisplay.append((i+1) + ". " + result +"\n");
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
						else txtDisplay.setText(txtSearch.getText().replaceAll("^\\s+|\\s+$", "") + "'s entry is not found.");
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBounds(355, 44, 89, 23);
		contentPane.add(btnSearch);
		
		txtDisplay = new JTextArea();
		txtDisplay.setBounds(22, 84, 721, 266);
		contentPane.add(txtDisplay);
		txtDisplay.setColumns(10);
		
		JButton btnAdd = new JButton("Add Entry");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddScreen newAddScreen = new AddScreen();
				newAddScreen.setVisible(true);
			}
		});
		btnAdd.setBounds(490, 397, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(630, 397, 89, 23);
		contentPane.add(btnExit);
	}
}
