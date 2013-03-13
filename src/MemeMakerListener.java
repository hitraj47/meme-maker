import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import util.MultipleFileExtensionFilter;

public class MemeMakerListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == MemeMaker.ACTION_EXIT) {
			exit();
		} else if (e.getActionCommand() == MemeMaker.ACTION_NEW) {
			newMeme();
		} else if (e.getActionCommand() == MemeMaker.ACTION_ABOUT) {
			showAboutDialog();
		} else if (e.getActionCommand() == MemeMaker.ACTION_INSTRUCTIONS) {
			MemeMaker.showInstructionsScreen();
		}
	}

	private void newMeme() {
		JFileChooser fc = new JFileChooser();
		String extensionDescription = "Image File (*.jpeg, *.jpg, *.png)";
		String[] extensions = { "jpg", "jpeg", "png" };
		MultipleFileExtensionFilter filter = new MultipleFileExtensionFilter(extensionDescription, extensions);
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedImage inputImage = ImageIO.read(fc.getSelectedFile());
				if (!meetsMinImageSizeRequirements(inputImage)) {
					String message = "The image you selected is too small. For best results, select an image that is at least 400 pixels in width and height";
					String title = "NEED MOAR PIXELS!!!1!1!!one";
					JOptionPane.showMessageDialog(null, message, title,
							JOptionPane.ERROR_MESSAGE);
				} else if (!meetsMaxImageSizeRequirements(inputImage)) {
					String message = "The image width and/or height is above the recommended maximum. Lets the crop the image so your meme can look awesome!";
					String title = "Width or Height Above Recommended Maximum";
					JOptionPane.showMessageDialog(null, message, title,
							JOptionPane.INFORMATION_MESSAGE);
					MemeMaker.showSetupScreen(inputImage);
				} else {
					MemeMaker.showEditScreen(inputImage);
				}
			} catch (IOException e) {
				System.err.println("Could not open image file: " + fc.getSelectedFile().getAbsolutePath());
			}

		}
	}

	private boolean meetsMaxImageSizeRequirements(BufferedImage inputImage) {
		return (inputImage.getWidth() <= MemeMaker.INPUT_IMAGE_MAX_WIDTH)
				|| (inputImage.getHeight() <= MemeMaker.INPUT_IMAGE_MAX_HEIGHT);
	}

	private boolean meetsMinImageSizeRequirements(BufferedImage inputImage) {
		return (inputImage.getWidth() >= MemeMaker.INPUT_IMAGE_MIN_WIDTH)
				&& (inputImage.getHeight() >= MemeMaker.INPUT_IMAGE_MIN_HEIGHT);
	}

	private void showAboutDialog() {
		String message = "Version 0.1\n\n"
				+ "Created by Raj Ramsaroop and Greg Westerfield, Jr.";
		String title = "About";
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void exit() {
		// TODO: check if user has file loaded, if so, ask about exiting,
		// otherwise just exit
		// for now just exit the program
		System.exit(0);
	}

}
