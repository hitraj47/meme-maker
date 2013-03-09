import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MemeMaker {
	
	private static JFrame frame;
	
	private CardLayout layout;
	
	private JPanel pnlEdit;
	private JPanel pnlSetup;
	
	public static final String SCREEN_EDIT = "Edit Screen";
	public static final String SCREEN_SETUP = "Setup Screen";
	
	public MemeMaker() {
		frame = new JFrame("Meme Maker");
		layout = new CardLayout();
		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		
		createGuiComponenets();
		frame.add(pnlEdit, SCREEN_EDIT);
		frame.add(pnlSetup, SCREEN_SETUP);
		frame.setVisible(true);
	}

	private void createGuiComponenets() {
		pnlEdit = createEditSreen();
		pnlSetup = createSetupScreen();		
	}

	private JPanel createSetupScreen() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		return panel;
	}

	private JPanel createEditSreen() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		return panel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
