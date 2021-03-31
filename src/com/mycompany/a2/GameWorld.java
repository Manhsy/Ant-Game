package com.mycompany.a2;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Display;
/**
 * @author manhsy
 *CSC 133: Assignment #1
 */
public class GameWorld extends Observable{
	private int life = 3;
	private int clock = 0;
	private String soundFlag = "OFF";
	private GameObjectCollection gameObj;
	private Random rand = new Random();
	private int width;
	private int height;
	/**
	 * initializes the game world
	 */
	public void init(boolean x) {
		gameObj = new GameObjectCollection();
		int randomNumsOfFlags = rand.nextInt(9-4)+4;
		
		gameObj.add(new Flag(new Point(0,0), 1));
		
		// location, seqNumdf
		for(int i = 2; i <= randomNumsOfFlags; i++) {
			gameObj.add(new Flag(new Point(rand.nextInt(width), rand.nextInt(height)), i));
		}

		gameObj.add(Ant.getAnt(x));
	
		//2< spiders(int heading, int speed, Point location, int color, int size)
		int randomNumsOfSpider = rand.nextInt(7-2)+2;
		for(int i = 1; i <= randomNumsOfSpider; i++) {
			//Point location, int heading, int speed, int size, int color
			gameObj.add(new Spider(new Point(rand.nextInt(width), rand.nextInt(height))));
		}
		
		//2< foodStation (Point location, int color, int size, int capacity)
		int randomNumsOfFoodStation = rand.nextInt(7-2)+2;
		for(int i = 1; i <= randomNumsOfFoodStation; i++) {
			int size = rand.nextInt(50-10)+10;
			gameObj.add(new FoodStation(new Point(rand.nextInt(width), rand.nextInt(height)),size));
		}
	}
	public GameObjectCollection getGameObjectCollection() {
		return gameObj;
	}
	public String getSound() {
		return soundFlag;
	}
	public Ant getAnt() {
		return Ant.getAnt(false);
	}
	public int getTime() {
		return clock;
	}
	public int getLives() {
		return life;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height; 
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height; 
	}
	/**
	 * invokes when user enters a for accelerate
	 */
	public void accelerate() {
		Ant.getAnt(false).accelerate();
		setChanged();
		notifyObservers(true);
	}
	/**
	 * invokes when the user enters b for break
	 */
	public void brake() {
		Ant.getAnt(false).decreaseSpeed();
		setChanged();
		notifyObservers(true);
	}
	/**
	 * invokes when the user enters l for setting ant's heading 5 degrees to the left 
	 */
	public void left() {
		Ant.getAnt(false).changeHeading(-5);
		setChanged();
		notifyObservers(true);
	}
	/**
	 * invokes when the user enters l for setting ant's heading 5 degrees to the right 
	 */
	public void right() {
		Ant.getAnt(false).changeHeading(5);
		setChanged();
		notifyObservers(true);
	}
	/**
	 * invokes when the user enters 1-9 indicating the flag sequence number 
	 * @param flagNum
	 * @return true/false, whether the flag reached was the last flag or not 
	 */
	public void checkFlag(int flagNum) {
		if(Ant.getAnt(false).reachFlag(flagNum, numberOfFlagg())) {
			System.out.println("Game Over, You Win! Total Time: " + clock);
			Display.getInstance().exitApplication();
		}else {
			setChanged();
			notifyObservers(false);
		}
	}
	public void updateSound(String x) {
		soundFlag = x;
		setChanged();
		notifyObservers(false);
	}
	/**
	 * invokes when the user enter f for colliding with food station 
	 * updates the ant and foodstation accordingly 
	 */
	public void foodStation() {
		//get food station 
		FoodStation b = getFoodStation();
		
		//ant reached foodstation
		Ant.getAnt(false).reachFoodStation(b);

		//update food station
		b.updateStation();
		
		//add new food station 
		int size = rand.nextInt(50-10)+10;
		gameObj.add(new FoodStation(new Point(rand.nextInt(width), rand.nextInt(height)),size));
		
		setChanged();
		notifyObservers(true);
	}
	/**
	 * 
	 * @return true/false about whether or not the collision with spider killed the ant 
	 */
	public void collideWithSpider() {
		//get spider
		Spider s = getRandomSpider();
		//update ant's state
		if(Ant.getAnt(false).collidedWSpider()) {
			life--;
			if(life==0) {
				System.out.println("Game Over, You Fail!");
				Display.getInstance().exitApplication();
			}else {
				System.out.println("Ant colided with spider and lost 1 life, game has reseted");
				init(true);
				setChanged();
				notifyObservers(true);
			}
		}else {
			System.out.println("Ant collided with spider, but it lives on");
			setChanged();
			notifyObservers(true);
		}
	}

