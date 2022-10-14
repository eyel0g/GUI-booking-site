import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Interface extends JFrame {
	
	JButton start=new JButton("Start Exploring!");
	JLabel welcome =new JLabel("Welcome to Our 344 Vacation Village :)");
	DBConnection dbconnection=new DBConnection();
	
	public Interface() {
		
		
		setLayout(new FlowLayout());
		Container c = getContentPane();
		c.setLayout(new FlowLayout());	
		c.add(welcome);
		c.add(start);
		
		
		start.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						dbconnection.connect();
						if(dbconnection.isStatus() == true) {
							//start.setText("Succesfully Connected");
							//start.setEnabled(false);
							Login login=new Login();
							setVisible(false);
						}
							
					}
				}
				);
		pack();
		setTitle("Welcome!");
		setSize(250, 250);
		setResizable(false);
		c.setBackground(new Color(95, 156, 114 ));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
		
		
		
		
	}
	
	
	
	

}
