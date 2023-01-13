package flowerPot;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

/*
 * This class implements the method to implement the methods 
 * to show the graphic after adding the water to pot
 */
public class AfterWater extends PotDecorator{
	
	public AfterWater(PotChanges p) {
		super(p);
		img = ImageLoader.loadImage("assets/soil2.png");

	}
	
	@Override
	public void draw(Graphics2D g2) {
		afterWater(g2);
		super.draw(g2);
		
	}
	
	private void afterWater(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale,scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight(), null);
		g2.setTransform(at);
	}

	
}