	/**
	 * increments clock
	 * update spider's random heading 
	 * all movable object, ant & spider, move to a new location depending on their heading and location 
	 * reduce the ant's food level according to the food level consumption 
	 * @return true/false whether or not the clock ticking which killed
	 */
	public void gameClock() {
		//increment game clock 
		clock++;
		
		//spider update heading
		IIterator spiderIt = gameObj.getIterator();
		while(spiderIt.hasNext()) {
			Object obj = spiderIt.getNext();
			if(obj instanceof Spider) {
				Spider spi = (Spider) obj;
				spi.moveRandom();
			}
		}
		//all movable object update positions according to current heading and speed
		IIterator MVit = gameObj.getIterator();
		while(MVit.hasNext()) {
			Object obj = MVit.getNext();
			if(obj instanceof Movable) {
				Movable mov = (Movable)obj;
				mov.move(mov, getWidth(), getHeight());
			}
		}
		
		//reduce ant's food level based on the foodConsumptionRAte
		Ant.getAnt(false).decreaseFoodLevel();
		if(Ant.getAnt(false).getCurrentFoodLevel() <= 0) {
			life--;
			if(life == 0) {
				System.out.println("Ant's good level reached 0 & Ant has lost all its lives, Game Over! you lost");
				Display.getInstance().exitApplication();
			}else {
				System.out.println("Ant's food level reached 0, game has reseted");
				init(true);
				setChanged();
				notifyObservers(true);
			}
		}else {
			System.out.println("Clock has ticked");
			setChanged();
			notifyObservers(true);
		}
	}


	/**
	 * selects a random spider that the ant can collide with 
	 * @return Spider
	 */
	private Spider getRandomSpider() {
		//arraylist that will hold indexes of spiders
		ArrayList<Spider> indexesOfSpiders = new ArrayList<Spider>();
		IIterator it = gameObj.getIterator();
		while(it.hasNext()) {
			Object obj = it.getNext();
			if(obj instanceof Spider) {
				indexesOfSpiders.add((Spider) obj);
			}
		}
		//get ant
		//pick random spider
		int randomSpiderIndex = rand.nextInt(indexesOfSpiders.size()-1);
		Spider s = (Spider) indexesOfSpiders.get(randomSpiderIndex);
		return s;
	}

	/**
	 * 
	 * @return FoodStation for ant to consume
	 */
	private FoodStation getFoodStation() {
		//arraylist that will hold non empty foodstation
		ArrayList<FoodStation> indOfNonEmptyFoodStation = new ArrayList<FoodStation>();
		IIterator it = gameObj.getIterator();
		int idx=0;
		while(it.hasNext()) {
			Object obj = it.getNext();
			if(obj instanceof FoodStation) {
				FoodStation ft = (FoodStation)obj;
				if(ft.getCapacity()!=0) {
					indOfNonEmptyFoodStation.add(ft);
				}
			}
			idx++;
		}
		int randomFoodStastionIndex = rand.nextInt(indOfNonEmptyFoodStation.size()-1);
		FoodStation s = (FoodStation) indOfNonEmptyFoodStation.get(randomFoodStastionIndex);

		return s;
	}
	/**
	 * 
	 * @return the number of flag in the gameObj
	 */
	private int numberOfFlagg() {
		int count=0;
		IIterator it = gameObj.getIterator();
		while(it.hasNext()) {
			Object obj = it.getNext();
			if(obj instanceof Flag) {
				count++;
			}
		}
		return count;
	}
	
	
}
