package indoor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;

/*
 * This class implements the method to implement the methods 
 * to show the graphic during the interaction with flowers on the table
 */

public class Flower extends BaseButton{
	private final static int initial = 0;
	private final static int paperOn = 1;
	private final static int wrapped = 2;
	private final static int tapeOn = 3;
	
	public Flower(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/flowers.png");
	}
	
	
	public void setFlowerImg(int flowerState) {

	    if (flowerState == initial)

	        img = ImageLoader.loadImage("assets/flowers.png");  //initial

	    else if (flowerState == paperOn)

	        img = ImageLoader.loadImage("assets/flowersOnPaper.png");   //on paper

	    else if (flowerState == wrapped)

	        img = ImageLoader.loadImage("assets/flowersWrapped.png"); //wrapped
	    
	    else if (flowerState == tapeOn)

	        img = ImageLoader.loadImage("assets/flowersFinal.png"); //tied up

	}
	
	
	public boolean hit(BaseButton p) {
		return Math.abs(xPos - p.getxPos()) <170 && Math.abs(yPos - p.getyPos()) <85;
	}
	
	@Override
	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.rotate(Math.toRadians(-90));
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
}
