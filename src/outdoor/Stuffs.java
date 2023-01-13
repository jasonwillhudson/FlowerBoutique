package outdoor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
/*
 * This is a abstract class implement and declare the common methods or field for it's subclass
 * It declares the methods for the stuffs on the ground in garden
 */
public abstract class Stuffs {
	protected PVector pos;
	protected BufferedImage img;
	protected double scale;
	public Stuffs(float x, float y) {
		this.pos = new PVector();
		pos.x = x;
		pos.y = y;
	}
	
	public abstract void draw(Graphics2D g2);
	
	public boolean isCollide(float x) {
		boolean isC = false;
		if(x > (pos.x - ((double) img.getWidth())/2*scale) && x < (pos.x + ((double) img.getWidth())/2*scale)) 
			isC = true;
		
		return isC;
	}
	
}
