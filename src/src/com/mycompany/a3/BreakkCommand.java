package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BreakkCommand extends Command{
	private GameWorld gw;
	public BreakkCommand(GameWorld gw) {
		super("Break");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.brake();
	}
}