import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class MemeMaker {
	
	private static JFrame frame;
	
	private CardLayout layout;
	
	private JPanel pnlEdit;
	private JPanel pnlSetup;
	
	public static JMenuItem menuFileNew, menuFileExport, menuFileExit;
	public static JMenuItem menuHelpAbout;
	
	private JTextArea textAreaMessage1Top, textAreaMessage1Bottom, textAreaMessage2Top, textAreaMessage2Bottom, textAreaMessage3Top, textAreaMessage3Bottom;
	
	private JCheckBox chkMessage1Show, chkMessage2Show, chkMessage3Show;
	
	private JComboBox<String> comboMessage1Font, comboMessage2Font, comboMessage3Font;
	
	private JColorChooser message1ColorChooser, message2ColorChooser, message3ColorChooser;
	
	private JComboBox<String> comboMessage1Size, comboMessage2Size, comboMessage3Size;
	
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
		panel.add(createToolBoxPanel());
		panel.add(createImageEditPanel());
		return panel;
	}

	private Component createImageEditPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		return panel;
	}

	private Component createToolBoxPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.setBorder(BorderFactory.createTitledBorder("Tools"));
		panel.add(createMessageTextBox("Message 1", 1, true));
		panel.add(createMessageTextBox("Message 2", 2, true));
		panel.add(createMessageTextBox("Message 3", 3, false));
		return panel;
	}

	private JPanel createMessageTextBox(String sectionTitle, int message, boolean showByDefault) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel lblTop = new JLabel("Top message:");
		panel.add(lblTop);
		
		JLabel lblBottom = new JLabel("Bottom message:");
		panel.add(lblBottom);
		
		JLabel lblFont = new JLabel("Font:");
		panel.add(lblFont);
		
		JLabel lblColor = new JLabel("Color:");
		panel.add(lblColor);
		
		JLabel lblSize = new JLabel("Size:");
		panel.add(lblSize);
		
		String[] fonts = { "Arial", "Times New Roman", "Comic Sans" };
		String[] sizes = { "10pt", "11pt", "12pt", "13pt", "14pt", "15pt" };
		
		if (message == 1) {
			textAreaMessage1Top = new JTextArea();
			panel.add(textAreaMessage1Top);
			
			textAreaMessage1Bottom = new JTextArea();
			panel.add(textAreaMessage1Bottom);
			
			chkMessage1Show = new JCheckBox("Show/Hide");
			if (showByDefault) {
				chkMessage1Show.setSelected(true);
			}
			panel.add(chkMessage1Show);
			
			panel.setBorder(BorderFactory.createTitledBorder("Message 1"));
			
			comboMessage1Font = new JComboBox<>(fonts);
			panel.add(comboMessage1Font);
			
			message1ColorChooser = new JColorChooser();
			panel.add(message1ColorChooser);
			
			comboMessage1Size = new JComboBox<>(sizes);
			panel.add(comboMessage1Size);
		} else if(message == 2) {
			textAreaMessage2Top = new JTextArea();
			panel.add(textAreaMessage2Top);
			
			textAreaMessage2Bottom = new JTextArea();
			panel.add(textAreaMessage2Bottom);
			
			chkMessage2Show = new JCheckBox("Show/Hide");
			if (showByDefault) {
				chkMessage2Show.setSelected(true);
			}
			panel.add(chkMessage2Show);
			
			panel.setBorder(BorderFactory.createTitledBorder("Message 2"));
			
			comboMessage2Font = new JComboBox<>(fonts);
			panel.add(comboMessage2Font);
			
			message2ColorChooser = new JColorChooser();
			panel.add(message2ColorChooser);
			
			comboMessage2Size = new JComboBox<>(sizes);
			panel.add(comboMessage2Size);
		} else if (message == 3) {
			textAreaMessage3Top = new JTextArea();
			panel.add(textAreaMessage3Top);
			
			textAreaMessage3Bottom = new JTextArea();
			panel.add(textAreaMessage3Bottom);
			
			chkMessage3Show = new JCheckBox("Show/Hide");
			if (showByDefault) {
				chkMessage3Show.setSelected(true);
			}
			panel.add(chkMessage3Show);
			
			panel.setBorder(BorderFactory.createTitledBorder("Message 3"));
			
			comboMessage3Font = new JComboBox<>(fonts);
			panel.add(comboMessage3Font);
			
			message3ColorChooser = new JColorChooser();
			panel.add(message3ColorChooser);
			
			comboMessage3Size = new JComboBox<>(sizes);
			panel.add(comboMessage3Size);
		}
		return panel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MemeMaker();
	}

}
