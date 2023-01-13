package indoor;

import util.ImageLoader;

/*
 * This class implements the method for the paper on the shelf
 */
public class Paper extends BaseButton{

	public Paper(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/paperStack.png");
	}
	

	public void setPos(int x, int y) {
		xPos = x;
		yPos = y;
		
	}

}
