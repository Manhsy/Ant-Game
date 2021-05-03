package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command{
	private GameWorld gw;
	private boolean mode = true;
	public AccelerateCommand(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(mode) gw.accelerate();
	}
	public void setEnable(boolean b) {
		//this method was added in place of Command's setEnabled, 
		//when Command's setEnabled was used, the accelerate command on the side menu was not disabling!
		mode = b;
	}
}