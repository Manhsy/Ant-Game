package com.mycompany.a2;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
/**
 * Represent flag object that extends fixed object
 * @author manhsy
 *
 */
public class Flag extends Fixed{

	private int sequenceNumber;
	/**
	 * Constructor
	 * @param location
	 * @param color
	 * @param size
	 * @param seqNum
	 */
	public Flag(Point location, int seqNum) {
		super(location, ColorUtil.rgb(0,0,255) , 30);
		this.sequenceNumber = seqNum;
		
	}
	/**
	 * Override parents' set color method
	 */
	public void setColor() {
		//empty body
	}
	/** 
	 * @return integer of sequenceNumber
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * Returns String that has includes the flag's location, color, size, & sequence number
	 * @return String
	 */
	public String toString() {
		int[] myColor = new int[3];
		myColor[0] = ColorUtil.red(super.getColor());
		myColor[1] = ColorUtil.green(super.getColor());
		myColor[2] = ColorUtil.blue(super.getColor());
		
		return "Flag: loc= " + super.getLocationToString() + " color= [" + myColor[0]+ ", "+ myColor[1] + ", " + myColor[2]+ "] size= " + super.getSize() + " seqNum= " + sequenceNumber+ "\n";
	}

}
