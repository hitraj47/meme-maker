import java.awt.CardLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MemeMaker {
	
	private static JFrame frame;
	
	private CardLayout layout;
	
	private JPanel pnlEdit;
	private JPanel pnlSetup;
	
	public static JMenuItem menuFileNew, menuFileExport, menuFileExit;
	public static JMenuItem menuHelpAbout;
	
	public static final String SCREEN_EDIT = "Edit Screen";
	public static final String SCREEN_SETUP = "Setup Screen";
	
	public MemeMaker() {
		frame = new JFrame("Meme Maker");
		layout = new CardLayout();
		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setJMenuBar(createMenuBar());
		
		createGuiComponenets();
		frame.add(pnlEdit, SCREEN_EDIT);
		frame.add(pnlSetup, SCREEN_SETUP);
		frame.setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		// Menus in menu bar
		JMenu menuFile, menuHelp;
		
		// Create file menu
		menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuFile.getAccessibleContext().setAccessibleDescription("File Menu");
		menuBar.add(menuFile);
		
		// File menu items
		menuFileNew = new JMenuItem("New Meme", KeyEvent.VK_N);
		menuFileNew.addActionListener(new MemeMakerListener());
		menuFileNew.getAccessibleContext().setAccessibleDescription("Create a new meme from an image");
		menuFile.add(menuFileNew);
		
		menuFileExport = new JMenuItem("Export/Save Meme", KeyEvent.VK_E);
		menuFileExport.addActionListener(new MemeMakerListener());
		menuFileExport.getAccessibleContext().setAccessibleDescription("Save the meme as an image");
		menuFile.add(menuFileExport);
		
		menuFileExit = new JMenuItem("Exit", KeyEvent.VK_X);
		menuFileExit.addActionListener(new MemeMakerListener());
		menuFileExit.getAccessibleContext().setAccessibleDescription("Exit the program");
		menuFile.add(menuFileExit);
		
		// create help menu
		menuHelp = new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.getAccessibleContext().setAccessibleDescription("Help Menu");
		menuBar.add(menuHelp);
		
		// help menu items
		menuHelpAbout = new JMenuItem("About", KeyEvent.VK_A);
		menuHelpAbout.addActionListener(new MemeMakerListener());
		menuHelpAbout.getAccessibleContext().setAccessibleDescription("About this program");
		menuHelp.add(menuHelpAbout);
		
		return menuBar;
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
		new MemeMaker();
	}

}
