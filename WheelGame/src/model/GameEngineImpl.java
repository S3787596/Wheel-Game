package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine
{

	final int wheel_size = Slot.WHEEL_SIZE;

	private List<GameEngineCallback> gameEngCallbackList = new ArrayList<GameEngineCallback>();
	private Map<String, Player> MapPlayer = new HashMap<String, Player>();
	private List<Slot> slotList = new ArrayList<Slot>();


	@Override
    public void spin(int initialDelay, int finalDelay, int delayIncrement) {

        /** Random the start location based on the wheel size **/
        int x = (int) (Math.random() * Slot.WHEEL_SIZE);
        
        while(finalDelay > initialDelay) {
            try {
                Thread.sleep(initialDelay);

                for (GameEngineCallback currentGEC : gameEngCallbackList) {
                    currentGEC.nextSlot(slotList.get(x), this);
                }
                
                x++;
                
                if(x == Slot.WHEEL_SIZE) {
                    x = 0;
                }
                
                initialDelay += delayIncrement; // Increase the delay

                /** Check if delay >= finaldelay, interrupt if true **/
                if (initialDelay >= finalDelay) {
                    Thread.currentThread().interrupt();
                }
                
            } catch (InterruptedException e) {
                break; // When delay is interrupt, will break out from the loop
            }
        }

        for(GameEngineCallback currentGEC : gameEngCallbackList) {
            currentGEC.result(slotList.get(x), this); // The ball fall into the position is base on count
        }
    }
	

	@Override
	public void calculateResult(Slot winningSlot)
	{
		BetType b;
		for(String key: MapPlayer.keySet()) 
		{			
			b = MapPlayer.get(key).getBetType();
			b.applyWinLoss(MapPlayer.get(key), winningSlot);	
		}

	}

	@Override
	// adds the player to the hashmap
	public void addPlayer(Player player)
	{
		MapPlayer.put(player.getPlayerId(), player);
	}

	@Override
	//gets the player from the hashmap
	public Player getPlayer(String id)
	{
		return MapPlayer.get(id);
	}

	@Override
	//removes play from the hashmap
	public boolean removePlayer(Player player)
	{
		if (MapPlayer.containsValue(player))
		{
			MapPlayer.values().remove(player);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		this.gameEngCallbackList.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		return this.gameEngCallbackList.remove(gameEngineCallback);
	}

	@Override
	// gets all the players form the hashmap
	public Collection<Player> getAllPlayers()
	{ 
		return Collections.unmodifiableCollection(MapPlayer.values());
	}

	@Override
	//places the bet depending on the bet and amount of points the player has.
	public boolean placeBet(Player player, int bet, BetType betType)
	{
		if(player.setBet(bet) && betType != null)
		{
			player.setBetType(betType);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Slot> getWheelSlots()
	{	
		Color color = null;

        /** Array of number for the roulette **/
        String[] number = { "00","27","10","25","29","12","8","19","31","18","6","21","33","16","4","23","35","14","2","0","28","9","26","30","11",
                "7","20","32","17","5","22","34","15","3","24","36","13","1" };

        /** For loop to determine the position colour and add into slot collection **/
        for (int position = 0; position < Slot.WHEEL_SIZE; position++) {
            
            if (number[position].equals("00")) {
                color = Color.GREEN00;
            } else if (number[position].equals("0")) {
                color = Color.GREEN0;
            } else if (position % 2 == 0) {
                color = Color.BLACK;
            } else {
                color = Color.RED;
            }

            slotList.add(new SlotImpl(position, color, Integer.parseInt(number[position])));
        }
        
        return slotList;

	}

}
