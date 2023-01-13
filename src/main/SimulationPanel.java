package main;
import static util.ImageLoader.loadImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import javax.swing.Timer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import factoryPattern.ObjectConcreteFactory;
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
import indoor.Butt;
import indoor.Flower;
import indoor.Indoor;
import indoor.Intro;
import indoor.Paper;
import indoor.Tie;
import indoor.ToolBox;
import outdoor.Fertilizer;
import outdoor.Outdoor;
import outdoor.Scissor;
import outdoor.Seed;
import outdoor.Smoke;
import outdoor.SnowFlake;
import outdoor.Stuffs;
import outdoor.Water;
import processing.core.PVector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageLoader;
import util.MinimHelper;

/*
 * This is a class to create the interactions depending on user input
 * 
 * ECOs implemented:
 * 1. sophisticated complex shapes and are sensible to the app (snowflakes, clouds)
 * 2. meaningful seasonal change (snowman melted, grass changes rainbow appear, sunrise)
 * 3. preferred decorator pattern features (human grab things)
 * 4. sensible FSM based intelligent character (in Human class)
 * 5. interactions involve with a human avatar 
 * 6. all images used are self-created with good quality  
 * 7. sophisticated micro-animations for character (human walking, grab stuffs)
 */
public class SimulationPanel extends JPanel implements ActionListener{
	JFrame cFrame;
	Text text;
	
	public static int W_WIDTH = 2388/2 - 100;
	public static int W_HEIGHT = 1688/2 - 100;
	
	private ObjectConcreteFactory obj;
	private boolean isMouseReleased;
	
	private int state = -2;
	private int counter = 0;

	// variables for holding mouse position
	private double mouseX;
	private double mouseY;
	
	private Minim minim;
	private Timer timer;
	
	//Indoor
	private Intro intro;
	private Butt button;
	private Indoor indoor;
	private ToolBox toolbox;
	private Paper paper;
	private Flower flower;
	private Tie tie;
	private SimpleHuman human;
	private PotChanges pot;
	
	//Outdoor
	private Outdoor outdoor;
	private Smoke smoke;
	private ArrayList<SnowFlake> snow = new ArrayList<SnowFlake>();
	private ArrayList<Stuffs> stuffs = new ArrayList<Stuffs>();
	
	//musics
	private AudioPlayer bkmusic;
	private AudioPlayer click;
	private AudioPlayer paperWrap;
	private AudioPlayer enterdoor;
	private AudioPlayer applause;
	private AudioPlayer pickup;
	private AudioPlayer scissor;
	private AudioPlayer soil;
	private AudioPlayer spring;
	private AudioPlayer tool;
	private AudioPlayer winter;
	private AudioPlayer woter;
	private AudioPlayer walk;
	
	SimulationPanel(JFrame frame) {
		this.setBackground(Color.black);
		cFrame = frame;
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
		obj = new ObjectConcreteFactory();
		//intro and ending
		intro = obj.createIntro("base");
		button = obj.createButton("base");
		text = obj.createText("base");
		
		//Indoor
		indoor = obj.createIndoor("base");
		flower = obj.createFlower("base");
		paper = (Paper) obj.createShelfStuffs("paper");
		tie = (Tie) obj.createShelfStuffs("tie");
		toolbox =(ToolBox) obj.createShelfStuffs("toolbox");
		
		//outdoor
		outdoor = obj.createOutdoor("base");
		smoke = new Smoke();
		for(int i = 0; i< 16; i++) {
			snow.add(obj.createSnowFlake("base"));
		}
		
		stuffs.add(obj.createOutdoorStuffs("fertilizer"));
		stuffs.add(obj.createOutdoorStuffs("seed"));
		stuffs.add(obj.createOutdoorStuffs("water"));
		stuffs.add(obj.createOutdoorStuffs("scissor"));
		
		//Musics
		minim = new Minim(new MinimHelper());
		bkmusic = minim.loadFile("bg.mp3");
		click = minim.loadFile("tap.mp3");
		paperWrap = minim.loadFile("paperWrap.mp3");
		enterdoor = minim.loadFile("enterdoor.mp3");
		applause = minim.loadFile("applause.mp3");
		pickup= minim.loadFile("pickup.mp3");
		scissor= minim.loadFile("scissor.mp3");
		soil= minim.loadFile("soil.mp3");
		spring= minim.loadFile("spring.mp3");
		tool= minim.loadFile("toolbox.mp3");
		winter= minim.loadFile("winter.mp3");
		woter= minim.loadFile("water.mp3");
		walk = minim.loadFile("walk.mp3");
		
		//Decorator Pattern
		human = obj.createHuman("base");
		pot = obj.createPot("base");
		
		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseMotionListener());
		addKeyListener(new MyKeyboardListener());
		setFocusable(true);
		
