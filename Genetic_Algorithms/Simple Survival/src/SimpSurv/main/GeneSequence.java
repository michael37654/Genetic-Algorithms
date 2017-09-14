package SimpSurv.main;

public class GeneSequence {
	private double[] genes;
	
	//red, green, blue, health, attack, size, wings 
	public GeneSequence(GeneSequence parent1, GeneSequence parent2){
		if(parent1 != null && parent2 != null){
			for(int i = 0; i < parent1.genes.length * Variables.cross; i++){
				genes[i] = parent1.genes[i];
			}
			
			for(int i = parent1.genes.length * Variables.cross; i < parent2.genes.length; i++){
				genes[i] = parent2.genes[i];
			}
		}
		
		else{
			for(int i = 0; i < 3; i++){
				genes[i] = generate(0, 256);
			}
			
			for(int i = 3; i < genes.length - 1; i++){
				
			}
		}
			
		
	}
	
	private double generate(int start, int end){
		return (Math.random() * end) + start;
	}
}
