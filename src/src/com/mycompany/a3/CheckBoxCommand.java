package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;

public class CheckBoxCommand extends Command{
	private GameWorld gw;
	private Toolbar toolBar;
	public CheckBoxCommand(GameWorld gw, Toolbar toolBar) {
		super("Sound");
		this.gw = gw;
		this.toolBar = toolBar;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(((CheckBox) evt.getComponent()).isSelected()) {
			gw.updateSound("ON");
		}else {
			gw.updateSound("OFF");
		}
		toolBar.closeSideMenu();
	}
}