package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.GameEngineImpl;
import model.Values;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class AppFrame extends JFrame 
{
	
	public AppFrame()
	{
		super("Wheel Game"); //console title
		
		GameEngineCallbackImpl callback = new GameEngineCallbackImpl();
		Values v = new Values();
		GameEngine gameEngine = new GameEngineImpl();
		MenuBar menuBar = new MenuBar(null, gameEngine, v);
		StatusBar statusBar = new StatusBar();
		ToolBar toolBar = new ToolBar(gameEngine, v);
		WheelDisplay wheelDisplay = new WheelDisplay(gameEngine);
	
		GameEngineCallbackGUI gui = new GameEngineCallbackGUI(statusBar, v, wheelDisplay);
		
		
		 // Adding callback
		gameEngine.addGameEngineCallback(gui);
		gameEngine.addGameEngineCallback(callback);
		gameEngine.getWheelSlots();
		
		
		
		  //Adding buttons 
		setJMenuBar(menuBar);
		add(wheelDisplay, BorderLayout.CENTER);
		add(statusBar, BorderLayout.NORTH);
		add(toolBar, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
