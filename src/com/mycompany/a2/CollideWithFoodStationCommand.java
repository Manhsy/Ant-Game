package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideWithFoodStationCommand extends Command{
	private GameWorld gw;
	public CollideWithFoodStationCommand(GameWorld gw) {
		super("Collide with Food Station");
		this.gw = gw;
	}
	@Override 

	public void actionPerformed(ActionEvent evt) {
		gw.foodStation();
	}
}
