import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddCustomer extends JFrame {
	JButton add;
	JButton back;
	
	JLabel label_name;
	JLabel label_age;
	JLabel label_room;
	JLabel label_phone;
	JTextField tfield_name;
	JTextField tfield_age;
	JTextField tfield_room;
	JTextField tfield_phone;
	int int_id;
	
	DBConnection dbConnection;
	
	public AddCustomer() {
		
		add = new JButton("Add to System");
		back = new JButton("Back");
		label_name= new JLabel("Enter Customer Name");
		label_age= new JLabel("Enter Customer Age");
		label_room= new JLabel("Enter Customer Room");
		label_phone= new JLabel("Enter Customer Phone");
		tfield_name= new JTextField(10);
		tfield_age= new JTextField(10);
		tfield_room= new JTextField(10);
		tfield_phone= new JTextField(10);
		dbConnection= new DBConnection();
		
		Container c = getContentPane();
		
		
		c.add(label_name);
		c.add(tfield_name);
		c.add(label_age);
		c.add(tfield_age);
		c.add(label_room);
		c.add(tfield_room);
		c.add(label_phone);
		c.add(tfield_phone);
		c.add(add);
		c.add(back);

		
		
		add.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						 String given_name = tfield_name.getText();
						
						// String given_id= tfield_id.getText();
						 
						 String given_age = tfield_age.getText();
						 String given_room = tfield_room.getText();
						 String given_phone = tfield_phone.getText();
						 
						 //int_id=Integer.valueOf(given_id);
						 
						//random number generator to create system password
							
						 int min = 0;
					     int max = 10000;
					        
					      int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
					      String created_password= Integer.toString(random_int);
						 
						 try {
								Connection conn=dbConnection.connect();
								PreparedStatement pstatement= conn.prepareStatement("insert into customer (customer_name,customer_age,room_number,contact_phone,password) values(?,?,?,?,?)");

			
								pstatement.setString(1, given_name);
								pstatement.setString(2, given_age);
								pstatement.setString(3, given_room);
								pstatement.setString(4, given_phone);
								pstatement.setString(5, created_password);
								pstatement.executeUpdate();
								pstatement.close();
								
								JOptionPane.showMessageDialog(null, "Customer is Succesfully Added to the System",
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
						
						ManagerMenu managermenu= new ManagerMenu();
						setVisible(false);
					}
				}
				);
				
		
		pack();
		c.setLayout(new FlowLayout());
		setTitle("Add Customer");
		setSize(200, 300);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


