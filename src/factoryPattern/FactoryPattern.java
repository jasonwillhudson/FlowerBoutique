package factoryPattern;

import ddf.minim.Minim;
import flowerPot.FlowerPot;
import flowerPot.PotChanges;
import human.Human;
import human.SimpleHuman;
import indoor.BaseButton;
import indoor.Butt;
import indoor.Flower;
import indoor.Indoor;
import indoor.Intro;
import indoor.Paper;
import indoor.Tie;
import indoor.ToolBox;
import main.Text;
import outdoor.Fertilizer;
import outdoor.Outdoor;
import outdoor.Scissor;
import outdoor.Seed;
import outdoor.Smoke;
import outdoor.SnowFlake;
import outdoor.Stuffs;
import outdoor.Water;
import util.MinimHelper;
/*
 * This class is a class making abstract methods for creating the shapes in factory
 */
public abstract class FactoryPattern {
	public abstract SimpleHuman createHuman(String typr);
	public abstract Intro createIntro(String typr);
	public abstract Butt createButton(String typr);
	public abstract Text createText(String typr);
	public abstract Indoor createIndoor(String typr);
	public abstract Outdoor createOutdoor(String typr);
	public abstract Flower createFlower(String typr);
	public abstract BaseButton createShelfStuffs(String typr);
	public abstract SnowFlake createSnowFlake(String typr);
	public abstract Smoke createCloud(String typr);
	public abstract Stuffs createOutdoorStuffs(String typr);
	public abstract PotChanges createPot(String typr);
	public abstract SimpleHuman addHumanDecoration(String typr, SimpleHuman h);
	public abstract PotChanges addPotDecoration(String typr, PotChanges p);

}
