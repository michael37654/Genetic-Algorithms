package evolution.launcher;

import java.util.ArrayList;

public class GeneSequence {
	
	private double[] genes;
	
	public GeneSequence(){
		/*
		[0] Class (Mammel, Reptile, Insect, Arachned): 2
		[1] Speed: 10
		[2] Health: 10
		[3] Skin Type (Heigher #, Scaley/Rough Skin): 10
		[4] Teeth Length: 10
		[5] Intelligence: 10
		[6] Size: 10
		[7] Legs: 4
		[8] Red: 255
		[9] Green: 255
		[10] Blue: 255
		[11] Flys: 2
		[12] Wings: 2
		[13] Feathers: 2
		[14] Tenticles: 2
		[15] Horns: 2
		[16] Base Attack
		[17] range
		[18] alpha value
		[19] stomach size
		[20] fear
		[21] pack
		[22] sight
		*/
		
		genes = new double[23];
		genes[0] = (generate(1, 4));
		
		for(int i = 1; i < 7; i++){
			genes[i] = generate(0, 11);
		}
		
		genes[7] = generate(1, 3);
		
		for(int i = 8; i < genes.length; i ++){
			genes[i] = generate(1, 1);
		}
		
		genes[16] = generate(0, 11);
		genes[17] = generate(0, 11);
		genes[18] = generate(0, 256);
		genes[19] = generate(3, 8);
		genes[20] = generate(1, 10);
		genes[21] = generate(1, 10);
		genes[22] = generate(1, 10);
	}
	
	public GeneSequence(GeneSequence parent1, GeneSequence parent2){
		for(int i = 0; i < (int)(parent1.getSequence().length * Variables.crossPoint);i++){
			genes[i] = parent1.get(i);
		}
		for(int j = (int) (parent1.getSequence().length * Variables.crossPoint); j < genes.length; j++){
			genes[j] = parent2.get(j);
		}
		
		mutate();
		
	}
	
	private void mutate(){
		int ranNum = (int) (Math.random() * 101);
		if(ranNum <= Variables.mutationRate){
			for(int i = 0; i < genes.length; i++){
				if(((int) (Math.random() * 101)) <= Variables.mutateGene){
					double gene = generate(0, 11);
					while(inRange(i, gene) == false ){
						gene = generate(0,256);
					}
					genes[i] = gene;
				}
			}
		}
	}
	
	private boolean inRange(int index, double num){
		if(index == 0 && num >= 1 && num <= 4) return true;
		if((index == 16 || index == 20 || index == 22 || index == 21 || index == 17 || (index >= 1 && index <= 6)) && num >= 0 && num <= 10) return true;
		if(index == 7 && num >= 1 && num <= 3) return true;
		if((index == 18 || (index >= 8 && index <= 10)) && num >= 0 && num <= 255) return true;
		if(index >= 11 && index < 16 && num >=0 && num <= 1) return true;
		if(index == 19 && num >= 3 && num <= 8) return true;
		return false;
	}
	
	public double generate(int start, int end){
		return (Math.random() * end) + start;
	}
	
	public double[] getSequence(){
		return genes;
	}
	
	public double get(int index){
		return genes[index];
	}
	
	public double getRed(){
		return genes[8];
	}
	
	public double getGreen(){
		return genes[9];
	}
	
	public double getBlue(){
		return genes[10];
	}
	
	public double getAlpha(){
		return genes[18];
	}
	
	public String toString(){
		String string = "[ ";
		for(int i = 0; i < genes.length; i++){
			string += genes[i] + " ";
		}
		string += "]";
		
		return string;
		
	}
	
}
