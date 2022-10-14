import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.*;

public class DeleteIndividual extends JFrame{

	JButton create;
	JButton back;
	JLabel label_name;
	JTextField tfield_name;
	int founded_id;
	
	public static String capacity= "";
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
	
	public DeleteIndividual() {
		
		create = new JButton("Delete Activity");
		back=new JButton ("Back");
		label_name= new JLabel("Enter Activity Name");

		tfield_name= new JTextField(15);
		
		dbConnection= new DBConnection();
		
		
		Container c = getContentPane();
		c.add(label_name);
		c.add(tfield_name);
		
		c.add(create);
		c.add(back);
		
		create.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						 String given_name = tfield_name.getText();
						 String SQL_individualactivity= "select * from individual_activity "; 
						 
							ResultSet individual_activity = read_query(SQL_individualactivity);
						
							try {
								while(individual_activity.next()) {
									 if(individual_activity.getString("individual_name").equals(given_name) ) {
											 founded_id=individual_activity.getInt("individual_id");
									 }
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						 
						 try {
								Connection conn=dbConnection.connect();
								PreparedStatement pstatement= conn.prepareStatement("delete from individual_activity where individual_id=?");
								
								
								pstatement.setInt(1, founded_id);
								pstatement.executeUpdate();
								pstatement.close();
								
								JOptionPane.showMessageDialog(null, "Activity is Succesfully Deleted",
						    		      "Succes", JOptionPane.INFORMATION_MESSAGE);
							}
								
							
							catch(Exception e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Activity is not Found!",
									      "Error!", JOptionPane.ERROR_MESSAGE);
							}
					}
						}
						);
						 
		back.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						DeleteActivity delete= new DeleteActivity();
						setVisible(false);
					}
				}
				);
				
		
		pack();
		c.setLayout(new FlowLayout());
		setTitle("Delete Mass Activity");
		setSize(250, 300);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
