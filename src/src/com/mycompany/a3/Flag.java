package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
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
		super(location, ColorUtil.CYAN, 100);
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
	public void draw(Graphics g, Point pCpmRelPrnt) {

		int xloc = (int)pCpmRelPrnt.getX() + (int)this.getLocation().getX();
		int yloc = (int)pCpmRelPrnt.getY() + (int)this.getLocation().getY();
		if(isSelected()) {
			g.setColor(this.getColor());
			g.drawPolygon(new int[] {xloc, (xloc - this.getSize()/2), (xloc + this.getSize()/2)}, 
					new int[] { yloc+this.getSize()/2, yloc-this.getSize()/2,  yloc-this.getSize()/2}, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+sequenceNumber, xloc-this.getSize()/8, yloc-this.getSize()/5);
		}else {
			g.setColor(this.getColor());
			g.fillPolygon(new int[] {xloc, (xloc - this.getSize()/2), (xloc + this.getSize()/2)}, 
					new int[] { yloc+this.getSize()/2, yloc-this.getSize()/2,  yloc-this.getSize()/2}, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+sequenceNumber, xloc-this.getSize()/8, yloc-this.getSize()/5);
		}

	}

	@Override
	public boolean contains(Point pPrtRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		int px = (int)pPrtRelPrnt.getX(); 
		int py = (int)pPrtRelPrnt.getY();
		int xLoc = (int)(pCmpRelPrnt.getX()+this.getLocation().getX()-this.getSize()/2); 
		int yLoc = (int) (pCmpRelPrnt.getY()+this.getLocation().getY()-this.getSize()/2);

		if((px >= xLoc) && (px <= xLoc + this.getSize()) && (py >= yLoc) && (py <= yLoc + this.getSize())) {
			return true;
		}else {
			return false;
		}
	}//1229, 308


}
