package outdoor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.SimulationPanel;
import util.ImageLoader;

/*
 * This class implements the method for graphic of out door scene depending on season
 */
public class Outdoor {
	private BufferedImage img, img2, img3;
	private int timer;
	private boolean addRainbow = false;
	private boolean addRect = false;
	private int xPos, yPos,speed;

	public Outdoor() {
		img = ImageLoader.loadImage("assets/fence1.png");
		img2 = ImageLoader.loadImage("assets/rainbow.png");
		img3 = ImageLoader.loadImage("assets/snow1.png");
		timer = 0;
		xPos = 825;
		yPos = 326;
		speed = 2;
	}

	public void drawOutdoor(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		
		g2.scale(1, 1);
		if (addRainbow) {
			g2.drawImage(img2, 0, 0, SimulationPanel.W_WIDTH, SimulationPanel.W_HEIGHT, null);
			g2.setColor(new Color(227, 174, 78));
			if(yPos>73) yPos -= 4;
			else {
				if(xPos<=775 || xPos >=825) speed*= -1;
				xPos += speed;
			}
			g2.fillOval(xPos, yPos, 80, 80);
		}
		g2.drawImage(img, 0, 0, SimulationPanel.W_WIDTH, SimulationPanel.W_HEIGHT, null);
		if(timer <= 40+90) timer ++;
		if (timer <= 130) {
			setImage();
			g2.drawImage(img3, 0, 0, SimulationPanel.W_WIDTH, SimulationPanel.W_HEIGHT, null);
		}
		
		g2.setColor(new Color(112, 193, 179, 190));
		if (addRect) g2.fillRect(0, 0, SimulationPanel.W_WIDTH, SimulationPanel.W_HEIGHT);
		g2.setTransform(at);
		
	}
	
	public void addRainbow() {
		addRainbow = true;
	}
	
	public void addRect() {
		addRect = true;
	}
	
	private void setImage() {
		if(timer == 40+30) 
			img3 = ImageLoader.loadImage("assets/snow2.png");
			
		else if(timer == 40+60) {
			img3 = ImageLoader.loadImage("assets/snow3.png");
			img = ImageLoader.loadImage("assets/fence2.png");
		}
		else if(timer == 40+90) {
			img3 = ImageLoader.loadImage("assets/indoor.png");
			img = ImageLoader.loadImage("assets/fence3.png");
		}
	}

}
