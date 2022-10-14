import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class ActivityCreatment extends JFrame {
	
	JButton back;
	JButton mass;
	JButton individual;
	public ActivityCreatment() {
		
		back= new JButton("back");
		mass= new JButton("Create a Mass Activity");
		individual= new JButton("Create an Individual Activity");
		
		Container c = getContentPane();
		
		c.add(mass);
		c.add(individual);
		c.add(back);
		

		mass.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						CreateMass createmass= new CreateMass();
						setVisible(false);
					}
				}
				);
		
		individual.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						CreateIndividual createind =new CreateIndividual();
						setVisible(false);
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
		setTitle("Activity Creatment");
		setSize(250, 250);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

}
