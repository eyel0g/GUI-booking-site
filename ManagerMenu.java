import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class ManagerMenu extends JFrame {
	private JButton add_customer;
	private JButton add_animator;
	private JButton add_equip;
	JButton back= new JButton("Back to Home Screen");
	
	public ManagerMenu() {
		
		add_customer = new JButton("Add Customer");
		add_animator = new JButton("Add Animator");
		add_equip = new JButton("Add Equip Person");
		
	Container c = getContentPane();
	c.add(add_customer);
	c.add(add_animator);
	c.add(add_equip);
	c.add(back);
	
	add_customer.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					AddCustomer addcustomer = new AddCustomer();
					setVisible(false);
				}
			}
			);
	
	add_animator.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					AddAnimator addanimator = new AddAnimator();
					setVisible(false);
				}
			}
			);
	
	add_equip.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					AddEquip addequip = new AddEquip();
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
	
	
	c.setLayout(new FlowLayout());
	setTitle("Animator Operation Selection Screen");
	setSize(250, 250);
	setResizable(false);
	c.setBackground(new Color(159, 175, 197));
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
