package flowerPot;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

/*
 * This class implements the method to implement the methods 
 * to show the graphic of flowers growing up as a animation
 */
public class GrowUp extends PotDecorator{
	private int t, state;
	private BufferedImage img2, img3;
	
	public GrowUp(PotChanges p) {
		super(p);
		img = ImageLoader.loadImage("assets/rose1.png");
		img2 = ImageLoader.loadImage("assets/rose2.png");
		img3 = ImageLoader.loadImage("assets/rose3.png");
		t = 0;
		state = 0;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		flower(g2);
		super.draw(g2);
		if(t <= 62) t++;
		if(t==30) state = 1;
		else if(t == 60) state = 2;
		setImage();
			
	}
	
	//draw the flowers
	private void flower(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale,scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight(), null);
		g2.setTransform(at);
	}
	
	private void setImage() {
		if(state == 1) img = img2;
		else if(state == 2) img = img3;
	}
	
}
