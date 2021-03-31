package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

public class CustomButton extends Button{
	public CustomButton(String buttonName, Command cmd) {
		super(buttonName);
		if(!buttonName.equals("Help")) {
			this.getAllStyles().setBgTransparency(250);
			this.getAllStyles().setBgColor(ColorUtil.BLUE);
			this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
			this.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
			this.getAllStyles().setPadding(Component.TOP, 5);
			this.getAllStyles().setPadding(Component.BOTTOM, 5);
			this.getAllStyles().setPadding(Component.LEFT, 5);
			this.getAllStyles().setPadding(Component.RIGHT, 5);
			this.setCommand(cmd);
		}else {
			this.setCommand(cmd);
		}
		
	}
}
