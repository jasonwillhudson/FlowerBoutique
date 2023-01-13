package indoor;

import util.ImageLoader;

/*
 * This class implements the method for the toolbox on the shelf
 */
public class ToolBox extends BaseButton{

	public ToolBox(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/toolbox.png");
	}
	
	//check if tool box hits the window
	public boolean hit() {
		return (xPos >374 && xPos <1055 && yPos < 327 && yPos>41);
	}
	

	public void setPos(int x, int y) {
		xPos = x;
		yPos = y;
		
	}

}

