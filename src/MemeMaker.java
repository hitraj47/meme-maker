import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import util.ImagePanel;

public class MemeMaker {
	
	/**
	 * The main window
	 */
	private static JFrame frame;
	
	/**
	 * The program layout
	 */
	private static CardLayout layout;
	
	/**
	 * The panel that holds the tabbed editor
	 */
	private JPanel pnlEditTabs;
	
	/**
	 * The image setup panel
	 */
	private static JPanel pnlSetup;
	
	/**
	 * New file menu item
	 */
	public static JMenuItem menuFileNew;
	
	/**
	 * Export as jpeg menu item
	 */
	public static JMenuItem menuFileExportJPG;
	
	/**
	 * Export as png menu item
	 */
	public static JMenuItem menuFileExportPNG; 
	
	/**
	 * Exit the program
	 */
	public static JMenuItem menuFileExit;
	
	/**
	 * About the program menu item
	 */
	public static JMenuItem menuHelpAbout;
	
	/**
	 * String constant for editor screen
	 */
	public static final String SCREEN_EDIT = "Edit Tabs Screen";
	
	/**
	 * String constant for image setup screen
	 */
	public static final String SCREEN_SETUP = "Setup Screen";
	
	/**
	 * Action command to exit the application
	 */
	public static final String ACTION_EXIT = "Exit";
	
	/**
	 * Action command for creating a new meme
	 */
	public static final String ACTION_NEW = "New Meme";
	
	/**
	 * Action command for displaying information about the program
	 */
	public static final String ACTION_ABOUT = "About";
	
	/**
	 * Minimum input image width
	 */
	public static final int INPUT_IMAGE_MIN_WIDTH = 400;
	
	/**
	 * Maximum input image width
	 */
	public static final int INPUT_IMAGE_MAX_WIDTH = 600;
	
	/**
	 * Minimum input image height
	 */
	public static final int INPUT_IMAGE_MIN_HEIGHT = 400;
	
	/**
	 * Maximum input image height
	 */
	public static final int INPUT_IMAGE_MAX_HEIGHT = 600;
	
	/**
	 * Window width
	 */
	public static final int WINDOW_WIDTH = 1280;
	
	/**
	 * Window height
	 */
	public static final int WINDOW_HEIGHT = 700;
	
	public MemeMaker() {
		frame = new JFrame("Meme Maker");
		
		layout = new CardLayout();
		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setJMenuBar(createMenuBar());
		
		createGuiComponenets();
		frame.add(pnlEditTabs, SCREEN_EDIT);
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
		menuFile.addActionListener(new MemeMakerListener());
		menuBar.add(menuFile);
		
		// File menu items
		menuFileNew = new JMenuItem("New Meme", KeyEvent.VK_N);
		menuFileNew.addActionListener(new MemeMakerListener());
		menuFileNew.getAccessibleContext().setAccessibleDescription("Create a new meme from an image");
		menuFile.add(menuFileNew);
		
		JMenu menuFileExport = new JMenu("Export/Save Meme");
		menuFileExport.setMnemonic(KeyEvent.VK_E);
		menuFileExport.getAccessibleContext().setAccessibleDescription("Save the meme as an image");
		menuFile.add(menuFileExport);
		
		menuFileExportJPG = new JMenuItem("Export as JPEG", KeyEvent.VK_J);
		menuFileExportJPG.getAccessibleContext().setAccessibleDescription("Save the meme as a .jpg image");
		menuFileExport.add(menuFileExportJPG);
		
		menuFileExportPNG = new JMenuItem("Export as PNG", KeyEvent.VK_P);
		menuFileExportPNG.getAccessibleContext().setAccessibleDescription("Save the image as a .png image");
		menuFileExport.add(menuFileExportPNG);
		
		menuFileExit = new JMenuItem(ACTION_EXIT, KeyEvent.VK_X);
		menuFileExit.addActionListener(new MemeMakerListener());
		menuFileExit.getAccessibleContext().setAccessibleDescription("Exit the program");
		menuFile.add(menuFileExit);
		
		// create help menu
		menuHelp = new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.getAccessibleContext().setAccessibleDescription("Help Menu");
		menuBar.add(menuHelp);
		
		// help menu items
		menuHelpAbout = new JMenuItem(ACTION_ABOUT, KeyEvent.VK_A);
		menuHelpAbout.addActionListener(new MemeMakerListener());
		menuHelpAbout.getAccessibleContext().setAccessibleDescription("About this program");
		menuHelp.add(menuHelpAbout);
		
		return menuBar;
	}

	private void createGuiComponenets() {
		pnlEditTabs = new MemeMakerEditor();
		pnlSetup = new JPanel();
		pnlSetup.setLayout(null);
	}

	public static void showSetupScreen(BufferedImage inputImage) {
		ImagePanel imageContainer = new ImagePanel(inputImage);
		pnlSetup.add(imageContainer);
		int x = getCenteredXPosition(inputImage.getWidth());
		int y = getCenteredYPosition(inputImage.getHeight());
		imageContainer.setBounds(x,y, inputImage.getWidth(), inputImage.getHeight());
		layout.show(frame.getContentPane(), SCREEN_SETUP);
	}

	/**
	 * Get the y position that would vertically align with center
	 * Good for absolute layout positioning
	 * @param height The height of the thing you want to center
	 * @return the y coordinate position
	 */
	private static int getCenteredYPosition(int height) {
		return (WINDOW_HEIGHT/2) - (height/2);
	}

	/**
	 * Get the x position that would horizontally align with center
	 * Good for absolute layout positioning
	 * @param width The width of the thing you want to center
	 * @return the x coordinate position
	 */
	private static int getCenteredXPosition(int width) {
		return (WINDOW_WIDTH/2) - (width/2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MemeMaker();
	}

}
