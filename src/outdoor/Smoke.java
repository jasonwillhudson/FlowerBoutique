package outdoor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import processing.core.PApplet;
import static util.Util.*;
/*
 * This class implements the method for graphic and movement of smoking clouds
 */
public class Smoke {
	private float xStart;
	private float xSeed;
	private float ySeed;
	private PApplet pa;
	private float trans = 135;
	private float diam = 125;
	private float col = 160;
	private int yPos = 328/3;
	
	public  Smoke() {
		xStart = random(10);
		xSeed = xStart;
		pa = new PApplet();
	}

	public boolean isSpring() {
		return yPos <= 40;
	}
	
	
	public void drawSmoke(Graphics2D g2) {
		float noiseFactor;
		if(trans>0) trans -= 2;
		if(diam>0) diam += 1;
		yPos --;
		
		for (int y = 40; y <= yPos; y += 5) {
			ySeed += 0.1;
			xSeed = xStart;
			for (int x = 373; x <= 1055; x += 5) {
				xSeed += 0.09;
				noiseFactor = pa.noise(xSeed, ySeed);
				AffineTransform at = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor * radians(360));
				float diameter = noiseFactor * 155;
				int grey = (int) (col + (noiseFactor * (255 - col)));
				int alph = (int) (0 + (noiseFactor * trans));
				g2.setColor(new Color(grey, grey, grey, alph));
				g2.fill(new Ellipse2D.Float(-diameter/2, -diameter/4, diameter, diameter/2));
				g2.setTransform(at);
			}
		}
	}

}

