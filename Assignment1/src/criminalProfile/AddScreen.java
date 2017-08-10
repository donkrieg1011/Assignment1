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
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtNationality;
	private JTextField txtCrime;
	private JTextField txtDOB;
	
	private CriminalInfo newCriminal;
	private static CriminalConnection newConnection;

	public static void main(String[] args) {
		try {
			AddScreen dialog = new AddScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AddScreen() {
		setTitle("Add Entry");
		setBounds(100, 100, 491, 411);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblLabel = new JLabel("Enter criminal information:");
			lblLabel.setBounds(22, 28, 138, 14);
			contentPanel.add(lblLabel);
		}
		{
			JLabel lblFirstName = new JLabel("First Name:");
			lblFirstName.setBounds(22, 82, 81, 14);
			contentPanel.add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setBounds(22, 133, 81, 14);
			contentPanel.add(lblLastName);
		}
		{
			JLabel lblNationality = new JLabel("Nationality:");
			lblNationality.setBounds(22, 184, 81, 14);
			contentPanel.add(lblNationality);
		}
		{
			JLabel lblCrime = new JLabel("Crime:");
			lblCrime.setBounds(22, 236, 81, 14);
			contentPanel.add(lblCrime);
		}
		{
			JLabel lblDOB = new JLabel("Date of Birth:");
			lblDOB.setBounds(22, 290, 81, 14);
			contentPanel.add(lblDOB);
		}
		{
			txtFirstName = new JTextField();
			txtFirstName.setBounds(144, 79, 132, 20);
			contentPanel.add(txtFirstName);
			txtFirstName.setColumns(10);
		}
		{
			txtLastName = new JTextField();
			txtLastName.setBounds(144, 130, 132, 20);
			contentPanel.add(txtLastName);
			txtLastName.setColumns(10);
		}
		{
			txtNationality = new JTextField();
			txtNationality.setBounds(144, 184, 132, 20);
			contentPanel.add(txtNationality);
			txtNationality.setColumns(10);
		}
		{
			txtCrime = new JTextField();
			txtCrime.setBounds(144, 233, 132, 20);
			contentPanel.add(txtCrime);
			txtCrime.setColumns(10);
		}
		{
			txtDOB = new JTextField();
			txtDOB.setBounds(144, 287, 132, 20);
			contentPanel.add(txtDOB);
			txtDOB.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							newConnection = new CriminalConnection();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						SimpleDateFormat sdfDOB = new SimpleDateFormat("dd/MM/yyyy");
						if ( txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() ||
								txtNationality.getText().isEmpty() || txtCrime.getText().isEmpty() || txtDOB.getText().isEmpty() ) {
							System.out.println("One of the fields is empty. Please fill all fields.");
						}
						else {
							if ( checkDate(txtDOB.getText().toString()) ) {
								try {
									newCriminal = new CriminalInfo(txtLastName.getText().replaceAll("^\\s+|\\s+$", ""), txtFirstName.getText().replaceAll("^\\s+|\\s+$", ""), txtNationality.getText().replaceAll("^\\s+|\\s+$", ""), txtCrime.getText().replaceAll("^\\s+|\\s+$", ""), sdfDOB.parse(txtDOB.getText()));
								} catch (NumberFormatException | ParseException e1) {
									e1.printStackTrace();
								}
								try {
									newConnection.addEntry(newCriminal);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								System.out.println("Criminal entry added.");
							}
							else
								System.out.println("One of the field is invalid. Please try again with the required format.");
						}
					}
					}
				);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}}
		}
	
	public boolean checkDate(String input){
		Date date = null;
		try {
			SimpleDateFormat sdfDOB = new SimpleDateFormat("dd/MM/yyyy");
			date = (Date) sdfDOB.parse(input);
			if(!input.equals(sdfDOB.format(date))) {
				return false;
			}
		}catch (ParseException e){
			return false;
		}
		return true;
	}
}
