package graphics2D;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;
import javax.swing.JPanel;

public class TextOutputPanel extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;

	private static TextOutputPanel oneInstance;
	private JTextArea output;
	private InputTextField input;
	private TextOutputPanel()
	{
		JPanel outputPanel = new JPanel(new FlowLayout());
		JPanel inputPanel = new JPanel();
		setLayout(new BorderLayout());

		output = new JTextArea();
		outputPanel.add(output);
		input = new InputTextField();
		inputPanel.add(input);

		this.add(outputPanel, BorderLayout.CENTER);
		this.add(inputPanel, BorderLayout.SOUTH);
	}
	
	public static TextOutputPanel getTextOutputPanel() 
	{
		if(oneInstance == null) oneInstance = new TextOutputPanel();
		return oneInstance;
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof String) output.append((String) arg);
	}
}
