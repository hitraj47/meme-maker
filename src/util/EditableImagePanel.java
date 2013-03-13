package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EditableImagePanel extends JPanel {

	/**
	 * The image to be used
	 */
	private BufferedImage image;

	/**
	 * This determines what to draw on the image panel in the pain method
	 */
	private int editingMode;
	
	/**
	 * Constant for cropping mode
	 */
	public static final int MODE_CROP = 0;
	
	/**
	 * Constant for adding/editing/deleting text
	 */
	public static final int MODE_TEXT = 1;
	
	public EditableImagePanel(String fileLocation) {
		setImage(fileLocation);
	}

	public EditableImagePanel(File file) {
		setImage(file);
	}

	public EditableImagePanel(BufferedImage image) {
		this.image = image;
	}
	
	public void setMode(int editingMode) {
		this.editingMode = editingMode;
	}
	
	public int getMode() {
		return this.editingMode;
	}

	public void setImage(File file) {
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			System.err.println("Could not create image panel with file: "
					+ file.getAbsolutePath());
		}
	}

	public void setImage(String fileLocation) {
		setImage(new File(fileLocation));
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	/**
	 * Override the JPanel's getPreferredSize() method to return the size
	 * of the image
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0,null);
	}

}
