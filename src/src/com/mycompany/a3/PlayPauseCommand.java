package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command{
	private Game g;
	private boolean pp = true;
	public PlayPauseCommand(Game g, String label) {
		super(label);
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		pp = !pp;
		if(pp==false) g.pause();
		if(pp==true) g.play();
		
		//gw.brake();
	}

}
