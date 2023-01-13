package flowerPot;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

/*
 * This class implements the method to draw a basic flower pot without any decoration
 */
public class FlowerPot implements PotChanges{
	
	private BufferedImage img;
	private int xPos, yPos;
	private double scale;
	
	public FlowerPot() {
		img = ImageLoader.loadImage("assets/flowerpot.png");
		scale = 0.1;
		xPos = 604;
		yPos = 340;
		
	}

	@Override
	public void draw(Graphics2D g2) {
	
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale,scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight(), null);
		g2.setTransform(at);
		
		
		
	}

	//check if human is collided to the pot
	@Override
	public boolean isCollide(float x) {
		return (x > (xPos - ((double) img.getWidth())/2*scale) && x < (xPos + ((double) img.getWidth())/2*scale));
	}

}
