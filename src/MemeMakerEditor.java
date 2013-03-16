import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import util.EditableImagePanel;

public class MemeMakerEditor extends JPanel {

	/**
	 * The Editors TabbedPane
	 */
	private JTabbedPane memeTabbedPane;

	/**
	 * The first Meme Tab
	 */
	private JComponent pnlMemeOne;

	/**
	 * The second Meme Tab
	 */
	private JComponent pnlMemeTwo;

	/**
	 * The third Meme tab
	 */
	private JComponent pnlMemeThree;

	/**
	 * The MemeMakerConfiguration Panel for tab one
	 */
	private MemeMakerConfiguration pnlConfigOne;

	/**
	 * The MemeMakerConfiguration Panel for tab two
	 */
	private MemeMakerConfiguration pnlConfigTwo;

	/**
	 * The MemeMakerConfiguration Panel for tab three
	 */
	private MemeMakerConfiguration pnlConfigThree;

	private JPanel pnlMaxBoundsOne;
	private JPanel pnlMaxBoundsTwo;
	private JPanel pnlMaxBoundsThree;

	/**
	 * The center coordinates for the pnlMaxBounds
	 */
	public static final int IMAGE_CENTER_X = 445;
	public static final int IMAGE_CENTER_Y = 295;

	/**
	 * The Constructor for the MemeMakerEditor
	 */
	public MemeMakerEditor() {
		memeTabbedPane = new JTabbedPane();
		createTabs();
		add(memeTabbedPane);
		memeTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	/**
	 * Creates the tabs for the TabbedPane
	 */
	private void createTabs() {
		// First Tab
		pnlMemeOne = makeMemePanel("Meme Version 1");
		pnlMemeOne.setPreferredSize(new Dimension(MemeMaker.WINDOW_WIDTH,
				MemeMaker.WINDOW_HEIGHT));
		memeTabbedPane.addTab("Meme Version 1", null, pnlMemeOne,
				"Does nothing");
		memeTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		pnlConfigOne = new MemeMakerConfiguration();
		pnlConfigOne.setBounds(12, 10,
				MemeMakerConfiguration.CONFIG_PANEL_WIDTH,
				MemeMakerConfiguration.CONFIG_PANEL_HEIGHT);
		
		pnlMaxBoundsOne = new JPanel();
		pnlMaxBoundsOne.setPreferredSize(new Dimension(
				MemeMaker.INPUT_IMAGE_MAX_WIDTH,
				MemeMaker.INPUT_IMAGE_MAX_HEIGHT));
		pnlMaxBoundsOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlMaxBoundsOne.setBounds(MemeMakerConfiguration.CONFIG_PANEL_WIDTH+25, 19, 890,
				589);

		pnlMemeOne.add(pnlConfigOne);
		pnlMemeOne.add(pnlMaxBoundsOne);


		// Second Tab
		pnlMemeTwo = makeMemePanel("Meme Version 2");
		pnlMemeTwo.setPreferredSize(new Dimension(MemeMaker.WINDOW_WIDTH,
				MemeMaker.WINDOW_HEIGHT));
		memeTabbedPane.addTab("Meme Version 2", null, pnlMemeTwo,
				"Does nothing");
		memeTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		pnlConfigTwo = new MemeMakerConfiguration();
		pnlConfigTwo.setBounds(12, 10,
				MemeMakerConfiguration.CONFIG_PANEL_WIDTH,
				MemeMakerConfiguration.CONFIG_PANEL_HEIGHT);
		
		pnlMaxBoundsTwo = new JPanel();
		pnlMaxBoundsTwo.setPreferredSize(new Dimension(
				MemeMaker.INPUT_IMAGE_MAX_WIDTH,
				MemeMaker.INPUT_IMAGE_MAX_HEIGHT));
		pnlMaxBoundsTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlMaxBoundsTwo.setBounds(MemeMakerConfiguration.CONFIG_PANEL_WIDTH+25, 19, 890,
				589);

		pnlMemeTwo.add(pnlConfigTwo);
		pnlMemeTwo.add(pnlMaxBoundsTwo);

		// Third Tab
		pnlMemeThree = makeMemePanel("Meme Version 3");
		pnlMemeThree.setPreferredSize(new Dimension(MemeMaker.WINDOW_WIDTH,
				MemeMaker.WINDOW_HEIGHT));
		memeTabbedPane.addTab("Meme Version 3", null, pnlMemeThree,
				"Does nothing");
		memeTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		pnlConfigThree = new MemeMakerConfiguration();
		pnlConfigThree.setBounds(12, 10,
				MemeMakerConfiguration.CONFIG_PANEL_WIDTH,
				MemeMakerConfiguration.CONFIG_PANEL_HEIGHT);
		
		pnlMaxBoundsThree = new JPanel();
		pnlMaxBoundsThree.setPreferredSize(new Dimension(
				MemeMaker.INPUT_IMAGE_MAX_WIDTH,
				MemeMaker.INPUT_IMAGE_MAX_HEIGHT));
		pnlMaxBoundsThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlMaxBoundsThree.setBounds(MemeMakerConfiguration.CONFIG_PANEL_WIDTH+25, 19, 890,
				589);

		pnlMemeThree.add(pnlConfigThree);
		pnlMemeThree.add(pnlMaxBoundsThree);

	}

	/**
	 * Dynamically sets the ediatableImagePanel at the center of
	 * pnlMaxBounds panel.
	 * 
	 * @param editableImagePanel
	 */
	private void setEditableImagePanelPos(EditableImagePanel editableImagePanel) {
		editableImagePanel.setBounds(
				IMAGE_CENTER_X - editableImagePanel.getWidth(), IMAGE_CENTER_Y
						- editableImagePanel.getHeight(),
				editableImagePanel.getWidth(), editableImagePanel.getHeight());
	}

	/**
	 * Creates the panel for the tab
	 * 
	 * @param text
	 *            Title of the tab
	 * @return Panel for created tab
	 */
	protected JComponent makeMemePanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(null);
		panel.add(filler);
		return panel;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MemeMakerEditor.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
