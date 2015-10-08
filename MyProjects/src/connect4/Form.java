package connect4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Form {

	private JFrame frame;
	private Engine engine;

	public JFrame getFrame(){
		return frame;
	}
	
	/**
	 * Create the application.
	 */
	public Form(Engine engine) {
		this.engine = engine;
		engine.clear();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 355, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = (JPanel) new PaintPanel(engine);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
}
