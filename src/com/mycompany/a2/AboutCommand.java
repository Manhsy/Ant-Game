package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;


public class AboutCommand extends Command{
	public AboutCommand() {
		super("About");
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		Command ok = new Command("Ok");
		Command namec = Dialog.show("About:\n","Name: Manh Sy\n"
				+ "Class: Computer Science 133\n"
				+ "Assignment number: 2", ok);	
	}
}
