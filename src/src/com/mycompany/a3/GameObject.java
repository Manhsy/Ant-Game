package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;

import java.util.ArrayList;

import com.codename1.charts.ChartUtil;
import com.codename1.charts.models.Point;
/**
 * Represented game objects
 * @author manhsy
 *
 */
public abstract class GameObject implements IDrawable, ICollider{
	private int size;
	private Point location;
	private int color;
	private ArrayList<GameObject> collideW =  new ArrayList<GameObject>();
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
	public ArrayList<GameObject> returnList(){
		return collideW;
	}
	public boolean checkList(GameObject x) {
		return collideW.contains(x);
	}
	public void removeFrList(GameObject x) {
		collideW.remove(x);
	}
	public void emptyList() {
		collideW =  new ArrayList<GameObject>();
	}
	public abstract void draw(Graphics g, Point pCpmRelPrnt);
	@Override
	public boolean collidesWith(GameObject object) {

		int thisCenterX = (int)(location.getX() + size/2);
		int thisCenterY = (int)location.getY() + size/2;
		
		int otherCenterX = (int)object.getLocation().getX() + object.getSize()/2;
		
		int otherCenterY = (int)object.getLocation().getY() + object.getSize()/2;
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int disBetweenCenterSqr = ((dx*dx) +(dy*dy));
		
		int thisRadius = size/2;
		int otherRadius = object.getSize()/2;
		int radiiSqr = thisRadius*thisRadius + 2*thisRadius*otherRadius+otherRadius*otherRadius;

		if(disBetweenCenterSqr <= radiiSqr) return true;
		
		return false;
	}
	
	@Override
	public void handleCollision(GameObject object) {
		if(this instanceof Ant && object instanceof Spider) {
			Ant a = (Ant) this;
			a.collidedWSpider();
		}else if(this instanceof Ant && object instanceof FoodStation) {
			Ant a = (Ant) this;
			FoodStation fs = (FoodStation) object;
			a.reachFoodStation(fs);
			fs.updateStation();

		}else if(this instanceof Ant && object instanceof Flag) {
			Ant a = (Ant) this;
			Flag flag = (Flag) object;
		}
		if(!collideW.contains(object))collideW.add(object);
		

	}
}