		timer = new Timer(30, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.setBackground(new Color(112, 193, 179));
		//start
		if (state == -2 || state == 99) {
			if(!bkmusic.isLooping()) bkmusic.loop();
			intro.draw(g2);
			button.drawButton(g2); 
		
			//winter to spring
		}else if(state == -1) {
			outdoor.drawOutdoor(g2);
			human.setState(0);
			human.draw(g2);
			pot.draw(g2);
			if(!smoke.isSpring()) { 
				for(SnowFlake s: snow)s.draw(g2);
				smoke.drawSmoke(g2);
			} else {
				state = 0;
				outdoor.addRainbow();
				human.setState(3);
				winter.pause();
				spring.loop();
			}
			indoor.drawIndoor(g2);
			paper.drawButton(g2);
			toolbox.drawButton(g2);
			tie.drawButton(g2);
			text.draw(g2, "Waiting for spring to come ...");
		
			//move toolbox to garden
		}else if(state == 0) {
			outdoor.drawOutdoor(g2);
			pot.draw(g2);
			human.draw(g2);
			indoor.drawIndoor(g2);
			paper.drawButton(g2);
			tie.drawButton(g2);
			toolbox.drawButton(g2);
			text.draw(g2, "Spring has arrived \nNow drag tool box into window\n to add tools in garden");
		
			//gardening interaction drawings
		}else if(state >=1 && state <= 9) {
			outdoor.drawOutdoor(g2);
			for(Stuffs s: stuffs) s.draw(g2);
			pot.draw(g2);
			human.draw(g2);
			indoor.drawIndoor(g2);
			paper.drawButton(g2);
			tie.drawButton(g2);
			toolbox.drawButton(g2);
			
			if (state == 1) text.draw(g2, "Now pressing <- or -> \nmove the human to fertilizer bag\npressing SPACE to pick up fertilizer");
			else if (state == 2) text.draw(g2, "Now pressing <- or -> \nmove the human to pot\npressing SPACE to add fertilizer");
			else if (state == 3) text.draw(g2, "Now pressing <- or -> \nmove the human to seed\npressing SPACE to pick up seed");
			else if (state == 4) text.draw(g2, "Now pressing <- or -> \nmove the human to pot\npressing SPACE to add seed");
			else if (state == 5) text.draw(g2, "Now pressing <- or -> \nmove the human to water can\npressing SPACE to pick up water can");
			else if (state == 6) text.draw(g2, "Now pressing <- or -> \nmove the human to pot\npressing SPACE to add water");
			else if (state == 7) text.draw(g2, "Now pressing <- or -> \nmove the human to scissor\npressing SPACE to pick up scissor");
			else if (state == 8) text.draw(g2, "Now pressing <- or -> \nmove the human to pot\npressing SPACE to cut off flowers");
			else if (state == 9) text.draw(g2, "Now pressing <- \nbring the flowers into house\n(left edge of screen)");
			
			//indoor drawings
		}else if(state == 10 || state == 11 || state == 12) {
			outdoor.addRect();
			outdoor.drawOutdoor(g2);
			indoor.drawIndoor(g2);
			flower.drawButton(g2);
			paper.drawButton(g2);
			tie.drawButton(g2);
			toolbox.drawButton(g2);
			
			text.setPos(730, 150);
			if(state == 10)
				text.draw(g2, "Use Mouse to drag the paper \nonto flowers \nand release the mouse \nto put it on");
			else if (state == 11)
				text.draw(g2, "Click the flowers to \nwrap paper around");
			else if (state == 12)
				text.draw(g2, "Use Mouse drag the tape \nonto flowers \nand release the mouse to \nadd some decorations");
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isMouseReleased) {
			paper.resetPos();
			tie.resetPos();
			toolbox.resetPos();

		}//reset position if release mouse
		
		if(!smoke.isSpring() && state!=-2) 
			for(SnowFlake s: snow) s.move(); //snow move only in winter
		
		
		
		if(human.getPos().x < 388 && state == 9) {
			state = 10;
			enterdoor.play(0);
		}
		if(state >=0 && state <= 9 )human.move(); //human move  when outdoor
		
		
		repaint();
	}
	
	
	
	//-----------------------------Mouse Actions-----------------------------------------
	private class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			//System.out.println(e.getX() + "  ,  "+e.getY());
			isMouseReleased = false;
			//click on start
			if(state == -2 && button.clicked(e.getX(), e.getY())) {
				state = -1;
				click.play(0);
				winter.loop();
			}
			//click on flower
			if(state == 11 && flower.clicked(e.getX(), e.getY())) {
				state = 12;
				flower.setFlowerImg(2);
				paperWrap.play(0);
			}
			
