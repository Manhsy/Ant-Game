package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.DataChangedListener;

public class ExitCommandd extends Command{
	public ExitCommandd() {
		super("Exit");
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		boolean exitConfirm = Dialog.show("Confirm exit", "Are you sure you want to exit?", "Yes", "No");
			if(exitConfirm) {
				Display.getInstance().exitApplication();
			}
	}
}
