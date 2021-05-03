package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {
	public HelpCommand() {
		super("Help");
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		Command ok = new Command("Ok");
		Command namec = Dialog.show("List of available Keys: \n", "a - accelerate\n"
				+ "b - brake\n"
				+ "l - left turn\nr - right turn\n", ok);
	}
}