			//click on restart
			if(state == 99 && button.clicked(e.getX(), e.getY())) {
				click.play(0);
				applause.pause();
				cFrame.dispose();
				bkmusic.pause();
				cFrame = new SimulationApp("Flower Bouquet");
				
			}
		}
		
		public void mousePressed(MouseEvent e) {
			isMouseReleased = false;
		}
		
		public void mouseReleased(MouseEvent e) {
			isMouseReleased = true;
			counter = 0;
			
			if(state == 0 && toolbox.hit()) {
				state = 1;
				tool.play(0);
			}
			
			if(flower.hit(paper) && state == 10) {
				state = 11;
				flower.setFlowerImg(1);
				paperWrap.play(0);
			}
		
			if( flower.hit(tie) && state == 12) {
				state = 99;
				button.setPos(W_WIDTH/2+200, W_HEIGHT/2 + 220);
				intro.setImg(1);
				button.setImg(1);
				paperWrap.play(0);
				applause.loop();
				spring.pause();
			}
			
			
		}
		
	}
	
	private class MyMouseMotionListener extends MouseAdapter {
		public void mouseDragged(MouseEvent e){
			if(state == 0 && toolbox.clicked(e.getX(), e.getY())) {
				toolbox.setPos(e.getX(), e.getY());
				if(counter == 0) click.play(0);
				counter++;
			}
			
			if(state == 10 && paper.clicked(e.getX(), e.getY())) {
				paper.setPos(e.getX(), e.getY());
				if(counter == 0)click.play(0);
				counter++;
			}
			
			if(state == 12 && tie.clicked(e.getX(), e.getY())) {
				tie.setPos(e.getX(), e.getY());
				if(counter == 0)click.play(0);
				counter++;
			}
			
			
		}
	}
	
	//-----------------------------KeyBoard Actions-----------------------------------------
	private class MyKeyboardListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			//control human moving
			if(state >= 0 && e.getKeyCode() == KeyEvent.VK_RIGHT) {
				human.setDir(1);
				if(!walk.isPlaying()) walk.play();
			}
			else if(state >= 0 && e.getKeyCode() == KeyEvent.VK_LEFT) {
				human.setDir(-1);
				if(!walk.isPlaying()) walk.play();
			}
			
			
			//pick up stuffs
			
			//pick up fertilizer
			boolean isCollide = false;
			
			try {
				if(state>=1 && state<=7) 
					isCollide = stuffs.get(0).isCollide(human.getPos().x);
			} catch (Exception Stuffs) {
				System.out.println("Stuffs arraylist is empty at state " + state);
				}
			
			if (state == 1 && isCollide && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 2;
				human = obj.addHumanDecoration("addFertilizer", human);
				human.setState(1);
				stuffs.remove(0);
				pickup.play(0);
			
				//pick up seed
			}else if (state == 3 && isCollide && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 4;
				human = obj.addHumanDecoration("addSeed", human);
				human.setState(1);
				stuffs.remove(0);
				pickup.play(0);
			
				//pick up water
			}else if (state == 5 && isCollide && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 6;
				human = obj.addHumanDecoration("addWater", human);
				human.setState(1);
				stuffs.remove(0);
				pickup.play(0);
			}
			//pick up scissor
			else if (state == 7 && isCollide && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 8;
				human = obj.addHumanDecoration("addScissor", human);
				human.setState(1);
				stuffs.remove(0);
				pickup.play(0);
			}
			
			
			//drop stuffs
			
			//drop fertilizer
			if (state == 2 && pot.isCollide(human.getPos().x) && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 3;
				PVector po = human.getPos();
				PVector sp = human.getSpeed();
				human = obj.createHuman("base");
				human.setPoSpeed(po, sp);
				pot = obj.addPotDecoration("addFertilizer", pot);
				soil.play(0);
			
				//drop seed
			}else if (state == 4 && pot.isCollide(human.getPos().x) && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 5;
				PVector po = human.getPos();
				PVector sp = human.getSpeed();
				human = obj.createHuman("base");
				human.setPoSpeed(po, sp);
				pot = obj.addPotDecoration("addSeed", pot);
				soil.play(0);
				
				//drop water
			}else if (state == 6 && pot.isCollide(human.getPos().x) && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 7;
				PVector po = human.getPos();
				PVector sp = human.getSpeed();
				human = obj.createHuman("base");
				human.setPoSpeed(po, sp);
				pot = obj.createPot("base");
				pot = obj.addPotDecoration("addWater", pot);
				woter.play(0);
			
				//drop scissor
			}else if (state == 8 && pot.isCollide(human.getPos().x) && e.getKeyCode() == KeyEvent.VK_SPACE) {
				state = 9;
				PVector po = human.getPos();
				PVector sp = human.getSpeed();
				human = new Human();
				human.setPoSpeed(po, sp);
				human = obj.addHumanDecoration("addFlower", human);
				pot = obj.createPot("base");
				pot = obj.addPotDecoration("addScissor", pot);
				human.setState(1);
				scissor.play(0);
			}
			
		}
		
		public void keyReleased(KeyEvent e) {
			//human stop moving
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
				human.stopMoving();
			}
			
			walk.pause();
			walk.rewind();
		}
	}
	

}
