import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		} else if (e.getActionCommand() == MemeMaker.ACTION_CROP) {
			BufferedImage croppedImage = MemeMaker.setupImageContainer
					.getCroppedImage();
			if (!MemeMaker.meetsMinImageSizeRequirements(croppedImage)) {

			} else if (!MemeMaker.meetsMaxImageSizeRequirements(croppedImage)) {

			} else {
				MemeMaker.showEditScreen(croppedImage);
			}
		} else if (e.getActionCommand() == MemeMaker.ACTION_RESIZE) {
			showResizePopup(MemeMaker.setupImageContainer.getImage());
		}
	}

	private void showResizePopup(BufferedImage image) {
		JPanel panel = new JPanel(new GridLayout(2, 2));

		JLabel lblWidth = new JLabel("Width: ");
		panel.add(lblWidth);

		JTextField txtWidth = new JTextField(Integer.toString(image.getWidth()));
		panel.add(txtWidth);

		JLabel lblHeight = new JLabel("Height: ");
		panel.add(lblHeight);

		JTextField txtHeight = new JTextField(Integer.toString(image
				.getHeight()));
		panel.add(txtHeight);

		Object[] buttonOptions = { "Resize", "Cancel" };
		int result = JOptionPane.showOptionDialog(null, panel, "Resize Image",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				buttonOptions, buttonOptions[0]);

		if (result == 0) {
			// TODO: Make sure user has entered numbers > 0
			int width = Integer.parseInt(txtWidth.getText());
			int height = Integer.parseInt(txtHeight.getText());
			BufferedImage resizedImage = MemeMaker.setupImageContainer
					.getResizedImage(width, height);
			if (!MemeMaker.meetsMaxImageSizeRequirements(resizedImage)) {
				JOptionPane.showMessageDialog(null,
					    "Resized image is too large. The width and height must be between 400 and 600 pixels.",
					    "Image Too Big",
					    JOptionPane.ERROR_MESSAGE);
			} else if (!MemeMaker.meetsMinImageSizeRequirements(resizedImage)) {
				JOptionPane.showMessageDialog(null,
					    "The resized image is too small. Width and height must be at least 400 pixels.",
					    "Image Too Small",
					    JOptionPane.ERROR_MESSAGE);
			} else {
				// TODO: show new resized image
				MemeMaker.setupImageContainer.setImage(resizedImage);
			}
		}
	}

	private void newMeme() {
		JFileChooser fc = new JFileChooser();
		String extensionDescription = "Image File (*.jpeg, *.jpg, *.png)";
		String[] extensions = { "jpg", "jpeg", "png" };
		MultipleFileExtensionFilter filter = new MultipleFileExtensionFilter(
				extensionDescription, extensions);
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedImage inputImage = ImageIO.read(fc.getSelectedFile());
				if (!MemeMaker.meetsMinImageSizeRequirements(inputImage)) {
					String message = "The image you selected is too small. For best results, select an image that is at least 400 pixels in width and height";
					String title = "NEED MOAR PIXELS!!!1!1!!one";
					JOptionPane.showMessageDialog(null, message, title,
							JOptionPane.ERROR_MESSAGE);
				} else if (!MemeMaker.meetsMaxImageSizeRequirements(inputImage)) {
					String message = "The image width and/or height is above the recommended maximum. Lets the crop the image so your meme can look awesome!";
					String title = "Width or Height Above Recommended Maximum";
					JOptionPane.showMessageDialog(null, message, title,
							JOptionPane.INFORMATION_MESSAGE);
					MemeMaker.showSetupScreen(inputImage);
				} else {
					MemeMaker.showEditScreen(inputImage);
				}
			} catch (IOException e) {
				System.err.println("Could not open image file: "
						+ fc.getSelectedFile().getAbsolutePath());
			}

		}
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
