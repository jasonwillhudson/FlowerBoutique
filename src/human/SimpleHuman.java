package human;

import java.awt.Graphics2D;

import processing.core.PVector;

/*
 * This is the interface claims the abstract methods for a human object
 */
public interface SimpleHuman {
	public void move();
	public void draw(Graphics2D g2);
	public void setDir(int d);
	public void stopMoving();
	public PVector getPos();
	public PVector getSpeed();
	public void setPoSpeed(PVector p, PVector s);
	public void setState(int s);
}
