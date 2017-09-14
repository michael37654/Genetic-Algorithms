package evolution.imaging;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import evolution.launcher.Variables;

public class Display{
	
	private JFrame frame;
	private Canvas canvas;
	
	public Display(String name, int width, int height){
		
		frame = new JFrame();
		canvas = new Canvas();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setTitle(name);
		//frame.setBackground(Color.GREEN);
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(Variables.displaySizeMin, Variables.displaySizeMin));
		frame.setResizable(true);
		
		canvas.setSize(width, height);
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(Variables.displaySizeMin, Variables.displaySizeMin));
		canvas.setBackground(new Color(150,220,150));
		
		frame.add(canvas);
		frame.setVisible(true);
		canvas.setVisible(true);
		
		frame.pack();
		
	}
	
	public Dimension getDimension(){
		return frame.getSize();
	}
	
	public int getHeight(){
		return frame.getHeight();
	}
	
	public int getWidth(){
		return frame.getWidth();
	}
	
	public boolean isVisible(){
		return frame.isVisible();
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
}
