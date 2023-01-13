package outdoor;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.*;

import processing.core.PVector;
import util.Util;

/*
 * This class implements the method for movement and graphic of snow flake
 */
public class SnowFlake {
	private PVector pos;
	private int s, count;
	
	public SnowFlake() {
		pos = new PVector(Util.random(385, 1050), Util.random(-600, -10));
		s = (int) Util.random(15, 20);
		count = 0;
	}

	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(pos.x, pos.y);
		drawSnow(g2, 0, 0, s, 2);
		
		g2.setTransform(at);
	
	}
	
	 
	
	public void drawSnow(Graphics2D g2, int x, int y, int size, int level) {
		g2.setStroke(new BasicStroke(1));
		g2.setColor(new Color(36, 123, 160));
	    for (int i = 0; i < 360; i += 60) {
	        double ang = Math.toRadians(i);
	        
	        int x2 = (int) (x + Math.cos(ang) * size);
	        int y2 = (int) (y + Math.sin(ang) * size);
	        
	        g2.drawLine(x, y, x2, y2);
	        
	        if (level > 0) {
	            drawSnow(g2, x2, y2, size/3, level-1);
	        }
	    }
	}



	public void move() {
		pos.y += s+4;
		count ++;
		if (count < 43 && pos.y> 330) {
			pos = new PVector(Util.random(385, 1050), Util.random(-50, -20));
			s = (int) Util.random(15, 20);
		}
		
	}

}

