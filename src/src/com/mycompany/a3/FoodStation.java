package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
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
		super(location , ColorUtil.GREEN, size);
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
	public void draw(Graphics g, Point pCpmRelPrnt) {
		int xloc = (int)pCpmRelPrnt.getX() + (int)this.getLocation().getX();
		int yloc = (int)pCpmRelPrnt.getY() + (int)this.getLocation().getY();
		if(isSelected()) {
			g.setColor(this.getColor());
			g.drawRect(xloc, yloc, this.getSize()+35, this.getSize()+35);
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+capacity , xloc+this.getSize()/2, yloc+this.getSize()/3);
		}else {
			g.setColor(this.getColor());
			g.fillRect(xloc, yloc, this.getSize()+35, this.getSize()+35);
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+capacity , xloc+this.getSize()/2, yloc+this.getSize()/3);
		}
	}
	@Override
	public boolean contains(Point pPrtRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		int px = (int)pPrtRelPrnt.getX();
		int py = (int)pPrtRelPrnt.getY();
		int xLoc = (int)(pCmpRelPrnt.getX()+this.getLocation().getX());
		int yLoc = (int) (pCmpRelPrnt.getY()+this.getLocation().getY());
		if(px >= xLoc && px <= xLoc + this.getSize()+35 && py >= yLoc && py <= yLoc + this.getSize()+35) {
			return true;
		}else {
			return false;
		}
	}
}
