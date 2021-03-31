package com.mycompany.a2;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
/**
 * Represents spider object that extends movable object
 * @author manhsy
 *
 */
public class Spider extends Movable {
	private static Random rand = new Random();
	/**
	 * Constructor
	 * @param heading
	 * @param speed
	 * @param location
	 * @param color
	 * @param size
	 */
	public Spider(Point location) {
		//Point location, int heading, int speed, int size, int color
		super(location,rand.nextInt(360), rand.nextInt(10-5)+5, rand.nextInt(50-10)+10, ColorUtil.rgb(150, 150, 150));
	}
	/**
	 * Method to override parent's set color method 
	 * @param color
	 */
	public void setColor(int color) {
		//empty body
	}
	/**
	 * Returns string that includes the spider's location, color, heading, speed, & size
	 * @return String 
	 */
	public String toString() {
		int[] myColor = new int[3];
		myColor[0] = ColorUtil.red(super.getColor());
		myColor[1] = ColorUtil.green(super.getColor());
		myColor[2] = ColorUtil.blue(super.getColor());
		return "Spider: loc= " +  super.getLocationToString() + " color= [" + myColor[0]+ ", "+ myColor[1] + ", " + myColor[2]
				+ "] heading= " +  super.getHeading() + " speed= " + super.getSpeed() + " size= " + super.getSize() + "\n";
	}
	/**
	 * allows the spider to updates its heading -5/+5 everytime game clock ticks 
	 */
	public void moveRandom() {
		Random rand = new Random();
	    int newHeading = super.getHeading() + (rand.nextBoolean() ? 5 : -5);
	    newHeading = (newHeading + 360) % 360;
	    super.setHeading(newHeading);
	}
}
