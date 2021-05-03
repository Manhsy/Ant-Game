package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	private Media m;
	public BGSound(String filename) {
		if(Display.getInstance().getCurrent()==null) {
			System.out.println("Error: Create sound objects after calling show()");
			System.exit(0);
		}

		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+filename);
			m = MediaManager.createMedia(is, "audio/wav", this);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		m.setTime(0);
		m.play();
	}
	public void play() {
		m.play();
	}
	public void pause() {
		m.pause();
	}
}
