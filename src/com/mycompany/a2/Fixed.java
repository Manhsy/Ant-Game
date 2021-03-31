package com.mycompany.a2;

import com.codename1.charts.models.Point;
/**
 * Represents fixed object that extends game object
 * @author manhsy
 *
 */

public abstract class Fixed extends GameObject{
	/**
	 * Constructor
	 * @param location
	 * @param color
	 * @param size
	 */
	public Fixed(Point location, int color, int size) {
		super(location, color, size);
	}
	
	/**
	 * Override parent's setlocation method
	 */
	public void setLocation(Point p) {
		//empty body, override
	}
}
