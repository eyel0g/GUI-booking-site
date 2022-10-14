import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
public class EquipmentAdding extends JFrame  {
	JButton back;
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
	public EquipmentAdding() {
		JButton add;
		JLabel label_name;
		JLabel label_purpose;
		JLabel label_ssn;
		JTextField tfield_name;
		JTextField tfield_purpose;
		JTextField tfield_ssn;
		
		 dbConnection= new DBConnection();
			
			add = new JButton("Add Equipment");
			label_name= new JLabel("Enter Equipment Name");
			label_purpose= new JLabel("Enter Equipment's Purpose");
			label_ssn= new JLabel("Enter Equip Person's SSN");
			tfield_name= new JTextField(10);
			tfield_purpose= new JTextField(10);
			tfield_ssn= new JTextField(10);
			back= new JButton("back");
			
			
			Container c = getContentPane();
			c.add(label_name);
			c.add(tfield_name);
			c.add(label_purpose);
			c.add(tfield_purpose);
			c.add(label_ssn);
			c.add(tfield_ssn);
			c.add(add);
			c.add(back);
			

			back.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							
							AnimatorOperationSelection animatormenu = new AnimatorOperationSelection();
							setVisible(false);
						}
					}
					);
			
			add.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							
							 String given_name = tfield_name.getText();
							 String given_purpose = tfield_purpose.getText();
							 String given_ssn = tfield_ssn.getText();
							 
							 String SQL_equip = "select * from equip_person"; 
								
								ResultSet equip= read_query(SQL_equip);
							 
							 try {
									Connection conn=dbConnection.connect();
									PreparedStatement pstatement= conn.prepareStatement("insert into equipment (equipment_name,purpose,equip_person_ssn) values(?,?,?)");
									
									pstatement.setString(1, given_name);
									pstatement.setString(2, given_purpose);
									pstatement.setString(3, given_ssn);
									pstatement.executeUpdate();
									pstatement.close();
								
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
			setTitle("Equipment Adding");
			setSize(250, 300);
			setResizable(false);
			c.setBackground(new Color(161, 224, 181));
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
