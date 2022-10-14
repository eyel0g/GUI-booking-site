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

public class DeleteMass extends JFrame{

	JButton create;
	JButton back;
	JLabel label_name;
	JTextField tfield_name;
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
	
	public DeleteMass() {
		
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
						 String SQL_massactivity= "select * from mass_activity "; 
						 
							ResultSet mass_activity = read_query(SQL_massactivity);
						
							try {
								while(mass_activity.next()) {
									 if(mass_activity.getString("mass_name").equals(given_name) ) {
											 founded_id=mass_activity.getInt("mass_id");
									 }
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						 
						 try {
								Connection conn=dbConnection.connect();
								PreparedStatement pstatement= conn.prepareStatement("delete from mass_activity where mass_id=?");
								
								
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
