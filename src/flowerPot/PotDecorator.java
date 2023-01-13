package flowerPot;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/*
 * This is the super class pull up the common field and implementation for it's subclass
 * It implements the methods to add decoration onto flower pot
 */
public class PotDecorator implements PotChanges{
	protected BufferedImage img;
	protected  int xPos, yPos;
	protected  double scale;
	private PotChanges pot;
	
	public PotDecorator(PotChanges p) {
		scale = 0.1;
		xPos = 604;
		yPos = 340;
		pot = p;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		pot.draw(g2);
		
	}

	@Override
	public boolean isCollide(float x) {
		// TODO Auto-generated method stub
		return pot.isCollide(x);
	}

}
