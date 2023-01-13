package flowerPot;

import java.awt.Graphics2D;

/*
 * this is a interface of the pot claim the methods for flower pot
 */
public interface PotChanges {
	public void draw(Graphics2D g2);
	public boolean isCollide(float x);
}
