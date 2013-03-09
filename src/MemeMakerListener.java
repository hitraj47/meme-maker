import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MemeMakerListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == MemeMaker.menuFileExit) {
			exit();
		} else if (e.getSource() == MemeMaker.menuHelpAbout) {
			showAboutDialog();
		}
	}

	private void showAboutDialog() {
		String message = "Version 0.1\n\n"
				+ "Created by Raj Ramsaroop and Greg Westerfield, Jr.";
		String title = "About";
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);		
	}

	private void exit() {
		// TODO: check if user has file loaded, if so, ask about exiting,
		// otherwise just exit
		// for now just exit the program
		System.exit(0);
	}

}
