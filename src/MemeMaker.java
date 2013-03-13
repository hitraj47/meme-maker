import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import util.EditableImagePanel;

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
	 * A welcome/home screen
	 */
	private JPanel pnlHome;

	/**
	 * The panel that holds the tabbed editor
	 */
	private JPanel pnlEditTabs;

	/**
	 * The instructions screen
	 */
	private JPanel pnlInstructions;

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
	 * Menu item to show instructions
	 */
	public static JMenuItem menuHelpInstructions;

	/**
	 * About the program menu item
	 */
	public static JMenuItem menuHelpAbout;

	/**
	 * The image panel that contains the image that needs to be cropped/resized
	 */
	public static EditableImagePanel setupImageContainer;

	/**
	 * String constant for home/welcome screen
	 */
	public static final String SCREEN_HOME = "Home Screen";

	/**
	 * String constant for instructions screen
	 */
	public static final String SCREEN_INSTRUCTIONS = "Instructions screen";

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
	 * Action command for instructions screen
	 */
	public static final String ACTION_INSTRUCTIONS = "Instructions";

	/**
	 * Action command for displaying information about the program
	 */
	public static final String ACTION_ABOUT = "About";
	
	/**
	 * Action command to crop image
	 */
	public static final String ACTION_CROP = "Crop";

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

	/**
	 * The Program title/name
	 */
	public static final String APPLICATION_TITLE = "Meme Maker 9000";

	public MemeMaker() {
		frame = new JFrame(APPLICATION_TITLE);

		layout = new CardLayout();
		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setJMenuBar(createMenuBar());

		createGuiComponenets();
		frame.add(pnlHome, SCREEN_HOME);
		frame.add(pnlInstructions, SCREEN_INSTRUCTIONS);
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
		menuFileNew.getAccessibleContext().setAccessibleDescription(
				"Create a new meme from an image");
		menuFile.add(menuFileNew);

		JMenu menuFileExport = new JMenu("Export/Save Meme");
		menuFileExport.setMnemonic(KeyEvent.VK_E);
		menuFileExport.getAccessibleContext().setAccessibleDescription(
				"Save the meme as an image");
		menuFile.add(menuFileExport);

		menuFileExportJPG = new JMenuItem("Export as JPEG", KeyEvent.VK_J);
		menuFileExportJPG.getAccessibleContext().setAccessibleDescription(
				"Save the meme as a .jpg image");
		menuFileExport.add(menuFileExportJPG);

		menuFileExportPNG = new JMenuItem("Export as PNG", KeyEvent.VK_P);
		menuFileExportPNG.getAccessibleContext().setAccessibleDescription(
				"Save the image as a .png image");
		menuFileExport.add(menuFileExportPNG);

		menuFileExit = new JMenuItem(ACTION_EXIT, KeyEvent.VK_X);
		menuFileExit.addActionListener(new MemeMakerListener());
		menuFileExit.getAccessibleContext().setAccessibleDescription(
				"Exit the program");
		menuFile.add(menuFileExit);

		// create help menu
		menuHelp = new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.getAccessibleContext().setAccessibleDescription("Help Menu");
		menuBar.add(menuHelp);

		// help menu items
		menuHelpInstructions = new JMenuItem(ACTION_INSTRUCTIONS, KeyEvent.VK_I);
		menuHelpInstructions.addActionListener(new MemeMakerListener());
		menuHelpInstructions.getAccessibleContext().setAccessibleDescription(
				"Instructions on how to use the program");
		menuHelp.add(menuHelpInstructions);

		menuHelpAbout = new JMenuItem(ACTION_ABOUT, KeyEvent.VK_A);
		menuHelpAbout.addActionListener(new MemeMakerListener());
		menuHelpAbout.getAccessibleContext().setAccessibleDescription(
				"About this program");
		menuHelp.add(menuHelpAbout);

		return menuBar;
	}

	private void createGuiComponenets() {
		pnlHome = new JPanel(new BorderLayout());
		pnlInstructions = createInstructionsSreen();
		pnlEditTabs = new MemeMakerEditor();
		pnlSetup = new JPanel(new BorderLayout());
		//pnlSetup.setLayout(null);
	}

	private JPanel createInstructionsSreen() {
		JPanel panel = new JPanel(new BorderLayout());

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		HTMLEditorKit kit = new HTMLEditorKit();
		editorPane.setEditorKit(kit);
		JScrollPane scrollPane = new JScrollPane(editorPane);
		panel.add(scrollPane, BorderLayout.CENTER);

		// add some styles to the html
		StyleSheet styleSheet = kit.getStyleSheet();
		styleSheet
				.addRule("body { background-color: #ffffff; color: #000000; font-family: Verdana, sans-serif; }");
		styleSheet.addRule("h1 { text-align: center; font-family: Arial, sans-serif; }");

		// create a document, set it on the jeditorpane, then add the html
		javax.swing.text.Document doc = kit.createDefaultDocument();
		editorPane.setDocument(doc);
		String html = "";
		try {
			Scanner s = new Scanner(new File("docs/instructions.html"));
			while (s.hasNextLine()) {
				html += s.nextLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not open file instructions file.");
		}
		editorPane.setText(html);

		return panel;
	}

	public static void showSetupScreen(BufferedImage inputImage) {
		setupImageContainer = new EditableImagePanel(inputImage);
		JScrollPane scrollPane = new JScrollPane(setupImageContainer);
		pnlSetup.add(scrollPane, BorderLayout.CENTER);
		setupImageContainer.addMouseListener(new MemeMakerListener());
		setupImageContainer.addMouseMotionListener(new MemeMakerListener());
		
		JPanel pnlCrop = new JPanel();
		pnlSetup.add(pnlCrop, BorderLayout.SOUTH);
		JLabel lblCropInstructions = new JLabel("Drag a box on the image to select an area to crop, then press the crop button.");
		pnlCrop.add(lblCropInstructions);
		JButton btnCrop = new JButton(ACTION_CROP);
		pnlCrop.add(btnCrop);
		
		layout.show(frame.getContentPane(), SCREEN_SETUP);
	}

	public static void showEditScreen(BufferedImage inputImage) {
		// TODO Auto-generated method stub

	}

	public static void showInstructionsScreen() {
		layout.show(frame.getContentPane(), SCREEN_INSTRUCTIONS);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MemeMaker();
	}

}
