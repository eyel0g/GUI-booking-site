import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddEquip extends JFrame {
	JButton add;
	JButton back;
	JLabel label_ssn;
	JLabel label_name;
	JLabel label_sname;
	JLabel label_phone;
	JTextField tfield_name;
	JTextField tfield_ssn;
	JTextField tfield_sname;
	JTextField tfield_phone;
	
	DBConnection dbConnection;
	
	public AddEquip() {
		
		//gerekli nesneler oluþturuldu
		add = new JButton("Add to System");
		back = new JButton("Back");
		label_name= new JLabel("Enter Equip Person Name");
		label_ssn= new JLabel("Enter Equip Person SSN");
		label_sname= new JLabel("Enter Equip Person Surname");
		label_phone= new JLabel("Enter Equip Person Phone");
		tfield_name= new JTextField(10);
		tfield_ssn= new JTextField(10);
		tfield_sname= new JTextField(10);
		tfield_phone= new JTextField(10);
		dbConnection= new DBConnection();
		
		Container c = getContentPane();
		
		c.add(label_ssn);
		c.add(tfield_ssn);
		c.add(label_name);
		c.add(tfield_name);
		c.add(label_sname);
		c.add(tfield_sname);
		c.add(label_phone);
		c.add(tfield_phone);
		c.add(add);
		c.add(back);
		
		add.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						 String given_name = tfield_name.getText();
						 String given_ssn = tfield_ssn.getText();
						 String given_sname = tfield_sname.getText();
						 String given_phone = tfield_phone.getText();
						 try {
								Connection conn=dbConnection.connect();
								PreparedStatement pstatement= conn.prepareStatement("insert into equip_person (ssn, equip_name,equip_sname,equip_phone) values(?,?,?,?)");

								pstatement.setString(1, given_ssn);
								pstatement.setString(2, given_name);
								pstatement.setString(3, given_sname);
								pstatement.setString(4, given_phone);
								pstatement.executeUpdate();
								pstatement.close();
								
								JOptionPane.showMessageDialog(null, "Equip Person is Succesfully Added to the System",
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
		setTitle("Mass Activity Creatment");
		setSize(200, 300);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

