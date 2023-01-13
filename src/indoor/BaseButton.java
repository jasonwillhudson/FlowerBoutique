package indoor;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/*
 * This is a abstract superclass implements and declare the common methods
 *  and field for it's subclass (Things on the Shelf)
 */
public abstract class BaseButton {
	protected double xPos;
	protected double yPos;
	protected double scale;
	protected BufferedImage img;
	protected double tempX;
	protected double tempY;
	
	public BaseButton(double x, double y, double s) {
		xPos = x;
		yPos = y;
		tempX = x;
		tempY = y;
		scale = s;
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if(x > (xPos - ((double) img.getWidth())/2*scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	
	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}

public double getxPos() {
	return xPos;
}

public double getyPos() {
	return yPos;
}
	//put the object back to it's initial position
	public void resetPos() {
		xPos = tempX;
		yPos = tempY;
	}
}
