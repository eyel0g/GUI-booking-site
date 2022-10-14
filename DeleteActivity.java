import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DeleteActivity extends JFrame {
	
	JButton back;
	JButton mass;
	JButton individual;
	public DeleteActivity() {
		
		back= new JButton("back");
		mass= new JButton("Delete a Mass Activity");
		individual= new JButton("Delete an Individual Activity");
		
		Container c = getContentPane();
		
		c.add(mass);
		c.add(individual);
		c.add(back);
		

		mass.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						DeleteMass deletemass= new DeleteMass();
						setVisible(false);
					}
				}
				);
		
		individual.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						DeleteIndividual deleteind =new DeleteIndividual();
						setVisible(false);
					}
				}
				);
		
		back.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						AnimatorOperationSelection animator= new AnimatorOperationSelection();
						setVisible(false);
					}
				}
				);
		
		pack();
		c.setLayout(new FlowLayout());
		setTitle("Delete Activity");
		setSize(250, 250);
		setResizable(false);
		c.setBackground(new Color(161, 224, 181));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

}
