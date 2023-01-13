package factoryPattern;

import flowerPot.AfterFert;
import flowerPot.AfterSeed;
import flowerPot.AfterWater;
import flowerPot.FlowerPot;
import flowerPot.GrowUp;
import flowerPot.PotChanges;
import human.HoldFertilizer;
import human.HoldFlower;
import human.HoldScissor;
import human.HoldSeed;
import human.HoldWater;
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
import main.SimulationPanel;
import main.Text;
import outdoor.Fertilizer;
import outdoor.Outdoor;
import outdoor.Scissor;
import outdoor.Seed;
import outdoor.Smoke;
import outdoor.SnowFlake;
import outdoor.Stuffs;
import outdoor.Water;

/*
 * This class implements the methods from abstract super class to create the concrete shapes 
 */
public class ObjectConcreteFactory extends FactoryPattern{

	@Override
	public SimpleHuman createHuman(String typr) {
		SimpleHuman shape = null;
		if(typr == "base") shape = new Human();
		return shape;
	}

	@Override
	public Intro createIntro(String typr) {
		Intro shape = null;
		if(typr == "base") shape = new Intro();
		return shape;
	}

	@Override
	public Butt createButton(String typr) {
		Butt shape = null;
		if(typr == "base") shape =new Butt(SimulationPanel.W_WIDTH/2,SimulationPanel.W_HEIGHT/2 + 260, .1);
		return shape;
	}

	@Override
	public Text createText(String typr) {
		Text shape = null;
		if(typr == "base") shape = new Text(680,530);
		return shape;
	}

	@Override
	public Indoor createIndoor(String typr) {
		Indoor shape = null;
		if(typr == "base") shape = new Indoor();
		return shape;
	}

	@Override
	public Flower createFlower(String typr) {
		Flower shape = null;
		if(typr == "base") shape = new Flower(600, 530, 0.2);
		return shape;
	}

	@Override
	public BaseButton createShelfStuffs(String typr) {
		BaseButton shape = null;
	
		if(typr == "paper") shape = new Paper(112, 128, 0.05);
		else if (typr == "toolbox") shape = new ToolBox(166, 240, 0.08);
		else if (typr == "tie") shape = new Tie(212, 128, 0.05);
		
		return shape;
	}

	@Override
	public SnowFlake createSnowFlake(String typr) {
		SnowFlake shape = null;
		if(typr == "base") shape = new SnowFlake();
		return shape;
	}

	@Override
	public Smoke createCloud(String typr) {
		Smoke shape = null;
		if(typr == "base") shape = new Smoke();
		return shape;
	}

	@Override
	public Stuffs createOutdoorStuffs(String typr) {

		Stuffs shape = null;
		
		if(typr == "fertilizer") shape = new Fertilizer(790, 328);
		else if (typr == "seed") shape =new Seed(860, 328);
		else if (typr == "water") shape = new Water(930, 328);
		else if (typr == "scissor") shape = new Scissor(1000, 328);
		
		return shape;
	}

	@Override
	public PotChanges createPot(String typr) {
		PotChanges shape = null;
		if(typr == "base") shape = new FlowerPot();
		return shape;
	}

	@Override
	public Outdoor createOutdoor(String typr) {
		Outdoor shape = null;
		if(typr == "base") shape = new Outdoor();
		return shape;
	}

	@Override
	public SimpleHuman addHumanDecoration(String typr, SimpleHuman h) {
		SimpleHuman shape = h;
		if(typr == "addFertilizer") shape = new HoldFertilizer(shape);
		else if(typr == "addSeed") shape = new HoldSeed(shape);
		else if(typr == "addWater") shape = new HoldWater(shape);
		else if(typr == "addScissor") shape = new HoldScissor(shape);
		else if(typr == "addFlower") shape = new HoldFlower(shape);
		return shape;
	}

	@Override
	public PotChanges addPotDecoration(String typr, PotChanges p) {
		PotChanges shape = p;
		if(typr == "addFertilizer") shape = new AfterFert(shape);
		else if(typr == "addSeed") shape = new AfterSeed(shape);
		else if(typr == "addWater") {
			shape = new AfterWater(shape);
			shape = new GrowUp(shape);
		}
		else if(typr == "addScissor") shape = new AfterWater(shape);

		return shape;
	}
}
