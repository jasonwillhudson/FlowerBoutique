package indoor;

import util.ImageLoader;

/*
 * This is a class implementing the methods for start and restart buttons
 */
public class Butt extends BaseButton{

	public Butt(double x, double y, double s) {
		super(x, y, s);
		img =  ImageLoader.loadImage("assets/start.png");
	}
	
	public void setImg(int state) {
		if (state == 1) img = ImageLoader.loadImage("assets/endb.png");
	}
	
	public void setPos(double x, double y) {
		xPos = x;
		yPos = y;
	}

}
