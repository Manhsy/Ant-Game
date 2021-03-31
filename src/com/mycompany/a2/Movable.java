package com.mycompany.a2;
import com.codename1.charts.models.Point;
/**
 * Represents movable object that extends game object
 * @author manhsy
 *
 */
public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
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
	
	//bounds for spider
	public void move(Movable mov, int width, int height) {
		
		float deltaX = (float) Math.cos(Math.toRadians(90-heading)) * speed;
		float deltaY =(float) Math.sin(Math.toRadians(90-heading)) * speed;

		this.setLocation(new Point ((float)getLocation().getX() + deltaX, (float)getLocation().getY()+deltaY));
		
		if(outOfBounds(width, height)) {
			if(mov instanceof Spider) {
				Spider s = (Spider) mov;
				s.moveRandom();
			}else if(mov instanceof Ant){
				Ant a = (Ant) mov;
				a.changeHeading(5);
			}
			move(mov, height, width);
		}
	}
	
	public boolean outOfBounds(int width, int height) {
		if((0 <= getLocation().getX() && getLocation().getX() <= width)  &&  (0 <=getLocation().getY() && getLocation().getY() <=height)) {
			return false;
		}else {
			return true;
		}
	}
	
}
