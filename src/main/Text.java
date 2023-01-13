package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
/*
 * This is a class implements the method of drawing text as instruction in the game
 */
public class Text {
	private int posX;
	private int posY;
	
	public Text(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public void draw(Graphics2D g, String st) {
		AffineTransform at = g.getTransform();
		g.translate(posX, posY);
		g.setColor(Color.black);
		String [] lines = st.split("\n"); //split the lines if \n detected
		Font f = new Font("Courier", Font.PLAIN, 30);
		g.setFont(f);
		FontMetrics metrics = g.getFontMetrics(f);
		
		int lineHeight = g.getFontMetrics(f).getHeight();
		for(int i = 0; i < lines.length; i ++){ //drawing each of splited lines
		    int yPos  = 0 + i * lineHeight;
		    String line = lines[i];
		    float textWidth = metrics.stringWidth(line);
		    g.drawString(line, -textWidth/2, yPos);	    
		}
		g.setTransform(at);
	}
	
	
	public void setPos(int x, int y) {
		posX =x;
		posY =y;
	}
}
