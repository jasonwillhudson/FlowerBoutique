
package human;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

/*
 * This class implements the method to implement the methods 
 * to show the graphic after adding the flower to human hand
 */
public class HoldFlower extends HumanDecorator{
	private BufferedImage img;
	
	public HoldFlower(SimpleHuman h) {
		super(h);
		img = ImageLoader.loadImage("assets/rose4.png");
	}

	@Override
	public void draw(Graphics2D g2) {
		drawFlower(g2);
		super.draw(g2);
	}
	
	private void drawFlower(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(pos.x, pos.y);
		g2.rotate(speed.heading());
		if(speed.x<0) g2.scale(1,-1);
		g2.scale(0.02, 0.02);
		g2.drawImage(img, 300, -150, null);
		g2.setTransform(at);
		
	}
}


