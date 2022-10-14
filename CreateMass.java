import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class CreateMass extends JFrame{

	JButton create;
	JButton back;
	JLabel label_name;
	JLabel label_link;
	JLabel label_capacity;
	JTextField tfield_name;
	JTextField tfield_link;
	JTextField tfield_capacity;
	JLabel label_date;
	JLabel label_hour;
	public static String capacity= "";
	DBConnection dbConnection;
	JFormattedTextField formatted_date;
	JFormattedTextField formatted_hour;
	 String given_date,given_hour;
	public CreateMass() {
		
		create = new JButton("Create Activity");
		back=new JButton ("Back");
		label_name= new JLabel("Enter Activity Name");
		label_link= new JLabel("Enter Activity's Internet Link");
		label_date= new JLabel("Enter Activity's Date (DD-MM-YY)");
		label_hour= new JLabel("Enter Activity's Hour (HH:MM)");
		label_capacity= new JLabel("Enter Maximum Capacity");
		tfield_name= new JTextField(15);
		tfield_link= new JTextField(20);
		tfield_capacity= new JTextField(15);
		dbConnection= new DBConnection();
		DateTimeFormatter date_format= DateTimeFormatter.ISO_DATE;
		 formatted_date = new JFormattedTextField(date_format);
		 formatted_date.setText("");
	     formatted_date.setPreferredSize( new Dimension( 200, 20 ) );

		 DateTimeFormatter hour_format= DateTimeFormatter.ISO_TIME;
		 formatted_hour = new JFormattedTextField(hour_format);
	     formatted_hour.setText("");
		 formatted_hour.setPreferredSize( new Dimension( 200, 20 ) );
		
		Container c = getContentPane();
		c.add(label_name);
		c.add(tfield_name);
		c.add(label_link);
		c.add(tfield_link);
		c.add(label_capacity);
		c.add(tfield_capacity);
		c.add(label_date);
		c.add(formatted_date);
		c.add(label_hour);
		c.add(formatted_hour);
		c.add(create);
		c.add(back);
		
		create.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						 String given_name = tfield_name.getText();
						 String given_link = tfield_link.getText();
						 String given_capacity = tfield_capacity.getText();
						 String given_date = formatted_date.getText();
						 String given_hour = formatted_hour.getText();
						 capacity = tfield_capacity.getText();
						 
						 try {
								Connection conn=dbConnection.connect();
								PreparedStatement pstatement= conn.prepareStatement("insert into mass_activity (mass_id,mass_name,mass_link,capacity,mass_date,mass_hour) values(nextval('mass'),?,?,?,?,?)");
								
								
								pstatement.setString(1, given_name);
								pstatement.setString(2, given_link);
								pstatement.setString(3, given_capacity);
								pstatement.setString(4, given_date);
								pstatement.setString(5, given_hour);
								pstatement.executeUpdate();
								pstatement.close();
								
								JOptionPane.showMessageDialog(null, "Activity is Succesfully Created",
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
						
						AnimatorOperationSelection animatormenu = new AnimatorOperationSelection();
						setVisible(false);
					}
				}
				);
				
		
		pack();
		c.setLayout(new FlowLayout());
		setTitle("Mass Activity Creatment");
		setSize(250, 300);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
