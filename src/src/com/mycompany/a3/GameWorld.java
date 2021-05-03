package com.mycompany.a3;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Display;
import com.codename1.ui.List;
/**
 * @author manhsy
 *CSC 133: Assignment #1
 */
public class GameWorld extends Observable{
	private int life = 3;
	private double clock = 0;
	private String soundFlag = "OFF";
	private GameObjectCollection gameObj;
	private Random rand = new Random();
	private int width;
	private int height;
	private Sound flag;
	private Sound foodStation;
	private Sound spider;
	private BGSound backgroundSound;
	private boolean pauseGame = false;
	private boolean position = false;
	/**
	 * initializes the game world
	 */
	public void init(boolean x) {

		gameObj = new GameObjectCollection();
		gameObj.add(Ant.getAnt(x));
		int randomNumsOfFlags = rand.nextInt(9-4)+4;
		
		gameObj.add(new Flag(new Point(50,100), 1));
		
		// location, seqNumdf
		Point lastLoc = new Point(0,0);
		for(int i = 2; i <= randomNumsOfFlags; i++) {
			int randomxx = rand.nextInt(width - 200);
			int randomyy = rand.nextInt(height - 200);
			gameObj.add(new Flag(new Point(randomxx, randomyy), i));
		}

		
		//2< spiders(int heading, int speed, Point location, int color, int size)
		int randomNumsOfSpider = rand.nextInt(7-2)+2;
		for(int i = 1; i <= randomNumsOfSpider; i++) {
			int randomxx = rand.nextInt(width - 200);
			int randomyy = rand.nextInt(height - 200);
			gameObj.add(new Spider(new Point(randomxx, randomyy)));
		}
		
		//2< foodStation (Point location, int color, int size, int capacity)
		int randomNumsOfFoodStation = rand.nextInt(7-2)+2;
		for(int i = 1; i <= randomNumsOfFoodStation; i++) {
			int size = rand.nextInt(50-10)+10;
			int randomxx = rand.nextInt(width - 200);
			int randomyy = rand.nextInt(height - 200);
			gameObj.add(new FoodStation(new Point(randomxx, randomyy),size));
		}

	}
	public void createSounds() {
		flag = new Sound("mario_haha!.wav");

		foodStation  = new Sound("eat.wav");

		spider = new Sound("udeath.wav");
		
		backgroundSound = new BGSound("JUNGLE.wav");
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
	public double getTime() {
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
	public void play() {
		position = false;
		pauseGame = false;
		if(soundFlag.equals("ON")) backgroundSound.run();
		IIterator it = gameObj.getIterator();
		while(it.hasNext()) {
			GameObject go = (GameObject)it.getNext();
			if(go instanceof ISelectable) {
				((ISelectable) go).setSelected(false);
			}
		}		
		setChanged();
		notifyObservers();
	}
	public void pause() {
		position = false;
		backgroundSound.pause();
		pauseGame = true;
	}
	public boolean isPause() {
		return pauseGame;
	}
	/**
	 * invokes when user enters a for accelerate
	 */
	public void accelerate() {
		Ant.getAnt(false).accelerate();
		setChanged();
		notifyObservers();
	}
	/**
	 * invokes when the user enters b for break
	 */
	public void brake() {
		Ant.getAnt(false).decreaseSpeed();
		setChanged();
		notifyObservers();
	}
	/**
	 * invokes when the user enters l for setting ant's heading 5 degrees to the left 
	 */
	public void left() {
		Ant.getAnt(false).changeHeading(-5);
		setChanged();
		notifyObservers();
	}
	/**
	 * invokes when the user enters l for setting ant's heading 5 degrees to the right 
	 */
	public void right() {
		Ant.getAnt(false).changeHeading(5);
		setChanged();
		notifyObservers();
	}
	/**
	 * invokes when the user enters 1-9 indicating the flag sequence number 
	 * @param flagNum

	 */
	public void checkFlag(int flagNum) {
		if(Ant.getAnt(false).reachFlag(flagNum, numberOfFlagg())) {
			System.out.println("Game Over, You Win! Total Time: " + clock);
			Display.getInstance().exitApplication();
		}else {
			setChanged();
			notifyObservers();
		}
	}
	public void updateSound(String x) {
		soundFlag = x;
		if(x.equals("OFF")) {
			backgroundSound.pause();
		}else if (x.equals("ON") && pauseGame==false){
			backgroundSound.play();
		}
		setChanged();
		notifyObservers();
	}

	public void gameClock(int timeElapsed) {
		
		int flags = numberOfFlagg();
		//increment game clock 
		clock+=1; //convert time ticked to millseconds

		//all movable object update positions according to current heading and speed
		IIterator MVit = gameObj.getIterator();
		while(MVit.hasNext()) {
			Object obj = MVit.getNext();
			if(obj instanceof Movable) {
				Movable mov = (Movable)obj;
				mov.move(mov, getWidth(), getHeight(), timeElapsed);
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
				notifyObservers();
			}
		}
		//detect collsion, the only collision that matters is between ant and other object
		IIterator it2 = gameObj.getIterator();
		GameObject curObj = Ant.getAnt(false);
		while(it2.hasNext()) {
			GameObject otherObj = (GameObject) it2.getNext();
			if(otherObj != curObj && !curObj.checkList(otherObj)) {
				if(curObj.collidesWith(otherObj)) {
					curObj.handleCollision(otherObj);
					handleGameWorld(curObj, otherObj);
				}else{
					curObj.removeFrList(otherObj);
				}
			}
		}
		boolean noCollision = true;
		IIterator it3 = gameObj.getIterator();
		GameObject ant = Ant.getAnt(false);
		while(it3.hasNext()) {
			GameObject otherObj = (GameObject) it3.getNext();
			if(otherObj != ant) {
				if(curObj.collidesWith(otherObj)) {
					noCollision = false;
				}
			}
		}
		if(noCollision) Ant.getAnt(false).emptyList();
		System.out.println("Clock has ticked");
		setChanged();
		notifyObservers();
	}
	public void handleGameWorld(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Ant && obj2 instanceof Spider) {
			if(soundFlag.equalsIgnoreCase("ON"))spider.play();;
		}
		if(obj1 instanceof Ant && obj2 instanceof FoodStation) {
			if(soundFlag.equalsIgnoreCase("ON"))foodStation.play();
			int size = rand.nextInt(50-10)+10;
			float randomx =rand.nextFloat() * (width - 100) + 100;
			float randomy =rand.nextFloat() * (height - 100) + 100;
			gameObj.add(new FoodStation(new Point(randomx, randomy),size));
		}
		if(obj1 instanceof Ant && obj2 instanceof Flag) {
			if(soundFlag.equalsIgnoreCase("ON"))flag.play();
			Flag s = (Flag) obj2;
			checkFlag(s.getSequenceNumber());
		}
	}
	public void setPosition(boolean b) {
		position = b;
	}
	public boolean getPosition() {
		return position;
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
