package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightCommand extends Command{
	private GameWorld gw;
	public RightCommand(GameWorld gw) {
		super("Right");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Ant has turned right ");
		gw.right();
		
	}
}
