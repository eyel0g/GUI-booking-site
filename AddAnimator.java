import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddAnimator extends JFrame {
	JButton add;
	JButton back;
	JLabel label_name;
	JLabel label_area;
	JLabel label_phone;
	JTextField tfield_name;
	JTextField tfield_age;
	JTextField tfield_area;
	JTextField tfield_phone;
	int int_id;
	DBConnection dbConnection;
	
	public AddAnimator() {
		
		add = new JButton("Add to System");
		back = new JButton("Back");
		label_name= new JLabel("Enter Animator Name");
		
		label_area= new JLabel("Enter Expertise Area");
		label_phone= new JLabel("Enter Animator Phone");
		tfield_name= new JTextField(10);
		
		tfield_area= new JTextField(10);
		tfield_phone= new JTextField(10);
		dbConnection= new DBConnection();
		
		Container c = getContentPane();
		
		
		c.add(label_name);
		c.add(tfield_name);
		c.add(label_phone);
		c.add(tfield_phone);
		c.add(label_area);
		c.add(tfield_area);
				c.add(add);
				c.add(back);
				
	
		
		add.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						 String given_name = tfield_name.getText();
						
						 String given_area = tfield_area.getText();
						 String given_phone = tfield_phone.getText();
						 
						
						 
						//random number generator to create system password
							
						 int min = 0;
					     int max = 10000;
					        
					      int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
					      String created_password= Integer.toString(random_int);
							
						 
						 try {
								Connection conn=dbConnection.connect();
								PreparedStatement pstatement= conn.prepareStatement("insert into animator (animator_name,phone_number,expertise_area,password) values(?,?,?,?)");

								
								pstatement.setString(1, given_name);
								pstatement.setString(2, given_phone);
								pstatement.setString(3, given_area);
								pstatement.setString(4, created_password);
								pstatement.executeUpdate();
								pstatement.close();
								
								JOptionPane.showMessageDialog(null, "Animator is Succesfully Added to the System",
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
		setTitle("Add Animator");
		setSize(200, 300);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

