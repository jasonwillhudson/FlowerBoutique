package human;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import processing.core.PVector;
/*
 * This is a class implements the methods of behaviour and graphics for a basic human
 */
public class Human implements SimpleHuman{
	private PVector pos, speed;
	private int timer, dir,state;
	private Color col;
	
	private Ellipse2D.Double head;
	private RoundRectangle2D.Double body;
	private RoundRectangle2D.Double arm;
	private RoundRectangle2D.Double leg;
	private double armspeed, legspeed, armangle, legangle;
	private boolean stopMoving = true;
	
	//FSM 
	private final static int frozen = 0;
	private final static int pickUp = 1;
	private final static int hold = 2;
	private final static int empty = 3;
	
	public Human() {
		this.pos = new PVector(400, 225);
		this.speed = new PVector(0,0);
		this.armangle = 0;
		this.legangle = 0;
		this.legspeed = 6;
		this.armspeed = 4;
		this.state = 3;
		this.dir = 1;
		this.col = Color.white;
		setAttributes();
	}
	
	private void setAttributes() {
		head = new Ellipse2D.Double(-20, -20, 40, 40);
		body =new RoundRectangle2D.Double();
		body.setRoundRect(-20, -40, 40, 75, 80, 40);
		
		arm =new RoundRectangle2D.Double();
		arm.setRoundRect(-5, -5, 10, 50, 10, 5);
		
		leg =new RoundRectangle2D.Double();
		leg.setRoundRect(-5, -5, 10, 70, 10, 5);
	}
	
	public void draw(Graphics2D g) {
		AffineTransform at = g.getTransform();
		if (state == frozen) col = new Color(173,216,230);
		else if (state != frozen) col = Color.white;
		
		g.translate(pos.x, pos.y);
		g.rotate(speed.heading());
		g.scale(0.7, 0.7);
		if(speed.x<0) g.scale(1, -1);
		
		AffineTransform xy = g.getTransform();
		
		g.setColor(col);
		g.fill(head);
		g.setColor(Color.black);
		g.draw(head);
		
		g.setTransform(xy);
		g.translate(0, 82);
		g.rotate(Math.toRadians(legangle));
		g.setColor(col);
		g.fill(leg);
		g.setColor(Color.black);
		g.draw(leg);
		
		g.setTransform(xy);
		g.translate(0, 62);
		g.setColor(col);
		g.fill(body);
		g.setColor(Color.black);
		g.draw(body);
		
		g.setTransform(xy);
		g.translate(0, 82);
		g.rotate(Math.toRadians(legangle) * -1);
		g.setColor(col);
		g.fill(leg);
		g.setColor(Color.black);
		g.draw(leg);
		
		g.setTransform(xy);
		g.translate(0, 42);
		g.rotate(Math.toRadians(armangle));
		g.setColor(col);
		g.fill(arm);
		g.setColor(Color.black);
		g.draw(arm);
		
		g.setTransform(at);
	}
	public void move() {
		if (pos.x > 1040) pos.x = 1040;
		if (pos.x < 388) pos.x = 388;
		
		//walking movements
		if(!stopMoving && pos.x <= 1040 && pos.x >= 388) {
			pos.add(speed);
			if(legangle >= 36 || legangle <= -36) legspeed *= -1;
	    	legangle += legspeed;
	    	
	    	if(state == empty) {
	    		if(armangle >= 24 || armangle <= -24) armspeed *= -1;
	    		armangle += armspeed;
	    		
	    	}
		}
		//when stop moving
		else if(stopMoving) {
			legangle = 0;
			if(state != pickUp && state != hold) armangle =0 ;
		}
		
		//pick up and holding stuffs animation
		if(state == pickUp) {
			if(armangle >= -89) armangle -= 20;
			else state = hold;
		}
		else if(state == hold) armangle = -90;
		
    	
	}
	
	public void setDir(int d) {
		speed.x = d * 10;
		dir = d;
		stopMoving = false;
	}
	
	public void stopMoving() {
		stopMoving = true;
	}

	@Override
	public PVector getPos() {
		// TODO Auto-generated method stub
		return pos;
	}
	
	public PVector getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}
	
	//set the position and speed
	public void setPoSpeed(PVector p, PVector s) {
		pos = p;
		speed = s;
	}
	
	public void setState(int s) {
		state = s;
	}
	

}
