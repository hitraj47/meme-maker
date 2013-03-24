import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import util.EditableImagePanel;

public class MemeMakerEditor extends JPanel {

	/**
	 * The Editors TabbedPane
	 */
	private JTabbedPane tabbedPane;

	/**
	 * ArrayList of editor tabs
	 */
	private ArrayList<EditorTab> editorTabs = new ArrayList<EditorTab>();

	/**
	 * The Constructor for the MemeMakerEditor
	 */
	public MemeMakerEditor() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	/**
	 * This will create and add a new tab to the arraylist and tabbedpane
	 * @param title The title for the tab
	 * @param image The image to be used
	 */
	public void createTab(String title, BufferedImage image) {
		EditorTab et = new EditorTab(title, image);
		et.setFocusable(true);
		editorTabs.add(et);
		tabbedPane.addTab(et.getTabTitle(), et);
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

	/**
	 * @return the tabbedPane
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * @return the editorTabs
	 */
	public ArrayList<EditorTab> getEditorTabs() {
		return editorTabs;
	}

	/**
	 * @param tabbedPane the tabbedPane to set
	 */
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	/**
	 * @param editorTabs the editorTabs to set
	 */
	public void setEditorTabs(ArrayList<EditorTab> editorTabs) {
		this.editorTabs = editorTabs;
	}

	public class EditorTab extends JPanel {
		private String tabTitle;
		private MemeMakerConfiguration configPanel;
		private EditableImagePanel imagePanel;
		private JPanel imageBorderPanel;

		public EditorTab() {
			this.setLayout(null);
			this.setPreferredSize(new Dimension(MemeMaker.WINDOW_WIDTH, MemeMaker.WINDOW_HEIGHT));
			this.setFocusable(true);
		}

		public EditorTab(String tabTitle, BufferedImage image) {
			this();
			this.setTabTitle(tabTitle);
			
			configPanel = new MemeMakerConfiguration();
			configPanel.setBounds(10, 10, MemeMakerConfiguration.CONFIG_PANEL_WIDTH, MemeMakerConfiguration.CONFIG_PANEL_HEIGHT);
			
			imageBorderPanel = new JPanel();
			imageBorderPanel.setLayout(null);
			imageBorderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			imageBorderPanel.setBounds(MemeMakerConfiguration.CONFIG_PANEL_WIDTH+25, 19, 890, 589);
			
			imagePanel = new EditableImagePanel(image);
			imagePanel.setEditingMode(EditableImagePanel.MODE_TEXT);
			int x = getCenteredx(imagePanel);
			int y = getCenteredy(imagePanel);
			imagePanel.setBounds(x, y, imagePanel.getWidth(), imagePanel.getHeight());
			
			this.add(configPanel);
			this.add(imageBorderPanel);
			imageBorderPanel.add(imagePanel);
		}

		/**
		 * Gets the centered X position of the imagePanel to center it on the imageBorderPanel
		 * @param imagePanel
		 * @return centerX
		 */
		private int getCenteredx(EditableImagePanel imagePanel) {
			return 445 - (imagePanel.getWidth()/2);
		}
		
		/**
		 * Gets the centered Y position of the imagePanel to center it on the imageBorderPanel
		 * @param imagePanel
		 * @return centerX
		 */
		private int getCenteredy(EditableImagePanel imagePanel) {
			return  295 - (imagePanel.getHeight()/2);
		}

		/**
		 * @return the tabTitle
		 */
		public String getTabTitle() {
			return tabTitle;
		}

		/**
		 * @param tabTitle
		 *            the tabTitle to set
		 */
		public void setTabTitle(String tabTitle) {
			this.tabTitle = tabTitle;
		}

		/**
		 * @return the configPanel
		 */
		public MemeMakerConfiguration getConfigPanel() {
			return configPanel;
		}

		/**
		 * @param configPanel
		 *            the configPanel to set
		 */
		public void setConfigPanel(MemeMakerConfiguration configPanel) {
			this.configPanel = configPanel;
		}

		/**
		 * @return the imagePanel
		 */
		public EditableImagePanel getImagePanel() {
			return imagePanel;
		}

		/**
		 * @param imagePanel
		 *            the imagePanel to set
		 */
		public void setImagePanel(EditableImagePanel imagePanel) {
			this.imagePanel = imagePanel;
		}

	}
}
