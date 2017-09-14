package evolution.launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import evolution.imaging.Display;

public class Animal{
	private int speed, posX, posY, health,fly, baseSpeed, size, range, stomach, stomachSize, foodVal;
	private GeneSequence genes;
	private double attack, fear, pack, sight;
	private Direction dir;
	private ArrayList<Animal> parents;
	private Display display;
	
	public Animal(Display display){
		this(null, null, display);
	}
	
	public Animal(Animal parent1, Animal parent2, Display display){
		this.posX = (int)(Math.random() * display.getWidth()) + 1;
		this.posY = (int)(Math.random() * display.getHeight()) + 1;
		this.display = display;
		
		if(parent1 != null && parent2 != null){
			genes = new GeneSequence(parent1.getGenes(), parent2.getGenes());
			parents.add(parent1);
			parents.add(parent2);
			parents.addAll(parent1.getParents());
			parents.addAll(parent2.getParents());
		}
		else
			this.genes = new GeneSequence();
		
		speed = 0;
		baseSpeed = (int)genes.get(1);
		fly = (int)genes.get(11);
		size = (int)genes.get(6) * 10;
		attack = (int)(genes.get(16));
		if(genes.get(15) == 1){
			attack += 2;
		}
		
		stomachSize = (int) genes.get(19);
		stomach = 0;
		foodVal = (int)genes.get(6);
		fear = genes.get(20);
		dir = Direction.UP;
		pack = genes.get(21);
		sight = genes.get(22);
		range = (int) genes.get(17);
	}
	
	public ArrayList<Animal> getParents(){
		int s = Variables.ansestorAmount;
		if(s == 0){
			return this.getAllParents();
		}
		else{
			if(this.getParents().size() < 2){
				s = this.getParents().size();
			}
			ArrayList<Animal> parent = new ArrayList<Animal>();
			for(int i = 0; i < s; i++){
				parent.add(parents.get(i));
			}
			return parent;
		}
	}
	
	private ArrayList<Animal> getAllParents(){
		return this.parents;
	}

	public void accelerate(){
		speed += baseSpeed/size;
	}
	
	public void deccelerate(){
		speed -= baseSpeed/size;
	}
	
	public boolean can(int x){
		if( 1 == x){
			return true;
		}
		else return false;
	}
	
