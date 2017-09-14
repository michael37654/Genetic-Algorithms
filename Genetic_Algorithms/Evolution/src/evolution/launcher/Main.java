package evolution.launcher;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import evolution.imaging.Display;
import evolution.imaging.Image_Util;

public class Main {

	public static void main(String[] args) {

		//Display display = new Display("Evolution", Variables.displaySize, Variables.displaySize);
		
		int[] array = UtilMethods.insertionSortLTH(new int[] {5, 8, 3, 9, 1, 4, 6, 6, 9, 0, 1, -1});
		for(int i : array){
			System.out.print(i + " ");
		}
		
		
	}

}
