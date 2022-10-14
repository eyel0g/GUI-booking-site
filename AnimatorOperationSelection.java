import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class AnimatorOperationSelection extends JFrame {
	
	private JButton activity_creatment;
	private JButton emergency_information;
	private JButton equipment_adding;
	private JButton delete_activity;
	JButton back= new JButton("Back to Home Screen");
	public AnimatorOperationSelection() {
		
		activity_creatment = new JButton("Activity Creatment");
		emergency_information = new JButton("Emergency Information");
		equipment_adding = new JButton("Equipment Adding");
		delete_activity= new JButton ("Delete Activity");
		
	Container c = getContentPane();
	c.add(activity_creatment);
	c.add(delete_activity);
	c.add(emergency_information);
	c.add(equipment_adding);
	c.add(back);
	activity_creatment.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					ActivityCreatment activitycreatment = new ActivityCreatment();
					setVisible(false);
				}
			}
			);
	
	delete_activity.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					DeleteActivity deleteactivity= new DeleteActivity();
					setVisible(false);
				}
			}
			);
	
	emergency_information.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					EmergencyInformation emergencyinformation = new EmergencyInformation();
					setVisible(false);
				}
			}
			);
	
	equipment_adding.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					EquipmentAdding equipmentadding = new EquipmentAdding();
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
	setTitle("Animator Operation Selection Screen");
	setSize(250,250);
	setResizable(false);
	c.setBackground(new Color(159, 175, 197));
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