	public double getAttack(){
		return attack;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public int animalSize(){
		return size;
	}
	
	public void showAnimal(Graphics2D g){
		g.setColor(new Color((int)genes.getRed(), (int)genes.getGreen(), (int)genes.getBlue(), (int)genes.getAlpha()));
		g.fillRect(posX, posY, size/2, size);
		g.setColor(Color.BLACK);
		g.fillRect((posX + size/2) / 3, 5, (posX + size/2)/3 , 2);
	}
	
	
	
	public boolean fears(Animal animal){
		if(Math.abs(animal.getSize() - this.getSize()) < .000001 && animal.getSize() - 2 <= animal.getSize()){
			return (Math.random() * 10) + 1 < fear + 1; 
		}
		return ((Math.random() * 1) + 1 < fear);
	}
	
	public boolean isEnemy(Animal animal){
		return !(isFriend(animal));
	}
	public boolean isFriend(Animal animal){
		for(Animal ans : parents){
			for(Animal an : animal.getAllParents()){
				if(ans.equals(an)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int[] formPack(Animal[] animals){
		Animal nA = this.nearestAnimal(animals);
		int[] coords = new int[2];
		if(Math.abs(nA.getPosX() - this.getPosX()) < Variables.range){
			if(Math.abs(nA.getPosY() - this.getPosY()) < Variables.range){
				coords[0] = nA.getPosX();
				coords[1] = nA.getPosY();
				return coords;
			}
		}
		
		return coords;

	}
	
	public Direction chooseDirection(int x){
		if(x == 1){
			dir = Direction.UP;
			return dir;
		}
		else if(x == 2){
			dir = Direction.LEFT;
			return dir;
		}
		else if(x == 3){
			dir = Direction.DOWN;
			return dir;
		}
		else if(x == 4){
			dir = Direction.RIGHT;
			return dir;
		}
		else return null;
	}
	
	private Animal[] seeAnimals(Animal[] animal, Direction d){
		Animal[] animals = closestAnimals(animal);
		ArrayList<Animal> sees = new ArrayList<Animal>();
		
		
		if( d == d.DOWN){
			for(Animal an : animals){
				int pValue = an.getPosY() - this.getPosY() + this.getPosX();
				int nValue = -1 * an.getPosY() + this.getPosY() + this.getPosX();
				if(an.getPosY() >= this.getPosY() && an.getPosX() < pValue && an.getPosX() > nValue && Math.abs(an.getPosY() - this.getPosY()) < sight){
					sees.add(an);
				}
			}
			
			return (Animal[]) sees.toArray();
		}
		
		else if(d == d.LEFT){
			for(Animal an : animals){
				int pValue = an.getPosY() - this.getPosY() + this.getPosX();
				int nValue = -1 * an.getPosY() + this.getPosY() + this.getPosX();
				if(an.getPosX() <= this.getPosX() && an.getPosY() < pValue && an.getPosY() > nValue && Math.abs(an.getPosX() - this.getPosX()) < sight){
					sees.add(an);
				}
			}
		}
		
		else if (d == d.RIGHT){
			for(Animal an : animals){
				int pValue = an.getPosY() - this.getPosY() + this.getPosX();
				int nValue = -1 * an.getPosY() + this.getPosY() + this.getPosX();
				if(an.getPosX() >= this.getPosX() && an.getPosY() > pValue && an.getPosY() < nValue && Math.abs(an.getPosY() - this.getPosY()) < sight){
					sees.add(an);
				}
			}
		}
		
		else if(d == d.UP){
			for(Animal an : animals){
				int pValue = an.getPosY() - this.getPosY() + this.getPosX();
				int nValue = -1 * an.getPosY() + this.getPosY() + this.getPosX();
				if(an.getPosY() <= this.getPosY() && an.getPosX() > pValue && an.getPosX() < nValue && Math.abs(an.getPosX() - this.getPosX()) < sight){
					sees.add(an);
				}
			}
		}
		
		else if(d == d.UPLEFT){
			for(Animal an : animals){
				if(an.getPosX() <= this.getPosX() && an.getPosY() <= this.getPosY()){
					if(Math.abs(an.getPosX() - this.getPosX()) < sight && Math.abs(an.getPosY() - this.getPosY()) < sight){
					sees.add(an);
					}
				}
			}
		}
		
		else if(d == d.UPRIGHT){
			for(Animal an : animals){
				if(an.getPosX() >= this.getPosX() && an.getPosY() <= this.getPosY()){
					if(Math.abs(an.getPosX() - this.getPosX()) < sight && Math.abs(an.getPosY() - this.getPosY()) < sight){
						sees.add(an);
					}
				}
			}
		}
		
		else if(d == d.DOWNRIGHT){
			for(Animal an : animals){
				if(an.getPosX() >= this.getPosX() && an.getPosY() >= this.getPosY()){
					if(Math.abs(an.getPosX() - this.getPosX()) < sight && Math.abs(an.getPosY() - this.getPosY()) < sight){
						sees.add(an);
					}
				}
			}
		}
		
		else if(d == d.DOWNLEFT){
			for(Animal an : animals){
				if(an.getPosX() <= this.getPosX() && an.getPosY() >= this.getPosY()){
					if(Math.abs(an.getPosX() - this.getPosX()) < sight && Math.abs(an.getPosY() - this.getPosY()) < sight){
						sees.add(an);
					}
				}
			}
		}
		
		
		
		return (Animal[]) sees.toArray();
		
	}
	
	private Animal nearestAnimal(Animal[] animals){
		Animal closest = animals[0];
		int minX = animals[0].getPosX();
		int minY = animals[0].getPosY();
		int min = 0;
		
		
		for(Animal animal : animals){
			int x = animal.getPosX();
			int y = animal.getPosY();
			int calc = hypotenuse(Math.abs(minX - x), Math.abs(minY - y));
			if( calc < min && animal != this){
				min = calc;
				closest = animal;
			}
				
		}
		return closest;
		
	}
	
	private Animal[] closestAnimals(Animal[] animals){
		ArrayList<Animal> finalList = new ArrayList<Animal>();
		
		for(int i = 0; i < animals.length; i++){
			int currentDist = displacement(animals[i]);
			int max = finalList.get(0).displacement(animals[i]);
			
			for(int j = 0; j < finalList.size(); j++){
				if(!(currentDist >= this.displacement(finalList.get(j)))){
					finalList.add(animals[i]);
				}
			}
			
		}
		
		if(finalList.indexOf(this) != -1){
			finalList.remove(finalList.indexOf(this));
		}
		
		return (Animal[]) finalList.toArray();
	}
	
	private int hypotenuse(int a, int b){		
		return (int)(Math.sqrt(Math.pow(a,  2) + Math.pow(b,  2)));
	}
	
	private int displacement(int x2, int y2){
		return hypotenuse(Math.abs(this.getPosX() - x2), Math.abs(this.getPosY()-y2));
	}
	
	private int displacement(Animal animal){
		return displacement(animal.getPosX(), animal.getPosY());
	}
	
	public void addFood (int food){
		stomach += food;
	}
	
	public void starve(int value){
		stomach -= value;
	}
	
	public void starve(){
		stomach--;
	}
	
	public GeneSequence getGenes(){
		return genes;
	}
	
	public double getSize(){
		return size;
	}
	
	public void ridHealth(double attack2){
		health -= attack2;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int[] moveTo(int x, int y){
		if(x >= 0 && x <= display.getWidth() && y >= 0 && display.getHeight() >= y){
			if(x < posX)
				if(Math.abs(x - posX) < speed)
					posX -= x;
				else
					posX -= speed;
			else
				if(Math.abs(x - posX) < speed)
					posX += x;
				else
					posX += speed;
			if(y < posY)
				if(Math.abs(y - posY) < speed)
					posY -= y;
				else
					posY -= speed;
			else
				if(Math.abs(y - posY) < speed)
					posY += y;
				else
					posY += speed;
			
			return new int[] {posX, posY};
		}
		return null;
		
	}
	
	public int[] moveTo(Animal animal){
		return moveTo(animal.getPosX(), animal.getPosY());
	}
	
	public int[] moveAI(Animal[] animals){
		int[] coords = new int[2];
		Animal cA = nearestAnimal(animals);
		int cAX = cA.getPosX();
		int cAY = cA.getPosY();
		int x = 0;
		int y = 0;
		for(int i = 0; i < animals.length; i ++){
			if((stomach <= stomachSize - 2 || stomach == 1 || stomach == 0)){
				this.moveTo(x, y);
			}						
		}		
		
		return coords;
		
	} 
	
	public int[] roam(Animal[] ani){
		int ranNum = (int)(Math.random() * 4) + 1;
		Animal[] seesAnimals = seeAnimals(ani, chooseDirection(ranNum));
		int counter = 0;
		for(Animal animal : seesAnimals){
			if(!(isFriend(animal))){
				
			}
		}
		
		
	}
	
	private int[] hunt(Animal[] ani){
		Animal[] sees = this.seeAnimals(ani, dir);
		
		for()
	}
	
	public Animal attack(Animal animal){
		if(displacement(animal) > range){
			this.moveTo(animal);
		}
		else
			animal.ridHealth(attack);
		
		return animal;
	}
	
}
