package flowerPot;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

/*
 * This class implements the method to implement the methods 
 * to show the graphic after adding the fertilizer to pot
 */
public class AfterFert extends PotDecorator{

	public AfterFert(PotChanges p) {
		super(p);
		img = ImageLoader.loadImage("assets/soil1.png");
	}
	
	@Override
	public void draw(Graphics2D g2) {
		afterFert(g2);
		super.draw(g2);
		
	}
	
	private void afterFert(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale,scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight(), null);
		g2.setTransform(at);
	}
	
}
