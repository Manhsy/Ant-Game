package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.models.Point;
/**
 * Represents movable object that extends game object
 * @author manhsy
 *
 */
public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
	private Random rand = new Random();
	/**
	 * Constructor
	 * @param heading
	 * @param speed
	 * @param location
	 * @param color
	 * @param size
	 */

	public Movable(Point location, int heading, int speed, int size, int color) {
	    super(location, color, size);
		this.heading = heading;
		this.speed = speed;
	}
	/**
	 * 
	 * @return integer of the objec's heading
	 */
	public int getHeading() {
		return heading;
	}
	/**
	 * sets the object's heading 
	 * @param heading
	 */
	public void setHeading(int heading) {
		this.heading = heading;
	}
	/**
	 * 
	 * @return integer of the object's speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Sets the speed to the newly received int speed
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * Sets the location of the object to the newly received location
	 * @param location
	 */
	public void setLocation(Point location) {
		super.setLocation(location);
	}
	/**
	 * Specifiies how movable objects can be moved 
	 */
	
	public void move(Movable mov, int width, int height, int timeElapsed) {
		if(mov instanceof Spider) {
			Spider spi = (Spider) mov;
			spi.changeHeading();
			mov = spi;
		}
			
		float dist = (float)speed * (float)((float)timeElapsed/1000.0);
		
		float deltaX = (float) Math.cos(Math.toRadians(90-heading)) * dist;
		float deltaY =(float) Math.sin(Math.toRadians(90-heading)) * dist;

		this.setLocation(new Point ((float)getLocation().getX() + deltaX, (float)getLocation().getY()+deltaY));
		
		float newX = this.getLocation().getX();
		float newY = this.getLocation().getY();
		//if new location is out of bound on the left side 
		outOfBounds(mov, width, height, newX, newY, timeElapsed);

	}
	
	public void outOfBounds(Movable mov, int width, int height, float newX, float newY, int timeElapsed) {
		if(mov instanceof Ant) {
			if(newX - 35 <= 0 || newX + 35 >= width || newY - 35 <= 0 || newY + 35 >= height) {
				Ant a = (Ant) mov;
				a.changeHeading(180);
				m(a, width, height, timeElapsed);
			}
		}else {
			if(newX - 25 <= 0 || newX + 25 >= width || newY - 25 <= 0 || newY + 25 >= height) {
				Spider s = (Spider) mov;
				s.updateHeading(180);
				m(s, width, height, timeElapsed);
			}
		}
	}
	public void m(Movable mov, int width, int height, int timeElapsed) {
		if(mov instanceof Spider) {
			Spider spi = (Spider) mov;
			spi.changeHeading();
			mov = spi;
		}
			
		float dist = (float)speed * (float)((float)timeElapsed/1000.0);
		
		float deltaX = (float) Math.cos(Math.toRadians(90-heading)) * dist;
		float deltaY =(float) Math.sin(Math.toRadians(90-heading)) * dist;

		this.setLocation(new Point ((float)getLocation().getX() + deltaX, (float)getLocation().getY()+deltaY));
		
		float newX = this.getLocation().getX();
		float newY = this.getLocation().getY();

	}
	
}
