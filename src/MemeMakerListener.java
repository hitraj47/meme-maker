import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import util.EditableImagePanel;
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
			boolean confirm = MemeMaker
					.showImagePreviewConfirmDialog(croppedImage);
			if (confirm) {
				MemeMaker.showEditScreen(croppedImage);
			}
		} else if (e.getActionCommand() == MemeMaker.ACTION_RESIZE) {
			showResizePopup(MemeMaker.setupImageContainer.getImage());
		} else if (e.getActionCommand() == MemeMaker.ACTION_SAVE) {
			BufferedImage image = MemeMaker.getSelectedEditorTabImagePanel().getImage();
			MemeMaker.getSelectedEditorTabImagePanel().setMemeImage(image);
			BufferedImage meme = MemeMaker.getSelectedEditorTabImagePanel().getMemeImage();
			boolean confirm = MemeMaker.showImagePreviewConfirmDialog(meme);
			if (confirm) {
				saveMeme();
			}
		}
	}

	private void saveMeme() {
		JFileChooser fc = new JFileChooser();
		String extensionDescription = "Image File (*.jpeg, *.jpg, *.png)";
		String[] extensions = { "jpg", "jpeg", "png" };
		MultipleFileExtensionFilter filter = new MultipleFileExtensionFilter(
				extensionDescription, extensions);
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				// TODO need to change to getMeme()
				BufferedImage meme = MemeMaker.getSelectedEditorTabImagePanel().getImage();
				String format = MemeMaker.getSelectedEditorTabConfigPanel().getButtonGroup().getSelection().getActionCommand();
				File outputFile = fc.getSelectedFile();
				String path = outputFile.getAbsolutePath();
				if(path.contains(".")){
				int index = path.lastIndexOf(".");
					path = path.substring(0, index) + "." + format;
				} else {
					path = path + "." + format;
				}
				outputFile = new File(path);

				ImageIO.write(meme, format, outputFile);

			} catch (IOException e) {
				System.err.println("Could not save image file: "
						+ fc.getSelectedFile().getAbsolutePath());
			}

		}
	}

	private void showResizePopup(final BufferedImage image) {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel lblWidth = new JLabel("Width: ");
		panel.add(lblWidth);

		final JTextField txtWidth = new JTextField(Integer.toString(image
				.getWidth()));
		panel.add(txtWidth);

		JLabel lblHeight = new JLabel("Height: ");
		panel.add(lblHeight);

		final JTextField txtHeight = new JTextField(Integer.toString(image
				.getHeight()));
		panel.add(txtHeight);

		// This button is used to update the width/height when contraining
		// proportions
		final JButton btnUpdateSize = new JButton("Update Size");
		btnUpdateSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int width = Integer.parseInt(txtWidth.getText());
				int height = Integer.parseInt(txtHeight.getText());
				BufferedImage resizedImage;

				if (image.getWidth() < image.getHeight()
						|| (image.getWidth() == image.getHeight())) {
					resizedImage = MemeMaker.setupImageContainer
							.getResizedImage(width, height,
									EditableImagePanel.RESIZE_CONSTRAIN_WIDTH);
					txtHeight.setText(Integer.toString(resizedImage.getHeight()));
				} else if (image.getHeight() < image.getWidth()) {
					resizedImage = MemeMaker.setupImageContainer
							.getResizedImage(width, height,
									EditableImagePanel.RESIZE_CONSTRAIN_HEIGHT);
					txtWidth.setText(Integer.toString(resizedImage.getWidth()));
				}
			}
		});
		btnUpdateSize.setVisible(false);

		// Checkbox for constrain proportions
		final JCheckBox chkConstrain = new JCheckBox("Constrian Proportions");
		chkConstrain
				.setToolTipText("Check this to keep width/height ratio so the image doesn't look stretched.");
		chkConstrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chkConstrain.isSelected()) {
					btnUpdateSize.setVisible(true);
					if (image.getWidth() < image.getHeight()
							|| (image.getWidth() == image.getHeight())) {
						txtHeight.setEnabled(false);
					} else if (image.getHeight() < image.getWidth()) {
						txtWidth.setEnabled(false);
					}
				} else {
					btnUpdateSize.setVisible(false);
					txtWidth.setEnabled(true);
					txtHeight.setEnabled(true);
				}
			}
		});
		panel.add(chkConstrain);
		panel.add(btnUpdateSize);

		// Show dialog box
		Object[] resizeButtons = { "Resize", "Cancel" };
		int result = JOptionPane.showOptionDialog(null, panel, "Resize Image",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				resizeButtons, resizeButtons[0]);

		// Resize button pressed
		if (result == 0) {
			// TODO: Make sure user has entered numbers > 0
			int width = Integer.parseInt(txtWidth.getText());
			int height = Integer.parseInt(txtHeight.getText());

			BufferedImage resizedImage = null;

			if (chkConstrain.isSelected()
					&& (image.getWidth() < image.getHeight())) {
				resizedImage = MemeMaker.setupImageContainer.getResizedImage(
						width, height,
						EditableImagePanel.RESIZE_CONSTRAIN_WIDTH);
			} else if (chkConstrain.isSelected()
					&& (image.getHeight() < image.getWidth())) {
				resizedImage = MemeMaker.setupImageContainer.getResizedImage(
						width, height,
						EditableImagePanel.RESIZE_CONSTRAIN_HEIGHT);
			} else if (chkConstrain.isSelected()
					&& (image.getWidth() == image.getHeight())) {
				resizedImage = MemeMaker.setupImageContainer.getResizedImage(
						width, height,
						EditableImagePanel.RESIZE_CONSTRAIN_PROPORTIONS);
			} else {
				resizedImage = MemeMaker.setupImageContainer.getResizedImage(
						width, height, EditableImagePanel.RESIZE_FIT_EXACT);
			}

			if (!MemeMaker.meetsMaxImageSizeRequirements(resizedImage)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Resized image is too large. The width and height must be between 400 and 600 pixels.",
								"Image Too Big", JOptionPane.ERROR_MESSAGE);
			} else if (!MemeMaker.meetsMinImageSizeRequirements(resizedImage)) {
				JOptionPane
						.showMessageDialog(
								null,
								"The resized image is too small. Width and height must be at least 400 pixels.",
								"Image Too Small", JOptionPane.ERROR_MESSAGE);
			} else {
				boolean confirm = MemeMaker
						.showImagePreviewConfirmDialog(resizedImage);
				if (confirm) {
					MemeMaker.showEditScreen(resizedImage);
				}
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
