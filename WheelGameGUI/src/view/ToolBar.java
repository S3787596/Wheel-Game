package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.PlayerActionListener;
import controller.ResultActionListener;
import controller.SpinListener;
import model.interfaces.GameEngine;
import model.Values;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar
{
	public ToolBar(GameEngine gameEngine, Values v)
	{
		setLayout(new GridLayout(0, 3, 5, 5));
		JButton spinButton = new JButton("Spin!");
		spinButton.addActionListener(new SpinListener(gameEngine));
		
		JButton playButton = new JButton("Players");
		playButton.addActionListener(new PlayerActionListener(gameEngine, v));
		
		JButton previousButton = new JButton("Previous Result");
		previousButton.addActionListener(new ResultActionListener(v, gameEngine));
		
		add(spinButton);
		add(playButton);
		add(previousButton);
		
		setFloatable(false);
	}
}
