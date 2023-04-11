package view;

import java.util.Collection;

import javax.swing.SwingUtilities;

import model.Values;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private StatusBar statusBar;
	private Values value;
	private WheelDisplay wheelDisplay;

	public GameEngineCallbackGUI(StatusBar statusBar, Values value, WheelDisplay wheelDisplay)
	{
		this.statusBar = statusBar;
		this.value = value;
		this.wheelDisplay = wheelDisplay;	
	}
	
	@Override
	public void nextSlot(Slot slot, GameEngine engine)
	{
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				wheelDisplay.setDegree(slot.getPosition());
				wheelDisplay.repaint();
			}
		});
		
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine)
	{
		// TODO Auto-generated method stub
		wheelDisplay.setDegree(winningSlot.getPosition());
		DisplayResult display = new DisplayResult(value, engine);
		
		statusBar.setWinColor(String.valueOf(winningSlot.getColor()));
		statusBar.setWinNumber(String.valueOf(winningSlot.getNumber()));
		
		engine.calculateResult(winningSlot);
		display.initFrame();
		resetBet(engine.getAllPlayers());
	}
	
	private void resetBet(Collection<Player> playerList)
	{
		if (playerList.size() > 0) 
		{
			for (Player player : playerList)
				player.resetBet();
		}
	}

}
