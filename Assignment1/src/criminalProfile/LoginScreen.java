package criminalProfile;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtPassword;
	private LoginConnection newConnection;

	public static void main(String[] args) {
		try {
			LoginScreen dialog = new LoginScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LoginScreen() {
		setTitle("Criminal Profile System");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(23, 50, 72, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(23, 89, 72, 14);
		contentPanel.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(104, 47, 149, 20);
		contentPanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(105, 86, 148, 20);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setBounds(23, 137, 311, 28);
		contentPanel.add(lblMessage);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("Log in");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							newConnection = new LoginConnection();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
							lblMessage.setText("One of the fields is empty. Please try again");
						}
						else {
							try {
								boolean result = newConnection.searchUsers(txtUsername.getText(), txtPassword.getText());
							
								if (result == true) {
									UserInterface userInterface = new UserInterface();
									userInterface.setVisible(true);
									dispose();
								}
								else {
									lblMessage.setText("Invalid login details. Please try again.");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						
						}
				}});
				loginButton.setActionCommand("OK");
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
