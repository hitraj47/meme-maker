import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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

	public class EditorTab extends JPanel {
		private String tabTitle;
		private MemeMakerConfiguration configPanel;
		private EditableImagePanel imagePanel;

		public EditorTab() {
			this.setLayout(null);
			this.setPreferredSize(new Dimension(MemeMaker.WINDOW_WIDTH, MemeMaker.WINDOW_HEIGHT));
		}

		public EditorTab(String tabTitle, BufferedImage image) {
			this();
			this.setTabTitle(tabTitle);
			
			configPanel = new MemeMakerConfiguration();
			configPanel.setBounds(10, 0, 350, 600);
			
			imagePanel = new EditableImagePanel(image);
			int x = getCenteredx(imagePanel);
			imagePanel.setBounds(x, 0, imagePanel.getWidth(), imagePanel.getHeight());
			
			this.add(configPanel);
			this.add(imagePanel);
		}

		private int getCenteredx(EditableImagePanel imagePanel) {
			return 180 + (imagePanel.getWidth()/2);
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
