
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import javax.swing.*;
public class EmergencyInformation extends JFrame  {
		JButton add;
		JButton back;
		
		JLabel label_name;
		JLabel label_phone;
		JLabel label_locker;
		JTextField tfield_name;
		JTextField tfield_locker;
		JTextField tfield_phone;
		int int_id;
		int founded_id;
		
		DBConnection dbConnection;
		
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
		
		public EmergencyInformation() {
			
			add = new JButton("Add to System");
			back = new JButton("Back");
			label_name= new JLabel("Enter Activity Name");
			label_phone= new JLabel("Enter Phone Number");
			label_locker= new JLabel("Enter Locker Number");
			tfield_name= new JTextField(10);
			tfield_phone= new JTextField(10);
			tfield_locker= new JTextField(10);
			
			dbConnection= new DBConnection();
			
			Container c = getContentPane();
			
			
			c.add(label_name);
			c.add(tfield_name);
			c.add(label_phone);
			c.add(tfield_phone);
			c.add(label_locker);
			c.add(tfield_locker);
		
			c.add(add);
			c.add(back);

			
			
			add.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							
							 String given_name = tfield_name.getText();
							
							// String given_id= tfield_id.getText();
							 
							 String given_locker = tfield_locker.getText();
							 String given_phone = tfield_phone.getText();
							 
							 String SQL_activity= "select * from activity "; 
							 
								ResultSet activity = read_query(SQL_activity);
							
								try {
									while(activity.next()) {
										 if(activity.getString("activity_name").equals(given_name) ) {
												 founded_id=activity.getInt("activity_id");
										 }
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							 try {
									Connection conn=dbConnection.connect();
									PreparedStatement pstatement= conn.prepareStatement("insert into emergency_information (phone_no,locker_no,activity_id) values(?,?,?)");

				
									
									pstatement.setString(1, given_phone);
									pstatement.setString(2, given_locker);
									pstatement.setInt(3, founded_id);
									pstatement.executeUpdate();
									pstatement.close();
									
									JOptionPane.showMessageDialog(null, "Emergency Information Succesfully Added to the System",
							    		      "Succes", JOptionPane.INFORMATION_MESSAGE);
								}
									
								
								catch(Exception e) {
									e.printStackTrace();
								}
						}
							}
							);
							 
			back.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							
							AnimatorOperationSelection opselect=new AnimatorOperationSelection();
							setVisible(false);
						}
					}
					);
					
			
			pack();
			c.setLayout(new FlowLayout());
			setTitle("Emergency Information");
			setSize(200, 300);
			setResizable(false);
			c.setBackground(new Color(161, 224, 181));
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
					}

