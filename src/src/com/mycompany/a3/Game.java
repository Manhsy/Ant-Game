package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.TextSelection.Char;
import com.codename1.ui.events.ActionEvent;
import java.lang.*;

/**
 * Controller class that controls the flow of the game that allows for user
 * input by extending Form
 * 
 * @author manhsy
 */

// game is controller
// gameworld is the model
// map and score view is the view
public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private CustomButton playpause;
	private CustomButton position;
	private CustomButton accelerate;
	private CustomButton left;
	private CustomButton breakk;
	private CustomButton right;
	private CheckBox sound;
	private AccelerateCommand ac;
	private LeftCommand lc;
	private RightCommand rc;
	private BreakkCommand bc;

	/**
	 * Constructor
	 */
	public Game() {
		gw = new GameWorld(); // game world is observable
		mv = new MapView(); // map view is an observer
		sv = new ScoreView(); // score view is an observer
		gw.addObserver(sv); // register the score observer
		gw.addObserver(mv); // register the map observer

		// edit the main form
		this.setLayout(new BorderLayout());
		Toolbar toolBar = new Toolbar();
		this.setToolbar(toolBar); // set form's tool bar to tool bar
		this.setTitle("ThePath Game"); // set title of the form

		// establish commands
		ac = new AccelerateCommand(gw);
		lc = new LeftCommand(gw);
		bc = new BreakkCommand(gw);
		rc = new RightCommand(gw);
		AboutCommand about = new AboutCommand();
		ExitCommandd exit = new ExitCommandd();
		CheckBoxCommand soundC = new CheckBoxCommand(gw, toolBar);
		HelpCommand helpC = new HelpCommand();
		PlayPauseCommand ppc = new PlayPauseCommand(this, "Pause");
		PositionCommand pc = new PositionCommand(gw);

		// establish all the button and their connect them to their perspective commands
		accelerate = new CustomButton("Accelerate", ac);
		left = new CustomButton("Left", lc);
		breakk = new CustomButton("Break", bc);
		right = new CustomButton("right", rc);
		CustomButton help = new CustomButton("Help", helpC);
		playpause = new CustomButton("Pause", ppc);
		position = new CustomButton("Position", pc);

		// to add command to toolbar use toobar.addCommandTo();
		// combine button with key
		addKeyListener('a', ac);
		addKeyListener('b', bc);
		addKeyListener('l', lc);
		addKeyListener('r', rc);

		// components for side menu
		toolBar.addCommandToLeftSideMenu(ac); // add command to left side menu of tool bar
		sound = new CheckBox("Sound");
		sound.setCommand(soundC);
		sound.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		sound.getAllStyles().setBgTransparency(255);
		sound.getAllStyles().setBgColor(ColorUtil.WHITE);
		toolBar.addComponentToLeftSideMenu(sound); // add command to left side menu of tool bar
		toolBar.addCommandToLeftSideMenu(about); // add command to left side menu of tool bar
		toolBar.addCommandToLeftSideMenu(exit);
		toolBar.add(BorderLayout.EAST, help);

		// make containers for different regions of form
		Container cmdLeft = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container cmdRight = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container cmdBot = new Container(new FlowLayout(Component.CENTER));

		// style the containers
		cmdLeft.getAllStyles().setBorder(Border.createLineBorder(2));
		cmdRight.getAllStyles().setBorder(Border.createLineBorder(2));
		cmdBot.getAllStyles().setBorder(Border.createLineBorder(2));
		cmdLeft.getAllStyles().setPadding(Component.TOP, 55);
		cmdRight.getAllStyles().setPadding(Component.TOP, 55);

		// add buttons the containers
		cmdLeft.add(accelerate);
		cmdLeft.add(left);

		cmdRight.add(breakk);
		cmdRight.add(right);

		cmdBot.add(position);
		cmdBot.add(playpause);

		// add the containers to the form
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.WEST, cmdLeft);
		add(BorderLayout.EAST, cmdRight);
		add(BorderLayout.SOUTH, cmdBot);
		add(BorderLayout.CENTER, mv);

		show();
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.init(false);
		gw.createSounds();
		revalidate();
		show();

		timer = new UITimer(this);
		timer.schedule(20, true, this);

		position.setEnabled(false);
		position.getDisabledStyle().setBgTransparency(250);
		position.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		position.getDisabledStyle().setFgColor(ColorUtil.BLACK);
	}

	public void play() {
		playpause.setText("Pause");
		gw.play();
		timer.schedule(20, true, this);
		position.setEnabled(false);
		accelerate.setEnabled(true);
		left.setEnabled(true);
		breakk.setEnabled(true);
		right.setEnabled(true);
		sound.setEnabled(true);
		ac.setEnable(true);

		position.getDisabledStyle().setBgTransparency(250);
		position.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		position.getDisabledStyle().setFgColor(ColorUtil.BLACK);

		addKeyListener('a', ac);
		addKeyListener('b', bc);
		addKeyListener('l', lc);
		addKeyListener('r', rc);
	}

	public void pause() {
		gw.pause();
		playpause.setText("Play");
		timer.cancel();
		position.setEnabled(true);
		accelerate.setEnabled(false);
		left.setEnabled(false);
		breakk.setEnabled(false);
		right.setEnabled(false);
		sound.setEnabled(false);
		ac.setEnable(false);

		accelerate.getDisabledStyle().setBgTransparency(250);
		accelerate.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		accelerate.getDisabledStyle().setFgColor(ColorUtil.BLACK);

		left.getDisabledStyle().setBgTransparency(250);
		left.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		left.getDisabledStyle().setFgColor(ColorUtil.BLACK);

		breakk.getDisabledStyle().setBgTransparency(250);
		breakk.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		breakk.getDisabledStyle().setFgColor(ColorUtil.BLACK);

		right.getDisabledStyle().setBgTransparency(250);
		right.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		right.getDisabledStyle().setFgColor(ColorUtil.BLACK);

		removeKeyListener('a', ac);
		removeKeyListener('b', bc);
		removeKeyListener('l', lc);
		removeKeyListener('r', rc);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		gw.gameClock(20);

	}
}
