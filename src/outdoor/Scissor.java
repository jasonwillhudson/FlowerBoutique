package outdoor;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;
/*
 * This class implements the method for graphic of scissor
 */
public class Scissor extends Stuffs{

	public Scissor(float x, float y) {
		super(x, y);
		img = ImageLoader.loadImage("assets/scissor.png");
		scale = 0.04;
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(pos.x, pos.y);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight(), null);
		g2.setTransform(at);
		
	}

}
