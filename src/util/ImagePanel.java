package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private BufferedImage image;
	
	public ImagePanel(String fileLocation) {
		setImage(fileLocation);
	}

	public ImagePanel(File file) {
		setImage(file);
	}

	public ImagePanel(BufferedImage image) {
		this.image = image;
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
