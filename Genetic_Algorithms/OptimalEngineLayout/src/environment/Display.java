package environment;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	private int width, height;
	private String title;

	public Display(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		
		createDisplay();
	}
	
	private void createDisplay(){
		frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setSize(width, height);
		canvas.setPreferredSize(new Dimension (width,height));
		canvas.setMaximumSize(new Dimension (width,height));
		canvas.setMinimumSize(new Dimension (width,height));
		
		frame.add(canvas);
		frame.pack();
	}
	
	public Canvas getCanvas(){
		return canvas;
	}

}
