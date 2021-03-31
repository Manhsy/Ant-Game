package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer{
	private Label timeLabel;
    private Label livesLabel;
    private Label lfrLabel;
    private Label foodLabel;
    private Label healthLabel;
    private Label sound;

	//sound
	public ScoreView() {
		timeLabel = new Label("Time: 0  ");
		livesLabel = new Label("Lives left: 3  ");
		lfrLabel = new Label ("Last Flag Reached: 1  ");
		foodLabel = new Label("Food Level: 25  ");
		healthLabel = new Label("Health Level: 10  ");
		sound = new Label("Sound: OFF");


		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lfrLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);


		
		this.setLayout(new FlowLayout(Component.CENTER));
		this.add(timeLabel);
		this.add(livesLabel);
		this.add(lfrLabel);
		this.add(foodLabel);
		this.add(healthLabel);
		this.add(sound);
	}

	@Override
	public void update(Observable observable, Object data) {
		
		if(observable instanceof GameWorld){
			GameWorld gw = (GameWorld) observable;
			timeLabel.setText("Time: " + gw.getTime());
			livesLabel.setText("Lives Left: "+ gw.getLives());
			lfrLabel.setText("Last Flag Reached: " + gw.getAnt().getLastFlagReached());
			foodLabel.setText("Food Level: " + gw.getAnt().getCurrentFoodLevel());
			healthLabel.setText("Health Level: " + gw.getAnt().getCurrentHealthLevel());
			sound.setText("Sound: " + gw.getSound());
		}
		


	}
}
