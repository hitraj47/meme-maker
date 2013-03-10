import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {

	private BufferedImage image;

    public ImagePanel() {
    }
    
    public ImagePanel(File file) {
    	setImage(file);
    }
    
    public void setImage(File file) {
    	try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			System.err.println("Could not create image panel with file: " + file.getAbsolutePath());
		}
    }
    
    public void setImage(String fileLocation) {
    	setImage(new File(fileLocation));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}
