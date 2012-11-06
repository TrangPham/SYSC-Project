package graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Controller to listen to KDTMenu 
 * 
 * @author Group D
 * @author Main author: Karen Madore
 * 
 * Group D Members
 * ---------------
 * Karen Madore
 * Trang Pham
 * Darrell Penner
 * 
 *
 * @version 1.0
 */
public class KDTMenuController implements ActionListener {
	private JFrame f;
	
	/**
	 * Constructor for KDTMenuController
	 * @param f - handle to the frame 
	 */
	public KDTMenuController(JFrame f){
		this.f=f;
	}
	
	/**
	 * Method to respond to the menu events
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		String command = e.getActionCommand();
		
		if(command.equals("Exit")){
			JFrame optionFrame= new JFrame("");
			int confirmExit = JOptionPane.showConfirmDialog(optionFrame, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
			
			if(confirmExit==JOptionPane.YES_OPTION){
				f.dispose();
				
				System.exit(0);	
			}
			
		}
	}

}
