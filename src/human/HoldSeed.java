package human;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;
/*
 * This class implements the method to implement the methods 
 * to show the graphic after adding the seed to human hand
 */
public class HoldSeed extends HumanDecorator{
	private BufferedImage img;
	
	public HoldSeed(SimpleHuman h) {
		super(h);
		img = ImageLoader.loadImage("assets/seed.png");
	}

	@Override
	public void draw(Graphics2D g2) {
		drawSeed(g2);
		super.draw(g2);
	}
	
	private void drawSeed(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(pos.x, pos.y);
		g2.rotate(speed.heading());
		if(speed.x<0) g2.scale(1,-1);
		g2.scale(0.04, 0.04);
		g2.drawImage(img, 500, 10, null);
		g2.setTransform(at);
		
	}
}
