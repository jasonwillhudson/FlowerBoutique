package human;

import java.awt.Graphics2D;

import processing.core.PVector;

/*
 * This is a super class implements the methods in interface to add on decorations
 */
public class HumanDecorator implements SimpleHuman{
	
	private SimpleHuman humanFigure;
	protected PVector pos, speed;
	
	
	public HumanDecorator(SimpleHuman h) {
		this.humanFigure = h;
		this.pos = h.getPos();
		this.speed = h.getSpeed();
	}

	@Override
	public void move() {
		humanFigure.move();
		
	}

	@Override
	public void draw(Graphics2D g2) {
		humanFigure.draw(g2);
		
	}

	@Override
	public void setDir(int d) {
		humanFigure.setDir(d);
		
	}

	@Override
	public void stopMoving() {
		humanFigure.stopMoving();
		
	}

	@Override
	public PVector getPos() {
		return pos;
	}

	@Override
	public PVector getSpeed() {
		return speed;
	}

	@Override
	public void setPoSpeed(PVector p, PVector s) {
		humanFigure.setPoSpeed(p, s);
		
	}

	@Override
	public void setState(int s) {
		humanFigure.setState(s);
		
	}
	

}
