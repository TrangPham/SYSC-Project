package graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class InputTextField extends JTextField implements KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputTextField()
	{
		addKeyListener(this);
	}

	@Override 
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() != KeyEvent.VK_ENTER) return;
		//get the text in the text field
		setActionCommand(this.getText());	
		//command event happens
		fireActionPerformed();	
		//clear the text
		this.setText("");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
