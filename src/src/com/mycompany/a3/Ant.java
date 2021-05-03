package com.mycompany.a3;
import com.codename1.charts.models.Point;
import java.util.Random;
/**
 * Represents Ant object that extends Movable object and implements steerable interface
 * @author manhsy
 */
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
public class Ant extends Movable implements ISteerable{
	private int maximumSpeed = 300;
	private double foodLevel = 250;
	private double foodConsumptionRate = .2;
	private int healthLevel = 10;
	private int lastFlagReached = 1;
	private static Ant a;
	/**
	 * Constructor for Ant
	 * @param location
	 * @param heading
	 * @param speed
	 * @param size
	 * @param maxSpeed
	 * @param foodlvl
	 * @param foodCon
	 * @param health
	 * @param lastFlagReached
	 * @param color
	 */

	private Ant(Point location, int heading, int speed, int size, int color) {
		super(location, heading, speed, size, color);
	}
	
	public static Ant getAnt(boolean x) {
		if(a==null || x == true) {
			a = new Ant(new Point(50,100), 5, 288, 50, ColorUtil.BLACK);
			return a;
		}else {
			return a;
		}
	}

	/**
	 * 
	 * @return integer of lastFlagReached
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	/**
	 *  
	 * @return integer of foodLevel
	 */
	public double getCurrentFoodLevel() {
		return foodLevel;
	}
	/**
	 * 
	 * @return integer of healthLevel
	 */
	public int getCurrentHealthLevel() {
		return healthLevel;
	}
	/**
	 * This method allows the Ant to accelerate according to health level
	 */
	public void accelerate() {
		System.out.println("Ant speed has changed according to its health and maximum speed");
		super.setSpeed((int) Math.floor(maximumSpeed * (healthLevel*(.100))));
	}
	/**
	 * This method allows theAnt to decrease in speed, but also ensuring that
	 * it will not reach 0
	 */
	public void decreaseSpeed() {
		Random rand = new Random();
		int randNum = rand.nextInt(7)+1;
		int newSpeed = super.getSpeed()-randNum;
		if(newSpeed<=0) {
			System.out.println("cannot decrease ant's speed, it will be reach below 0");
			System.out.println("please accelerate ant before decreasing its speed");
		}else {
			System.out.println("Brake has been applied");
			super.setSpeed(super.getSpeed()-randNum);
		}
		
		
	}
	/**
	 * Changes the heading of the Ant
	 */
	public void changeHeading(int c) {
		super.setHeading((super.getHeading()+c+360) % 360);
	}
	/**
	 * This method checks if the entered flag sequence was valid, and updates Ant's lastFlagReached 
	 * value accordingly
	 * @param flagNum
	 * @param numOfFlags
	 * @return true/false that indicates if Ant has successfully walked through
	 * all the flags
	 */
	public boolean reachFlag(int flagNum, int numOfFlags) {
		if(this.lastFlagReached+1 == flagNum) {
			this.lastFlagReached = flagNum;
			if(this.lastFlagReached == numOfFlags) {
				return true;
			}else {
				System.out.println("Nice! correct walk, no change in the map view");
				return false;
			}
		}else {
			System.out.println("Incorrect walk, no change in the map view");
			return false;
		}
		
	}
	/**
	 * Increases ant's foodlevel by the capacity of the chosen food station
	 * @param fd
	 */
	public void reachFoodStation(FoodStation fd) {
		this.foodLevel += fd.getCapacity();
		System.out.println("Ant has collided with food station");
	}
	/*
	 * Decreases Ant's health by 1
	 * Changes Ant's color
	 * Changes Ant's speed according to its new health level
	 * @return true/false about whether or not the collision resulted in the death of the spider
	 */
	public void collidedWSpider() {
		healthLevel--;
		super.setColor();
		//accelerate based on health
		accelerate();

	}
	/**
	 * decreases Ant's food level by its food consumption rate 
	 */
	public void decreaseFoodLevel() {
		foodLevel -= foodConsumptionRate;
	}
	/**
	 * Return string that includes the Ant's locaiton, color, heading, speed, size, maximum speed, & food consumption rate
	 * @return String
	 */
	public String toString() {

		int[] myColor = new int[3];
		myColor[0] = ColorUtil.red(super.getColor());
		myColor[1] = ColorUtil.green(super.getColor());
		myColor[2] = ColorUtil.blue(super.getColor());
		return "Ant: loc= " + super.getLocationToString() + " color= [" + myColor[0]+ ", "+ myColor[1] + ", " + myColor[2]+ "] heading= " + 
				super.getHeading() + " speed= " + super.getSpeed() + " size= " + super.getSize() + " maxSpeed= "+ this.maximumSpeed + 
				" foodConsumptionRate= " + this.foodConsumptionRate + "\n";
	}
	public void draw(Graphics g, Point pCpmRelPrnt) {
		g.setColor(this.getColor());

		int xloc = (int)pCpmRelPrnt.getX() + (int)this.getLocation().getX();
		int yloc = (int)pCpmRelPrnt.getY() + (int)this.getLocation().getY();
		
		g.fillArc(xloc, yloc, this.getSize(), this.getSize(), 0, 360);


	}
}
