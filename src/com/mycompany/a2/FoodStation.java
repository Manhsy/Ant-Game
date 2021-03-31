package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
/**
 * Represent food station object that extends fixed object
 * @author manhsy
 *
 */
public class FoodStation extends Fixed {
	private static Random rand = new Random();
	private int capacity;
	/**
	 * Constructor
	 * @param location
	 * @param color
	 * @param size
	 * @param capacity
	 */

	public FoodStation(Point location, int size) {
		super(location , ColorUtil.rgb(255,0,0), size);
		this.capacity = (int) size * 1/2;
	}
	/**
	 * 
	 * @return integer of food station's capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Updates the food station by setting its capacity to 0 and changing its color
	 */
	public void updateStation() {
		this.capacity = 0;
		super.setColor();
	}
	/**
	 * Returns String that includes the food station's location, color, size, & capacity
	 * @return String
	 */
	public String toString() {
		int[] myColor = new int[3];
		myColor[0] = ColorUtil.red(super.getColor());
		myColor[1] = ColorUtil.green(super.getColor());
		myColor[2] = ColorUtil.blue(super.getColor());
		return "FoodStation: loc= " + super.getLocationToString() + " color= [" + myColor[0]+ ", "+ myColor[1] + ", " + myColor[2]+ "] size= " + super.getSize() + " capacity= "+ capacity+ "\n";
	}
}
