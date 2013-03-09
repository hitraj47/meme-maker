import java.awt.CardLayout;

import javax.swing.JFrame;


public class MemeMakerFrame extends JFrame {

	public MemeMakerFrame() {
		super("Meme Maker");
		setLayout(new CardLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}

}
