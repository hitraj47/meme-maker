package util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EditableImagePanel extends JPanel implements MouseListener,
		MouseMotionListener {

	/**
	 * The image to be used
	 */
	private BufferedImage image;

	/**
	 * This determines what to draw on the image panel in the pain method
	 */
	private int editingMode;

	/**
	 * Flag variable for cropping mode. True by default.
	 */
	private boolean isNewCropRect = true;

	/**
	 * The start and end x,y coordinates of cropping rectangle being drawn on
	 * image for cropping
	 */
	private int cropx1, cropx2, cropy1, cropy2;

	/**
	 * A cropped image
	 */
	private BufferedImage croppedImage;

	/**
	 * Constant for normal image viewing mode
	 */
	public static final int MODE_VIEW = 0;

	/**
	 * Constant for cropping mode
	 */
	public static final int MODE_CROP = 1;

	/**
	 * Constant for adding/editing/deleting text
	 */
	public static final int MODE_TEXT = 2;

	/**
	 * Default constructor
	 */
	public EditableImagePanel() {
		setEditingMode(MODE_VIEW);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * Create a new image panel from a file location
	 * 
	 * @param fileLocation
	 *            The location of the file
	 */
	public EditableImagePanel(String fileLocation) {
		this();
		setImage(fileLocation);
	}

	/**
	 * Crate a new image panel from a file object
	 * 
	 * @param file
	 *            The image file object to be used
	 */
	public EditableImagePanel(File file) {
		this();
		setImage(file);
	}

	/**
	 * Create a new image panel from an image object
	 * 
	 * @param image
	 *            The image object to be used
	 */
	public EditableImagePanel(BufferedImage image) {
		this();
		this.image = image;
	}

	/**
	 * Sets the editing mode of the panel
	 * 
	 * @param editingMode
	 *            The mode to be set
	 */
	public void setEditingMode(int editingMode) {
		this.editingMode = editingMode;
		repaint();
	}

	/**
	 * Returns the mode the image panel is in
	 * 
	 * @return The editing mode the panel is in
	 */
	public int getEditingMode() {
		return this.editingMode;
	}

	/**
	 * Sets the image from a file object
	 * 
	 * @param file
	 *            The file for the image
	 */
	public void setImage(File file) {
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			System.err.println("Could not create image panel with file: "
					+ file.getAbsolutePath());
		}
	}

	/**
	 * Sets the image from a file location
	 * 
	 * @param fileLocation
	 *            The location of the image file
	 */
	public void setImage(String fileLocation) {
		setImage(new File(fileLocation));
	}

	/**
	 * Returns the image being used for the panel
	 * 
	 * @return Image object being used for panel
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Return the width of the image
	 * 
	 * @return Width in pixels
	 */
	public int getWidth() {
		return image.getWidth();
	}

	/**
	 * Return the height of the image
	 * 
	 * @return Height in pixels
	 */
	public int getHeight() {
		return image.getHeight();
	}

	/**
	 * Override the JPanel's getPreferredSize() method to return the size of the
	 * image, not the JPanel
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}

	/**
	 * This will paint stuff differently according to the mode
	 * 
	 * @param g
	 *            The graphics object
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);

		if (getEditingMode() == MODE_CROP) {
			// get the actual width and height of the drawn rectangle
			int width = getCropx1() - getCropx2();
			int height = getCropy1() - getCropy2();

			// get the width and height to use for drawing the rectangle
			int w = Math.abs(width);
			int h = Math.abs(height);

			// get the coordinates for placing the rectangle
			int x = width < 0 ? getCropx1() : getCropx2();
			int y = height < 0 ? getCropy1() : getCropy2();

			if (!this.isNewCropRect) {
				// draw a rectangle to show the user the area
				g.drawRect(x, y, w, h);

				// create a cropped image
				setCroppedImage(x, y, w, h);
			}
		} else if (getEditingMode() == MODE_TEXT) {
		}
	}

	public void drawText(String text, int x, int y, Font font, Color color) {
		
	}
	
	private void setCroppedImage(int x, int y, int width, int height) {
		if (width > 0 && height > 0) {
			croppedImage = image.getSubimage(x, y, width, height);
		} else {
			croppedImage = image;
		}
		
	}

	public BufferedImage getCroppedImage() {
		return croppedImage;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCropx2(e.getX());
			setCropy2(e.getY());
			isNewCropRect = false;
			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCursor(Cursor.getDefaultCursor());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCropx1(e.getX());
			setCropy1(e.getY());
			isNewCropRect = true;
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCropx2(e.getX());
			setCropy2(e.getY());
			repaint();
		}
	}

	/**
	 * @return the cropx1
	 */
	public int getCropx1() {
		return cropx1;
	}

	/**
	 * @param cropx1
	 *            the cropx1 to set
	 */
	public void setCropx1(int cropx1) {
		this.cropx1 = cropx1;
	}

	/**
	 * @return the cropx2
	 */
	public int getCropx2() {
		return cropx2;
	}

	/**
	 * @param cropx2
	 *            the cropx2 to set
	 */
	public void setCropx2(int cropx2) {
		this.cropx2 = cropx2;
	}

	/**
	 * @return the cropy1
	 */
	public int getCropy1() {
		return cropy1;
	}

	/**
	 * @param cropy1
	 *            the cropy1 to set
	 */
	public void setCropy1(int cropy1) {
		this.cropy1 = cropy1;
	}

	/**
	 * @return the cropy2
	 */
	public int getCropy2() {
		return cropy2;
	}

	/**
	 * @param cropy2
	 *            the cropy2 to set
	 */
	public void setCropy2(int cropy2) {
		this.cropy2 = cropy2;
	}

}
