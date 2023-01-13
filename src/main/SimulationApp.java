package main;

import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;
/*
 * This is a class used to create a windows frame for the panel
 */
public class SimulationApp extends JFrame {

	public SimulationApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 00);
		Container contentPane = getContentPane();
		SimulationPanel tp = new SimulationPanel(this);
		contentPane.add(tp);

		this.pack();
		this.setVisible(true);	
	}
	
	public static void main(String[] args) {
		new SimulationApp("Flower Bouquet");
	}

}
