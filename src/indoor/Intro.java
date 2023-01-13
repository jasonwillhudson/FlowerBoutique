package indoor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.SimulationPanel;
import util.ImageLoader;
/*
 * This class implements the method for drawing introduction page
 */
public class Intro {
	private BufferedImage img;

	public Intro() {
		img = ImageLoader.loadImage("assets/main.png");
	}

	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		//g2.translate(-90, 0);
		g2.scale(1, 1);
		g2.drawImage(img, 0, 0, SimulationPanel.W_WIDTH, SimulationPanel.W_HEIGHT, null);
		
		g2.setTransform(at);
	}
	
	public void setImg(int state) {
		if (state == 1) img = ImageLoader.loadImage("assets/end.png");
	}
}
