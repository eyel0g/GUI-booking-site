import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class GetAppoinment extends JFrame{
	JButton mass;
	JButton individual;
	JButton back= new JButton("Back to Home Screen");
	 public GetAppoinment() {
		 
		 mass = new JButton("Select a Mass Activity");
		 individual= new JButton("Select an Individual Activity");
		 
	Container c = getContentPane();
	c.add(mass);
	c.add(individual);
	c.add(back);
	
	mass.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					MassActivity massactivity= new MassActivity();
					setVisible(false);
				}
			}
			);
	

	individual.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					IndividualActivity individualactivity= new IndividualActivity();
					setVisible(false);
				}
			}
			);
	
	back.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					Interface welcome= new Interface();
					setVisible(false);
				}
			}
			);
	
	pack();
	c.setLayout(new FlowLayout());
	setTitle("Get Appointment");
	setSize(250, 250);
	setResizable(false);
	c.setBackground(new Color(159, 175, 197));
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
}
