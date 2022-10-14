import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
public class IndividualActivity extends JFrame{
	
	JButton create_app;
	JButton back;
	JLabel label_type;
	JLabel label_date;
	JLabel label_hour;
	JTextField tfield_type;
	JFormattedTextField formatted_date;
	JFormattedTextField formatted_hour;
	DBConnection dbConnection;
	Statement statement;
	int founded_employee_id;
	int founded_customer_id;
	int founded_activity_id;
	String customer_age;
	String age_req;
	JComboBox date, hour,name;
	String[] activity_hour=new String [20];
	String[] activity_date=new String [20];
	String[] activity_name=new String [20];
	 String given_type,given_date,given_hour;
	 String[] combo_date=new String [20];
	 String[] combo_hour=new String [20];
	 int i = 0;
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
	
	public IndividualActivity() {
		
		
		Container c = getContentPane();
		create_app= new JButton("Create Appointment");
		back=new JButton ("Back");
		label_type=new JLabel("Enter Requested Activity Type");
		label_date=new JLabel("Enter Requested Date (YY-MM-DD)");
		label_hour=new JLabel("Enter Requested Hour (HH:MM)");
		tfield_type=new JTextField(20);
		DateTimeFormatter date_format= DateTimeFormatter.ISO_DATE;
		 formatted_date = new JFormattedTextField(date_format);
		 formatted_date.setText("");
		 formatted_date.setPreferredSize( new Dimension( 200, 20 ) );

		 DateTimeFormatter hour_format= DateTimeFormatter.ISO_TIME;
		 formatted_hour = new JFormattedTextField(hour_format);
		 formatted_hour.setText("");
		 formatted_hour.setPreferredSize( new Dimension( 200, 20 ) );
		
	 dbConnection= new DBConnection();
	
	 Login login =new Login();
		
	 String SQL_indactivity = "select * from individual_activity"; 
	 ResultSet ind_activity = read_query(SQL_indactivity);
			
			
			try {
				
					
				while(ind_activity.next()) {
					   activity_date[i]=ind_activity.getString("individual_date");
						//System.out.println(activity_date[i]);
						activity_hour[i]=ind_activity.getString("individual_hour");
						//System.out.println(activity_hour[i]);
						activity_name[i]=ind_activity.getString("individual_name");
						//System.out.println(activity_name[i]);
						i++;
					
					}
				   
					name=new JComboBox(activity_name);
					date=new JComboBox(activity_date);
					hour=new JComboBox(activity_hour);
					
					c.add(label_type);
					c.add(name);
				
					
			
					 
					name.addItemListener(
							new ItemListener() {
								public void itemStateChanged(ItemEvent ie) {
									given_type = name.getSelectedItem().toString();
									 for(i=0;i<20;i++) {
										 if(activity_name[i].equals(given_type))
											 break;
											 
									 }
									 combo_date[0]= activity_date[i];
									 combo_hour[0]= activity_hour[i];
									 date.removeAllItems();
									 date.addItem(combo_date[0]);
									 hour.removeAllItems();
									 hour.addItem(combo_hour[0]);
								}
							}
							);

					c.add(label_date);
					c.add(date);
					
					c.add(label_hour);
					c.add(hour);
					
					c.add(create_app);
					c.add(back);
					given_date = date.getSelectedItem().toString();
					 given_hour = hour.getSelectedItem().toString();
					 
					 
				}
			 catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
	
		
		create_app.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						 
						 String SQL_customer= "select * from customer "; 
						 String SQL_animator = "select * from animator"; 
			
					
							ResultSet customer= read_query(SQL_customer);
							ResultSet animator= read_query(SQL_animator);
							ResultSet individual_activity = read_query(SQL_indactivity);
							
							try {
								
									while(animator.next()) {
								    if(animator.getString("expertise_area").equals(given_type) ) {
										 founded_employee_id=animator.getInt("employee_id");
								}
								   
								    
								}
									while(customer.next()) {
									if(customer.getString("contact_phone").equals(login.phone)) {
										 founded_customer_id=customer.getInt("vacation_village_id");
										  customer_age=customer.getString("customer_age");
								}
									}
									while(individual_activity.next()) {
										if(individual_activity.getString("individual_name").equals(given_type)) {
											founded_activity_id=individual_activity.getInt("individual_id");
											age_req=individual_activity.getString("age_requirement");
											//System.out.println(age_req);
											
										}
										}
									
									
							} 
								
							
								catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "The Activity that You Select is not Available",
						    		      "Error!", JOptionPane.ERROR_MESSAGE);
								
							}
							
							
							
//						
						
					try {
						Connection conn=dbConnection.connect();
						PreparedStatement pstatement= conn.prepareStatement("insert into appointment (customer_id,animator_id,activity_id,activity_date,activity_hour) values(?,?,?,?,?)");
						
						if(Integer.parseInt(age_req)<= Integer.parseInt(customer_age)) {
						pstatement.setInt(1,founded_customer_id);
						pstatement.setInt(2, founded_employee_id);
						pstatement.setInt(3, founded_activity_id);
						pstatement.setString(4, given_date);
						pstatement.setString(5, given_hour);
						pstatement.executeUpdate();
						pstatement.close();
						
						JOptionPane.showMessageDialog(null, "Appointment is Succesfully Created",
				    		      "Succes", JOptionPane.INFORMATION_MESSAGE);
						
					}
						else {
							JOptionPane.showMessageDialog(null, "Customer Age is Under the Age Requirement!",
								      "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
						
					
					catch(Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Appointment could not been Created!",
							      "Error!", JOptionPane.ERROR_MESSAGE);
					}
					}
				}
				);
		
		back.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						GetAppoinment getappoinment= new GetAppoinment();
						setVisible(false);
					}
				}
				);
		
		pack();
		c.setLayout(new FlowLayout());
		setTitle("Individual Activity ");
		setSize(250, 300);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
