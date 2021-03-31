package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.TextSelection.Char;
import com.codename1.ui.events.ActionEvent;
import java.lang.*;
/**
 * Controller class that controls the flow of the game that allows for user input by extending Form
 * @author manhsy
 */

//game is controller 
//gameworld is the model 
//map and score view is the view 
public class Game extends Form{	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;

	/**
	 * Constructor
	 */

	public Game() {
		gw = new GameWorld(); //game world is observable
		mv = new MapView(); //map view is an observer
		sv = new ScoreView(); //score view is an observer
		gw.addObserver(mv); //register the map observer
		gw.addObserver(sv); //register the score observer

		
		//edit the main form 
		this.setLayout(new BorderLayout());
		Toolbar toolBar = new Toolbar();
		this.setToolbar(toolBar); //set form's tool bar to tool bar 
		this.setTitle("ThePath Game"); //set title of the form 
		
		//establish commands 
		AccelerateCommand ac = new AccelerateCommand(gw);
		LeftCommand lc = new LeftCommand(gw);
		BreakkCommand bc = new BreakkCommand(gw);
		RightCommand rc = new RightCommand(gw);
		CollideWithFlagCommand cwfc = new CollideWithFlagCommand(gw);
		CollideWithSpiderCommand cwsc = new CollideWithSpiderCommand(gw);
		CollideWithFoodStationCommand cwfsc = new CollideWithFoodStationCommand(gw);
		TickCommand tc = new TickCommand(gw);
		AboutCommand about = new AboutCommand();
		ExitCommandd exit = new ExitCommandd();
		CheckBoxCommand soundC = new CheckBoxCommand(gw, toolBar);
		HelpCommand helpC = new HelpCommand();
		
		//establish all the button and their connect them to their perspective commands 
		CustomButton accelerate = new CustomButton("Accelerate", ac);
		CustomButton left = new CustomButton("Left", lc);
		CustomButton breakk = new CustomButton("Break", bc);
		CustomButton right = new CustomButton("right", rc);
		CustomButton cwf = new CustomButton("Collide With Flag", cwfc);
		CustomButton cws = new CustomButton("Collide with Spider", cwsc);
		CustomButton cwsf = new CustomButton("Collde with Food Station", cwfsc);
		CustomButton tick = new CustomButton("Tick", tc);
		CustomButton help = new CustomButton("Help", helpC);

		//to add command to toolbar use toobar.addCommandTo();
		//combine button with key
		addKeyListener('a', ac);
		addKeyListener('b', bc);
		addKeyListener('l', lc);
		addKeyListener('r', rc);
		addKeyListener('f', cwfsc);
		addKeyListener('g', cwsc);
		addKeyListener('t', tc);
				
		//components for side menu 
		toolBar.addCommandToLeftSideMenu(ac); //add command to left side menu of tool bar 
		CheckBox sound = new CheckBox("Sound");
		sound.setCommand(soundC);
		sound.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		sound.getAllStyles().setBgTransparency(255);
		sound.getAllStyles().setBgColor(ColorUtil.WHITE);
		toolBar.addComponentToLeftSideMenu(sound); //add command to left side menu of tool bar 
		toolBar.addCommandToLeftSideMenu(about); //add command to left side menu of tool bar 
		toolBar.addCommandToLeftSideMenu(exit);

		toolBar.add(BorderLayout.EAST, help);
		
		//make containers for different regions of form 
		Container cmdLeft = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container cmdRight = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container cmdBot = new Container(new FlowLayout(Component.CENTER));
		
		//style the containers
		cmdLeft.getAllStyles().setBorder(Border.createLineBorder(2));
		cmdRight.getAllStyles().setBorder(Border.createLineBorder(2));
		cmdBot.getAllStyles().setBorder(Border.createLineBorder(2));
		cmdLeft.getAllStyles().setPadding(Component.TOP, 55);
		cmdRight.getAllStyles().setPadding(Component.TOP, 55);
		
		//add buttons the containers
		cmdLeft.add(accelerate);
		cmdLeft.add(left);
		
		cmdRight.add(breakk);
		cmdRight.add(right);
		
		cmdBot.add(cwf);
		cmdBot.add(cws);
		cmdBot.add(cwsf);
		cmdBot.add(tick);
		
		//add the containers to the form 
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.WEST, cmdLeft);
		add(BorderLayout.EAST, cmdRight);
		add(BorderLayout.SOUTH, cmdBot);
		add(BorderLayout.CENTER, mv);
		
		//code here to create Command objects for each command
		//add commands to side menu and title bar areas, bind commands to keys, 
		//create control containers for the buttons, add button to the control containers
		//add command to the button, and add control containers, MapView and ScoreView to the form 
		//code here query MapView's width and height and set them as world's width and height
	
		show();
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.init(false);
	}


}



