package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;


public class CollideWithFlagCommand extends Command{
	private GameWorld gw;
	public CollideWithFlagCommand(GameWorld gw) {
		super("Collide with Flag");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		Command ok = new Command("Ok");
		TextField tf = new TextField();
		Command c = Dialog.show("Enter a flag number between 1-9", tf, ok);	
		try {
			int flagNum = Integer.parseInt(tf.getText());
			if(0>=flagNum || flagNum>9) {
				System.out.println("please only enter number 1-9");
			}else {
				gw.checkFlag(flagNum);
			}
			
		}catch(Exception E) {
			System.out.println("please only enter number 1-9, no letters");
		}
	}
}
