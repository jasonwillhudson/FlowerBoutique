package indoor;

import util.ImageLoader;

/*
 * This class implements the method for the tape on the shelf
 */
public class Tie extends BaseButton{

	public Tie(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/tape.png");
	}

	public void setPos(int x, int y) {
		xPos = x;
		yPos = y;
		
	}

}
