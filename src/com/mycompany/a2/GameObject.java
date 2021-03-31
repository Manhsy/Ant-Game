package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;
import com.codename1.charts.ChartUtil;
import com.codename1.charts.models.Point;
/**
 * Represented game objects
 * @author manhsy
 *
 */
public abstract class GameObject{
	private int size;
	private Point location;
	private int color;
	/**
	 * constructor
	 * @param location
	 * @param color
	 * @param size
	 */

	public GameObject(Point location, int color, int size) {
		this.location = location;
		this.size = size;
		this.color = color;
	}
	/**
	 * 
	 * @return integer of the size of the object
	 */
	public int getSize() {
		return size;
	}
	/**
	 * 
	 * @return Point of the object's location 
	 */
	
	public Point getLocation() {
		return location;
	}
	/**
	 * 
	 * @return integer of object's color
	 */
	
	public int getColor() {
		return color;
	}
	/**
	 * 
	 * @return String that represents the object's location in the form of x,y
	 */
	public String getLocationToString() {
		return Math.floor(location.getX()) + ", " + Math.floor(location.getY());
	}
	
	/**
	 * Set color of the object by subject 50 from it 
	 */
	public void setColor() {
		int[] myColor = new int[3];
		myColor[0] = ColorUtil.red(color);
		myColor[1] = ColorUtil.green(color);
		myColor[2] = ColorUtil.blue(color);
		this.color = ColorUtil.rgb(myColor[0]-50,myColor[1]-50, myColor[2]-50);
	}
	/**
	 * Set the location of the object according to the newly received location
	 * @param location
	 */
	public void setLocation(Point location) {
		this.location = location;
	} 
}
