import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;
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
		ChangeListener, FocusListener {

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
	private ButtonGroup buttonGroup;
	
	/**
	 * Timer for firing repaint for the TopLine TestArea
	 */
	private Timer timerTopLine;
	
	/**
	 * Timer for firing repaint for the BottomLine TestArea
	 */
	private Timer timerBottomLine;

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
		txtAreaTopLine.addFocusListener(this);

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
		txtAreaBottomLine.addFocusListener(this);

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
		btnColorChooser.setBackground(Color.WHITE);
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
		String[] fontSize = {"24", "26", "28", "36", "48", "72" };
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
		radioPng.setActionCommand("png");
		radioPng.setBounds(175, 500, 75, 25);
		
		// Create jpeg radio button
		radioJpg = new JRadioButton("*.jpg");
		radioJpg.setSelected(true);
		radioJpg.setActionCommand("jpg");
		radioJpg.setBounds(250, 500, 75, 25);
		
		// Create Button group
		 buttonGroup = new ButtonGroup();
		 buttonGroup.add(radioPng);
		 buttonGroup.add(radioJpg);

	}
	
	/**
	 * @return the txtAreaTopLine
	 */
	public JTextArea getTxtAreaTopLine() {
		return txtAreaTopLine;
	}

	/**
	 * @return the txtAreaBottomLine
	 */
	public JTextArea getTxtAreaBottomLine() {
		return txtAreaBottomLine;
	}

	/**
	 * @return the comboBoxSetFont
	 */
	public JComboBox getComboBoxSetFont() {
		return comboBoxSetFont;
	}

	/**
	 * @return the comboBoxFontSize
	 */
	public JComboBox getComboBoxFontSize() {
		return comboBoxFontSize;
	}

	/**
	 * @return the btnColorChooser
	 */
	public JButton getBtnColorChooser() {
		return btnColorChooser;
	}

	/**
	 * @return the radioPng
	 */
	public JRadioButton getRadioPng() {
		return radioPng;
	}

	/**
	 * @return the radioJpg
	 */
	public JRadioButton getRadioJpg() {
		return radioJpg;
	}

	/**
	 * @return the group
	 */
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	/**
	 * @param txtAreaTopLine the txtAreaTopLine to set
	 */
	public void setTxtAreaTopLine(JTextArea txtAreaTopLine) {
		this.txtAreaTopLine = txtAreaTopLine;
	}

	/**
	 * @param txtAreaBottomLine the txtAreaBottomLine to set
	 */
	public void setTxtAreaBottomLine(JTextArea txtAreaBottomLine) {
		this.txtAreaBottomLine = txtAreaBottomLine;
	}

	/**
	 * @param comboBoxSetFont the comboBoxSetFont to set
	 */
	public void setComboBoxSetFont(JComboBox comboBoxSetFont) {
		this.comboBoxSetFont = comboBoxSetFont;
	}

	/**
	 * @param comboBoxFontSize the comboBoxFontSize to set
	 */
	public void setComboBoxFontSize(JComboBox comboBoxFontSize) {
		this.comboBoxFontSize = comboBoxFontSize;
	}

	/**
	 * @param btnColorChooser the btnColorChooser to set
	 */
	public void setBtnColorChooser(JButton btnColorChooser) {
		this.btnColorChooser = btnColorChooser;
	}

	/**
	 * @param radioPng the radioPng to set
	 */
	public void setRadioPng(JRadioButton radioPng) {
		this.radioPng = radioPng;
	}

	/**
	 * @param radioJpg the radioJpg to set
	 */
	public void setRadioJpg(JRadioButton radioJpg) {
		this.radioJpg = radioJpg;
	}

	/**
	 * @param group the group to set
	 */
	public void setButtonGroup(ButtonGroup group) {
		this.buttonGroup = group;
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
			updateFontColor();
			MemeMaker.getSelectedEditorTabImagePanel().repaint();
		}
		if(e.getSource() == comboBoxFontSize){
			updateFont();
			MemeMaker.getSelectedEditorTabImagePanel().repaint();
		}
		if(e.getSource() == comboBoxSetFont){
			updateFont();
			MemeMaker.getSelectedEditorTabImagePanel().repaint();
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
	
	/**
	 * Updates the Font the user Chooses
	 */
	private void updateFont(){
		String fontType = (String) comboBoxSetFont.getSelectedItem();
		String fontSize = (String) comboBoxFontSize.getSelectedItem();
		int size = Integer.parseInt(fontSize);
		Font font = new Font(fontType, Font.PLAIN, size);
		MemeMaker.getSelectedEditorTabImagePanel().setFont(font);
	}
	
	/**
	 * Updates the Font Color the User Chooses
	 */
	private void updateFontColor() {
		Color fontColor = btnColorChooser.getBackground();
		MemeMaker.getSelectedEditorTabImagePanel().setFontColor(fontColor);
	}

	/*
	 * Focus Listener Methods
	 */
	@Override
	public void focusGained(FocusEvent arg) {
		if(arg.getSource() == txtAreaTopLine){
			timerTopLine = new Timer(1, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateFont();
					updateFontColor();
					String text = txtAreaTopLine.getText();
					MemeMaker.getSelectedEditorTabImagePanel().setTopText(text);
					MemeMaker.getSelectedEditorTabImagePanel().repaint();
				}
			});timerTopLine.start();
		}
		if(arg.getSource() == txtAreaBottomLine){
			timerBottomLine = new Timer(1, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateFont();
					updateFontColor();
					String text = txtAreaBottomLine.getText();
					MemeMaker.getSelectedEditorTabImagePanel().setBottomText(text);
					MemeMaker.getSelectedEditorTabImagePanel().repaint();
				}
			});timerBottomLine.start();
		} 

	}

	@Override
	public void focusLost(FocusEvent arg) {
		if(arg.getSource() == txtAreaTopLine){
			timerTopLine.stop();
		}
		if(arg.getSource() == txtAreaBottomLine){
			timerBottomLine.stop();
		}
	}
	
	
}
