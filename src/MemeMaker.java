import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import util.EditableImagePanel;

public class MemeMaker {

	/**
	 * The main window
	 */
	public static JFrame frame;

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
	public static MemeMakerEditor tabbedEditScreen;

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
	 * The instructions label when cropping an image
	 */
	public static JLabel lblCropInstructions;
	
	/**
	 * Button on the Home screen to create a new Meme.
	 */
	public static JButton btnHomeNewMeme;
	
	/**
	 * Button on the Home screen to view Instructions panel.
	 */
	public static JButton btnHomeInstructions;

	/**
	 * Button that will set the cropped image as the editor image
	 */
	public static JButton btnCrop;
	
	/**
	 * Button to resize setup image
	 */
	public static JButton btnResize;

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
	 * Action command to resize image
	 */
	public static final String ACTION_RESIZE = "Resize Image";
	
	/**
	 * Action command to resize image
	 */
	public static final String ACTION_SAVE = "Save";

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

	/**
	 * Constructor for the MemeMaker Class
	 */
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
		setFrameIcon();
		frame.add(pnlHome, SCREEN_HOME);
		frame.add(pnlInstructions, SCREEN_INSTRUCTIONS);
		frame.add(tabbedEditScreen, SCREEN_EDIT);
		frame.add(pnlSetup, SCREEN_SETUP);
		frame.setVisible(true);
	}

	/**
	 * Set up the Frame Icon image.
	 */
	private void setFrameIcon() {
		try {
			BufferedImage image = ImageIO.read(new File("res/images/logo.jpg"));
			frame.setIconImage(image);
		} catch (IOException e) {
			System.out
					.println("ERROR: Could not load logo.");
		}
	}
	
	/**
	 * Creates the MenuBar
	 * @return
	 */
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
		//menuFileExportJPG.setActionCommand(ACTION_SAVE);
		menuFileExportJPG.addActionListener(new MemeMakerListener());
		menuFileExport.add(menuFileExportJPG);

		menuFileExportPNG = new JMenuItem("Export as PNG", KeyEvent.VK_P);
		menuFileExportPNG.getAccessibleContext().setAccessibleDescription(
				"Save the image as a .png image");
		//menuFileExportPNG.setActionCommand(ACTION_SAVE);
		menuFileExportPNG.addActionListener(new MemeMakerListener());
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

	/**
	 * Creates the GUI Components 
	 */
	private void createGuiComponenets() {
		pnlHome = createHomeScreen();
		pnlInstructions = createInstructionsSreen();
		tabbedEditScreen = new MemeMakerEditor();
		pnlSetup = new JPanel(new BorderLayout());
	}

	/**
	 * Creates the HomeScreen
	 * @return
	 */
	private JPanel createHomeScreen(){
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBackground(Color.WHITE);
		
		// Create logo
		JLabel lblLogo = new JLabel(new ImageIcon("res/images/logo.jpg"));
		lblLogo.setBounds(25, 10, 610,610);
		
		// Create welcome
		JLabel welcome = new JLabel("Welcome");
		welcome.setFont(new Font("Arial", Font.PLAIN, 64));
		welcome.setForeground(Color.BLACK);
		welcome.setBounds(800,150, 300, 100);
		
		// Create Separator
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBackground(Color.BLACK);
		separator.setBounds(635, 100, 10, 450);
		
		// Create NewMeme Button
		btnHomeNewMeme = new JButton("New Meme");
		btnHomeNewMeme.setBackground(Color.BLACK);
		btnHomeNewMeme.setForeground(Color.WHITE);
		btnHomeNewMeme.setFocusPainted(false);
		btnHomeNewMeme.setBorderPainted(false);
		btnHomeNewMeme.setFont(new Font("Arial", Font.PLAIN, 32));
		btnHomeNewMeme.setBounds(830, 275, 225, 35);
		btnHomeNewMeme.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnHomeNewMeme.setBackground(Color.BLACK);	
			}	
			@Override
			public void mouseExited(MouseEvent e) {
				btnHomeNewMeme.setBackground(Color.BLACK);	
			}	
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHomeNewMeme.setBackground(Color.RED);
			}
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
			@Override
			public void mouseReleased(MouseEvent e) {	
			}

		});
		btnHomeNewMeme.addActionListener(new MemeMakerListener());
		
		// Create Instructions Button
		btnHomeInstructions = new JButton("Instructions");
		btnHomeInstructions.setBackground(Color.BLACK);
		btnHomeInstructions.setForeground(Color.WHITE);
		btnHomeInstructions.setFocusPainted(false);
		btnHomeInstructions.setBorderPainted(false);
		btnHomeInstructions.setFont(new Font("Arial", Font.PLAIN, 32));
		btnHomeInstructions.setBounds(830, 325, 225, 35);
		btnHomeInstructions.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				btnHomeInstructions.setBackground(Color.BLACK);	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnHomeInstructions.setBackground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHomeInstructions.setBackground(Color.RED);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnHomeInstructions.addActionListener(new MemeMakerListener());
		
		// Add components to panel
		homePanel.add(lblLogo);
		homePanel.add(welcome);
		homePanel.add(separator);
		homePanel.add(btnHomeNewMeme);
		homePanel.add(btnHomeInstructions);
		return homePanel;
	}
	
	/**
	 * Creates the Instructions Screen
	 * @return
	 */
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
		styleSheet
				.addRule("h1 { text-align: center; font-family: Arial, sans-serif; }");

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

	/**
	 * Shows the setup Screen 
	 * @param inputImage
	 */
	public static void showSetupScreen(BufferedImage inputImage) {
		setupImageContainer = new EditableImagePanel(inputImage);
		JScrollPane scrollPane = new JScrollPane(setupImageContainer);
		pnlSetup.add(scrollPane, BorderLayout.CENTER);
		setupImageContainer.setEditingMode(EditableImagePanel.MODE_CROP);
		setupImageContainer.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				BufferedImage croppedImage = setupImageContainer
						.getCroppedImage();
				int width = 0, height = 0;
				if (croppedImage != null) {
					width = croppedImage.getWidth();
					height = croppedImage.getHeight();
					if (meetsMaxImageSizeRequirements(croppedImage)
							&& meetsMinImageSizeRequirements(croppedImage)) {
						MemeMaker.btnCrop.setEnabled(true);
					} else {
						MemeMaker.btnCrop.setEnabled(false);
					}
				}
				MemeMaker.lblCropInstructions.setText("New width and height: "
						+ width + "x" + height);

			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
		});

		JPanel pnlCrop = new JPanel();
		pnlSetup.add(pnlCrop, BorderLayout.SOUTH);

		String cropInstructions = "Drag a box on the image to select an area to crop, then press the crop button. ";
		cropInstructions += "New width and height of cropped image must be between 400 and 600 pixels inclusive.";
		lblCropInstructions = new JLabel(cropInstructions);
		pnlCrop.add(lblCropInstructions);

		btnCrop = new JButton(ACTION_CROP);
		btnCrop.setEnabled(false);
		btnCrop.addActionListener(new MemeMakerListener());
		pnlCrop.add(btnCrop);
		
		btnResize = new JButton(ACTION_RESIZE);
		btnResize.addActionListener(new MemeMakerListener());
		pnlCrop.add(btnResize);

		layout.show(frame.getContentPane(), SCREEN_SETUP);
	}

	/**
	 * Shows the EditScreen
	 * @param image
	 */
	public static void showEditScreen(BufferedImage image) {
		String title = "Meme ";
		
		for (int i=1;i<=3;i++) {
			tabbedEditScreen.createTab(title + i, image);
			
		}
		layout.show(frame.getContentPane(), SCREEN_EDIT);
	}

	/**
	 * Shows the Instruction Screen
	 */
	public static void showInstructionsScreen() {
		layout.show(frame.getContentPane(), SCREEN_INSTRUCTIONS);
	}

	/**
	 * Checks if the image opened by the User meets Max Requirements
	 */
	public static boolean meetsMaxImageSizeRequirements(BufferedImage image) {
		return (image.getWidth() <= INPUT_IMAGE_MAX_WIDTH)
				&& (image.getHeight() <= INPUT_IMAGE_MAX_HEIGHT);
	}

	/**
	 * Checks if the image opened by the User meets Min Requirements
	 */
	public static boolean meetsMinImageSizeRequirements(BufferedImage image) {
		return (image.getWidth() >= INPUT_IMAGE_MIN_WIDTH)
				&& (image.getHeight() >= INPUT_IMAGE_MIN_HEIGHT);
	}
	
	/**
	 * Returns the currently selected EditorTab
	 * @return EditorTab
	 */
	public static MemeMakerEditor.EditorTab getSelectedEditorTab(){
		int index = MemeMaker.tabbedEditScreen.getTabbedPane().getSelectedIndex();
		MemeMakerEditor.EditorTab editorTab = MemeMaker.tabbedEditScreen.getEditorTabs().get(index);
		return editorTab;
	}
	
	/**
	 * Returns the MemeMakerConfiguration Panel in the currently selected EditorTab
	 * @return MemeMakerConfiguration Panel
	 */
	public static MemeMakerConfiguration getSelectedEditorTabCofigPanel(){
		MemeMakerConfiguration config = MemeMaker.getSelectedEditorTab().getConfigPanel();
		return config;
	}
	
	/**
	 * Returns the EditableImagePanel in the currently selected EditorTab
	 * @return EditableImagePanel
	 */
	public static EditableImagePanel getSelectedEditorTabImagePanel(){
		EditableImagePanel imagePanel = MemeMaker.getSelectedEditorTab().getImagePanel();
		return imagePanel;
	}
	
	/**
	 * Show a popup of an image. Returns true if the user wants to keep it
	 * @param image The image to be previewed
	 * @return true if the user wants to keep
	 */
	public static boolean showImagePreviewConfirmDialog(BufferedImage image) {
		EditableImagePanel resizedImagePanel = new EditableImagePanel(image);
		Object[] confirmResizeButtons = { "Keep", "Discard" };
		int confirm = JOptionPane.showOptionDialog(null,
				resizedImagePanel, "Image Preview",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, confirmResizeButtons,
				confirmResizeButtons[0]);
		return (confirm==0) ? true : false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MemeMaker();
	}

}
