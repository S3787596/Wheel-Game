package model;
import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player
{

	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private BetType BetType = null;

	public SimplePlayer(String playerId, String playerName, int points) 
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = points;
	}

	
	@Override
	public String getPlayerName() 
	{
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		playerName = this.playerName;
	}

	@Override
	public int getPoints() 
	{
		return this.points;
	}

	@Override
	public void setPoints(int points) 
	{
		points = this.points;
	}

	@Override
	public String getPlayerId() 
	{
		return this.playerId;
	}

	@Override
	public boolean setBet(int bet) 
	{	
		if (bet > 0 && (this.points - bet >= 0)) 
			{
				this.bet = bet; // Set the bet amount if enough points
				return true;
			}
		
			resetBet();
			return false;
	}

	@Override
	public int getBet() 
	{
		return this.bet;
	}

	@Override
	public void setBetType(BetType betType) 
	{
		this.BetType = betType;
	}

	@Override
	public BetType getBetType() 
	{
		return BetType;
	}

	@Override
	public void resetBet() 
	{
		this.BetType = null;
		this.bet = 0;
	}

	public String toString() 
	{
		return String.format("Player: id=%s, name=%s, bet=%s, betType=%s, points=%d", this.playerId, this.playerName,
                this.getBet() > 0? this.getBet() : "/", this.getBet() > 0? this.getBetType() : "/", this.points);
	}
}
