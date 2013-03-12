import java.awt.Dimension;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
		pnlMemeOne = makeTextPanel("Meme Version 1");
		pnlMemeOne.setPreferredSize(new Dimension(1280, 700));
		memeTabbedPane.addTab("Meme Version 1", null, pnlMemeOne, "Does nothing");
		memeTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		pnlConfigOne = new MemeMakerConfiguration();
		pnlConfigOne.setBounds(10,10,350,600);
		pnlMemeOne.add(pnlConfigOne);
		
		// Second Tab
		pnlMemeTwo = makeTextPanel("Meme Version 2");
		pnlMemeTwo.setPreferredSize(new Dimension(1280, 700));
		memeTabbedPane.addTab("Meme Version 2", null, pnlMemeTwo, "Does nothing");
		memeTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		pnlConfigTwo = new MemeMakerConfiguration();
		pnlConfigTwo.setBounds(10,10,350,600);
		pnlMemeTwo.add(pnlConfigTwo);
		
		// Third Tab
		pnlMemeThree = makeTextPanel("Meme Version 3");
		pnlMemeThree.setPreferredSize(new Dimension(1280, 700));
		memeTabbedPane.addTab("Meme Version 3", null, pnlMemeThree, "Does nothing");
		memeTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		pnlConfigThree = new MemeMakerConfiguration();
		pnlConfigThree.setBounds(10,10,350,600);
		pnlMemeThree.add(pnlConfigThree);
		
	}

	/**
	 * Creates the panel for the tab
	 * @param text
	 * 		 Title of the tab
	 * @return Panel for created tab
	 */
	protected JComponent makeTextPanel(String text) {
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
