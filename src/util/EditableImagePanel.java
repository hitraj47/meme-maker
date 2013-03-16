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
import java.util.ArrayList;

import javax.swing.JPanel;

import org.imgscalr.Scalr;

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
	 * The start and end x,y coords of a textbox rectangle
	 */
	private int textx1, textx2, texty1, texty2;

	/**
	 * Flag variable for drawing a textbox
	 */
	private boolean isNewTextBox = true;
	
	/**
	 * Flag variable to tell paintComponenet to make a text box
	 */
	private boolean createTextBox = false;

	/**
	 * ArrayList of text on the image
	 */
	private ArrayList<TextBox> textBoxes = new ArrayList<TextBox>();

	/**
	 * When resizing image, use this to let the class decide how to constrain
	 * proportions
	 */
	public static final int RESIZE_CONSTRAIN_PROPORTIONS = 0;

	/**
	 * When resizing image, use this to constrain proportions based on image
	 * width
	 */
	public static final int RESIZE_CONSTRAIN_WIDTH = 1;

	/**
	 * When resizing image, use this to constrain proportions based on image
	 * height
	 */
	public static final int RESIZE_CONSTRAIN_HEIGHT = 2;

	/**
	 * When resizing image, use this to resize width and height regardless of
	 * width/height ratio
	 */
	public static final int RESIZE_FIT_EXACT = 3;

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

	public void setImage(BufferedImage image) {
		this.image = image;
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		repaint();
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
		// draw the image
		g.drawImage(image, 0, 0, null);

		// draw any text that might be on the image
		if (textBoxes.size() > 0) {
			for (TextBox t : textBoxes) {
				g.setColor(t.getColor());
				g.setFont(t.getFont());
				g.drawString(t.getText(), t.getX(), t.getY());
			}
		}

		if (getEditingMode() == MODE_CROP) {
			drawCropBox(g);
		} else if (getEditingMode() == MODE_TEXT) {
			drawTextBox(g);
		}

	}

	private void drawTextBox(Graphics g) {
		// get the actual width and height of the drawn rectangle
		int width = getTextx1() - getTextx2();
		int height = getTexty1() - getTexty2();

		// get the width and height to use for drawing the rectangle
		int w = Math.abs(width);
		int h = Math.abs(height);

		// get the coordinates for placing the rectangle
		int x = width < 0 ? getTextx1() : getTextx2();
		int y = height < 0 ? getTexty1() : getTexty2();

		if (!this.isNewTextBox) {
			// draw a rectangle to show the user the area
			g.drawRect(x, y, w, h);

			if (createTextBox) {
				
			}
		}

	}
	
	public void addText(String text, int x, int y, Font font, Color color) {
		TextBox tb = new TextBox(x, y);
		tb.setFont(font);
		tb.setColor(color);
		textBoxes.add(tb);
	}

	private void drawCropBox(Graphics g) {
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
		if (getEditingMode() == MODE_TEXT) {
			int x = e.getX();
			int y = e.getY();
			if (textBoxes.size() > 0) {
				for (TextBox t : textBoxes) {
					/**
					 * TODO: Check to see if mouse is within the bounds of the
					 * text box. If so, select it and allow the user to edit it.
					 * 
					 */
				}
			} else {
				// TODO: New blank text box. Maybe make it so that as soon as
				// the user clicks
				// they can start typing text.
				//textBoxes.add(new TextBox(x, y));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else if (getEditingMode() == MODE_TEXT) {
			setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// set back to default when mouse outside image
		setCursor(Cursor.getDefaultCursor());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCropx1(e.getX());
			setCropy1(e.getY());
			isNewCropRect = true;
			repaint();
		} else if (getEditingMode() == MODE_TEXT) {
			setTextx1(e.getX());
			setTexty1(e.getY());
			isNewTextBox = true;
			createTextBox = false;
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (getEditingMode() == MODE_CROP) {
			setCropx2(e.getX());
			setCropy2(e.getY());
			repaint();
		} else if (getEditingMode() == MODE_TEXT) {
			setTextx2(e.getX());
			setTexty2(e.getY());
			createTextBox = true;
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

	/**
	 * Returns a resized image
	 * 
	 * @param width
	 *            The new width in pixels
	 * @param height
	 *            The new height in pixels
	 * @param resizeMode
	 *            How to resize the image
	 * @return An Image
	 */
	public BufferedImage getResizedImage(int width, int height, int resizeMode) {
		BufferedImage resizedImage = null;
		if (resizeMode == RESIZE_CONSTRAIN_PROPORTIONS) {
			resizedImage = Scalr.resize(image, width);
		} else if (resizeMode == RESIZE_CONSTRAIN_WIDTH) {
			resizedImage = Scalr.resize(image, Scalr.Mode.FIT_TO_WIDTH, width);
		} else if (resizeMode == RESIZE_CONSTRAIN_HEIGHT) {
			resizedImage = Scalr
					.resize(image, Scalr.Mode.FIT_TO_HEIGHT, height);
		} else if (resizeMode == RESIZE_FIT_EXACT) {
			resizedImage = Scalr.resize(image, Scalr.Mode.FIT_EXACT, width,
					height);
		}
		return resizedImage;
	}

	/**
	 * @return the textx1
	 */
	public int getTextx1() {
		return textx1;
	}

	/**
	 * @param textx1
	 *            the textx1 to set
	 */
	public void setTextx1(int textx1) {
		this.textx1 = textx1;
	}

	/**
	 * @return the textx2
	 */
	public int getTextx2() {
		return textx2;
	}

	/**
	 * @param textx2
	 *            the textx2 to set
	 */
	public void setTextx2(int textx2) {
		this.textx2 = textx2;
	}

	/**
	 * @return the texty1
	 */
	public int getTexty1() {
		return texty1;
	}

	/**
	 * @param texty1
	 *            the texty1 to set
	 */
	public void setTexty1(int texty1) {
		this.texty1 = texty1;
	}

	/**
	 * @return the texty2
	 */
	public int getTexty2() {
		return texty2;
	}

	/**
	 * @param texty2
	 *            the texty2 to set
	 */
	public void setTexty2(int texty2) {
		this.texty2 = texty2;
	}

	public class TextBox {
		private String text;
		private int x, y;
		private int fontSize;
		private Font font;
		private String fontName;
		private int fontStyle;
		private Color color;
		private boolean backgroundTransparent = true;
		private Color backgroundColor;

		TextBox(int x, int y) {
			this.setX(x);
			this.setY(y);
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}

		/**
		 * @return the size
		 */
		public int getSize() {
			return fontSize;
		}

		/**
		 * @param size
		 *            the size to set
		 */
		public void setSize(int size) {
			this.fontSize = size;
		}

		/**
		 * @return the font
		 */
		public Font getFont() {
			return font;
		}

		/**
		 * @param font
		 *            the font to set
		 */
		public void setFont(Font font) {
			this.font = font;
		}

		/**
		 * @return the color
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * @param color
		 *            the color to set
		 */
		public void setColor(Color color) {
			this.color = color;
		}

		/**
		 * @return the backgroundTransparent
		 */
		public boolean isBackgroundTransparent() {
			return backgroundTransparent;
		}

		/**
		 * @param backgroundTransparent
		 *            the backgroundTransparent to set
		 */
		public void setBackgroundTransparent(boolean backgroundTransparent) {
			this.backgroundTransparent = backgroundTransparent;
		}

		/**
		 * @return the backgroundColor
		 */
		public Color getBackgroundColor() {
			return backgroundColor;
		}

		/**
		 * @param backgroundColor
		 *            the backgroundColor to set
		 */
		public void setBackgroundColor(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
		}

		/**
		 * @return the fontName
		 */
		public String getFontName() {
			return fontName;
		}

		/**
		 * @param fontName
		 *            the fontName to set
		 */
		public void setFontName(String fontName) {
			this.fontName = fontName;
		}

		/**
		 * @return the fontStyle
		 */
		public int getFontStyle() {
			return fontStyle;
		}

		/**
		 * @param fontStyle
		 *            the fontStyle to set
		 */
		public void setFontStyle(int fontStyle) {
			this.fontStyle = fontStyle;
		}
	}

}
