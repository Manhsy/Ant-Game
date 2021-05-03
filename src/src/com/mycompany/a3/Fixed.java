package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
/**
 * Represents fixed object that extends game object
 * @author manhsy
 *
 */

public abstract class Fixed extends GameObject implements ISelectable{
	/**
	 * Constructor
	 * @param location
	 * @param color
	 * @param size
	 */
	private boolean isSelected=false;
	public Fixed(Point location, int color, int size) {
		super(location, color, size);
	}
	
	/**
	 * Override parent's setlocation method
	 */
	public void changeLocation(Point p) {
		 this.setLocation(p);
	}
	public void setSelected (boolean b) {
		this.isSelected = b;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public abstract void draw(Graphics g, Point pCmpRePrnt);
	public abstract boolean contains(Point pPrtRelPrnt, Point pCmpRelPrnt);
	
}
