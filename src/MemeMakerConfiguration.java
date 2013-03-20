import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.FontCellRenderer;

/**
 * Creates the configuration panel for the MemeMakerEditor Panel
 * 
 * @author Greg Westerfield, Jr. Raj Ramsaroop
 */

public class MemeMakerConfiguration extends JPanel implements ActionListener,
		ChangeListener {

	/**
	 * Title Border for the Meme Message
	 */
	private TitledBorder titledBorderMessage;

	/**
	 * Label for top line of Meme message
	 */
	private JLabel lblTopLine;

	/**
	 * Label for bottom line of Meme message
	 */
	private JLabel lblBottomLine;

	/**
	 * Label for the Set Font ComboBox
	 */
	private JLabel lblSetFont;

	/**
	 * Label for the Set Color ComboBox
	 */
	private JLabel lblTextColor;

	/**
	 * Label for the Set Font Size ComboBox
	 */
	private JLabel lblFontSize;

	/**
	 * TextArea for Meme Top Line message
	 */
	private JTextArea txtAreaTopLine;

	/**
	 * TextArea for Meme Bottom Line message
	 */
	private JTextArea txtAreaBottomLine;

	/**
	 * ComboBox for setting the message Font
	 */
	private JComboBox comboBoxSetFont;

	/**
	 * ComboBox for setting the message Font Size
	 */
	private JComboBox comboBoxFontSize;

	/**
	 * ColorChooser for Message Font Color
	 */
	private JColorChooser colorChooser;

	/**
	 * Button for launching the ColorChooser
	 */
	private JButton btnColorChooser;
	
	/**
	 * Button to Generate/Save Meme
	 */
	private JButton btnSave;
	
	/** 
	 * Radio button to save as *.png
	 */
	private JRadioButton radioPng;
	
	/** 
	 * Radio button to save as *.jpg
	 */
	private JRadioButton radioJpg;
	
	/**
	 * ButtonGroup for JRadioButtons
	 */
	private ButtonGroup group;

	/**
	 * MemeMakeConfiguration Width
	 */
	public static final int CONFIG_PANEL_WIDTH = 350;

	/**
	 * MemeMakerConfiguration Height
	 */
	public static final int CONFIG_PANEL_HEIGHT = 600;

	/**
	 * Font used for labels
	 */
	private static final Font BORDER_FONT = new Font("Arial", Font.BOLD, 16);
	private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);
	private static final Font TEXT_AREA_FONT = new Font("Arial", Font.PLAIN, 14);

	/**
	 * Constructor for MemeMakerConfiguration
	 */
	public MemeMakerConfiguration() {

		createConfigurationComponents();
		setPreferredSize(new Dimension(CONFIG_PANEL_WIDTH, CONFIG_PANEL_HEIGHT));
		setLayout(null);
		add(lblTopLine);
		add(txtAreaTopLine);
		add(lblBottomLine);
		add(txtAreaBottomLine);
		add(lblSetFont);
		add(comboBoxSetFont);
		add(lblTextColor);
		add(btnColorChooser);
		add(lblFontSize);
		add(comboBoxFontSize);
		add(btnSave);
		add(radioPng);
		add(radioJpg);

	}

	/**
	 * Creates the configuration components on the MemeMakerConfiguration Panel
	 */
	private void createConfigurationComponents() {
		// Create Titled Message Border
		Border blackline = BorderFactory.createLineBorder(Color.black);
		titledBorderMessage = BorderFactory.createTitledBorder(blackline,
				"Meme Message");
		titledBorderMessage.setTitleJustification(TitledBorder.LEFT);
		titledBorderMessage.setTitleFont(BORDER_FONT);
		this.setBorder(titledBorderMessage);

		// Create Top Line Label
		lblTopLine = new JLabel("Top Line:");
		lblTopLine.setFont(LABEL_FONT);
		lblTopLine.setBounds(10, 35, 75, 15);

		// Create Top Line Text Area
		txtAreaTopLine = new JTextArea();
		txtAreaTopLine.setEditable(true);
		txtAreaTopLine.setLineWrap(true);
		txtAreaTopLine.setWrapStyleWord(true);
		txtAreaTopLine.setFont(TEXT_AREA_FONT);
		txtAreaTopLine.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtAreaTopLine.setBounds(10, 55, 330, 100);

		// Create Bottom Line Label
		lblBottomLine = new JLabel("Bottom Line:");
		lblBottomLine.setFont(LABEL_FONT);
		lblBottomLine.setBounds(10, 175, 100, 15);

		// Create Bottom Line Text Area
		txtAreaBottomLine = new JTextArea();
		txtAreaBottomLine.setEditable(true);
		txtAreaBottomLine.setLineWrap(true);
		txtAreaBottomLine.setWrapStyleWord(true);
		txtAreaBottomLine.setFont(TEXT_AREA_FONT);
		txtAreaBottomLine
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtAreaBottomLine.setBounds(10, 195, 330, 100);

		// Create Set Font Label
		lblSetFont = new JLabel("Set Font:");
		lblSetFont.setFont(LABEL_FONT);
		lblSetFont.setBounds(25, 350, 75, 15);

		// Create Set Font ComboBox
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		comboBoxSetFont = new JComboBox(fonts);
		comboBoxSetFont.setRenderer(new FontCellRenderer());
		comboBoxSetFont.setBounds(175, 350, 150, 25);
		comboBoxSetFont.addActionListener(this);

		// Create Text Color Label
		lblTextColor = new JLabel("Text Color:");
		lblTextColor.setFont(LABEL_FONT);
		lblTextColor.setBounds(25, 400, 125, 15);

		// Create Button for Color Chooser
		btnColorChooser = new JButton();
		btnColorChooser.setBackground(Color.BLUE);
		btnColorChooser.setBounds(175, 400, 75, 25);
		btnColorChooser.addActionListener(this);

		// Create Font Color Chooser
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);

		// Create Font Size Label
		lblFontSize = new JLabel("Font Size:");
		lblFontSize.setFont(LABEL_FONT);
		lblFontSize.setBounds(25, 450, 125, 15);

		// Create Font Size ComboBox
		String[] fontSize = { "8pt", "9pt", "10pt", "11pt", "12pt", "14pt",
				"16pt", "18pt", "20pt", "22pt", "24pt", "26pt", "28pt", "36pt",
				"48pt", "72pt" };
		comboBoxFontSize = new JComboBox(fontSize);
		comboBoxFontSize.setBounds(175, 450, 50, 25);
		comboBoxFontSize.addActionListener(this);
	
		// Create the save button
		btnSave = new JButton("Save");
		btnSave.setFont(LABEL_FONT);
		btnSave.setBounds(25, 500, 75, 25);
		btnSave.addActionListener(new MemeMakerListener());
		
		// Create png radio button
		radioPng = new JRadioButton("*.png");
		radioPng.setBounds(175, 500, 75, 25);
		
		// Create jpeg radio button
		radioJpg = new JRadioButton("*.jpg");
		radioJpg.setBounds(250, 500, 75, 25);
		
		// Create Button group
		 group = new ButtonGroup();
		 group.add(radioPng);
		 group.add(radioJpg);

	}
	
	/*
	 * Change Listener Methods
	 */
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		Color newColor = colorChooser.getColor();
		btnColorChooser.setForeground(newColor);
	}

	

	/*
	 * Action Listener Methods
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnColorChooser){
			launchColorChooser();
		}
	}

	/**
	 * Launches the ColorChooser
	 */
	private void launchColorChooser() {
		Color newColor = JColorChooser.showDialog(MemeMakerConfiguration.this,
				"Choose Font Color", btnColorChooser.getBackground());
		if (newColor != null) {
			btnColorChooser.setBackground(newColor);
		}
	}

}
