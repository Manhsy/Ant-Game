package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	GameWorld gw;
	GameObjectCollection collection;
	boolean activatePaint = false;

	public MapView() {
		this.setLayout(new FlowLayout());
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255, 0, 0)));

	}

	@Override
	public void update(Observable observable, Object data) {
		if(observable instanceof GameWorld){
			this.gw = (GameWorld) observable;
			this.collection = gw.getGameObjectCollection();
			System.out.println();
			GameWorld gw = (GameWorld) observable;
			IIterator itt = gw.getGameObjectCollection().getIterator();
			while(itt.hasNext()) {
				Object obj = itt.getNext();	
				String op = obj.toString();
				System.out.print(op);
				
			}
		}
		activatePaint = true;
		this.repaint();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt= new Point(getX(), getY());
		if(activatePaint) {
			IIterator it = collection.getIterator();
			while(it.hasNext()) {
				GameObject object = (GameObject) it.getNext();
				object.draw(g, pCmpRelPrnt);
			}
		}
	}
	@Override
	public void pointerPressed(int x, int y) {
		Fixed f = null;
		//if game is pause and its  not in positioning mode
		if(gw.isPause()&&!gw.getPosition()) {
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());

			IIterator it = gw.getGameObjectCollection().getIterator();
			while(it.hasNext()) {
				GameObject object = (GameObject) it.getNext();
				if(object instanceof ISelectable) {
					if(((ISelectable) object).contains(pPtrRelPrnt, pCmpRelPrnt)) {
						((ISelectable) object).setSelected(true);
					}else {
						((ISelectable) object).setSelected(false);
					}
				}
			}
		}
		
		this.repaint();
		//if game is paused and its in position mode
		if(gw.getPosition() && gw.isPause()) {
			int newX = x - this.getAbsoluteX();
			int newY = y - this.getAbsoluteY();
			Point newPoint = new Point(newX, newY);

			IIterator it2 = gw.getGameObjectCollection().getIterator();
			while(it2.hasNext()) {
				GameObject o = (GameObject) it2.getNext();
				if(o instanceof Fixed) {
					Fixed ff = (Fixed) o;
					if(ff.isSelected()) {
						f = ff;
						break;
					}
				}
			}
			if(f!=null && gw.getPosition()) {
				f.changeLocation(newPoint);
				f.setSelected(false);
				this.repaint();
			}
			gw.setPosition(false);
		}

	}

}
