package view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.ExitActionListener;
import controller.PlayerActionListener;

import model.Values;
import model.interfaces.GameEngine;

import view.AppFrame;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{
	public MenuBar(AppFrame frame, GameEngine gameEngine, Values v)
	{
		JMenu jMenu = new JMenu("File");

		jMenu.setMnemonic(KeyEvent.VK_I);

		JMenuItem addItem = new JMenuItem("AddPlayer", KeyEvent.VK_O);
		addItem.addActionListener(new PlayerActionListener(gameEngine, v));
		JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitItem.addActionListener(new ExitActionListener());
		exitItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_DOWN_MASK));

		jMenu.add(addItem);
		jMenu.addSeparator();
		jMenu.add(exitItem);
		this.add(jMenu);
	}
}