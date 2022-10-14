import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame{
	private DBConnection dbConnection;
	private JLabel label_phoneNo, label_password;
	private JTextField tfield_phoneNo;
	private JPasswordField tfield_password;
	private JButton login_button;
	public static String phone= "";
	
	public ResultSet read_query(String query) {
		Statement statement;
		try {
			statement = dbConnection.dbconnect.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			return resultset; 


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		}
	
	

	public Login() {
		
		label_phoneNo = new JLabel("Enter Phone Number:");
		label_password = new JLabel("Enter Password:");
		tfield_phoneNo = new JTextField(10);
		tfield_password = new JPasswordField(10);
		login_button = new JButton("Login");
		dbConnection= new DBConnection();

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(label_phoneNo);
		c.add(tfield_phoneNo);
		c.add(label_password);
		c.add(tfield_password);
		c.add(login_button);
		
		
		login_button.addActionListener( new ActionListener() {
					 @SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent ae) {
							 String given_phone = tfield_phoneNo.getText();
							 phone = tfield_phoneNo.getText();
							 String given_password = tfield_password.getText();
							 String SQL_manager = "select * from managerloginview"; 
							 String SQL_customer = "select * from customerloginview"; 
							 String SQL_animator = "select * from animatorloginview"; 
							 
							 

								ResultSet c_phoneNo = read_query(SQL_customer);
								ResultSet a_phoneNo = read_query(SQL_animator);
								ResultSet m_phoneNo = read_query(SQL_manager);

							
								try {
									while(c_phoneNo.next()) {
											if(c_phoneNo.getString("contact_phone").equals(given_phone) 
												&& c_phoneNo.getString("password").equals(given_password)) {
											
										JOptionPane.showMessageDialog(null, "Succesfully Logged in! Directing to Get Appointment Page.....",
										    		      "Succes", JOptionPane.INFORMATION_MESSAGE);
											GetAppoinment getappointment = new GetAppoinment();
											setVisible(false);
											
										}
										
										}
									
									while(a_phoneNo.next()) {
										if(a_phoneNo.getString("phone_number").equals(given_phone) 
											&& a_phoneNo.getString("password").equals(given_password)) {
										
											
											JOptionPane.showMessageDialog(null, "Successfully Logged in! Directing to Animator Operation Selection Page....",
									    		      "Succes", JOptionPane.INFORMATION_MESSAGE);
											AnimatorOperationSelection animatormenu = new AnimatorOperationSelection();
											setVisible(false);
						
										
									}
									
									}
									
									while(m_phoneNo.next()) {
										if(m_phoneNo.getString("manager_phone").equals(given_phone) 
											&& m_phoneNo.getString("password").equals(given_password)) {
										
											
											JOptionPane.showMessageDialog(null, "Successfully Logged in! Directing to Manager Menu Page......",
									    		      "Succes", JOptionPane.INFORMATION_MESSAGE);
											ManagerMenu managermenu = new ManagerMenu();
											setVisible(false);
									
									}
									
									}
								
								} 
								catch (SQLException e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Wrong Phone Number or Password!",
										      "Error!", JOptionPane.ERROR_MESSAGE);
									Login login= new Login();
									setVisible(false);
								}
					 }
		}
					 );
							
			
			
		setLayout(new FlowLayout());
	
		setTitle("Login");
		setSize(250, 250);
		setResizable(false);
		c.setBackground(new Color(135, 191, 129 ));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}	
					 
						
	
		}

