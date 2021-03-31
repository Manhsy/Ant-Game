package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{
	public MapView() {
		this.setLayout(new FlowLayout());
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255, 0, 0)));
	}

	@Override
	public void update(Observable observable, Object data) {
		boolean x = (boolean) data;
		if(observable instanceof GameWorld && x == true){
			System.out.println();
			GameWorld gw = (GameWorld) observable;
			IIterator itt = gw.getGameObjectCollection().getIterator();
			while(itt.hasNext()) {
				Object obj = itt.getNext();	
				String op = obj.toString();
				System.out.print(op);
				
			}
		}
	}

}
