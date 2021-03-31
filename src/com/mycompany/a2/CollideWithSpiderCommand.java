package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideWithSpiderCommand extends Command{
	private GameWorld gw;
	public CollideWithSpiderCommand(GameWorld gw) {
		super("Collide with Spider");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.collideWithSpider();
	}
}
