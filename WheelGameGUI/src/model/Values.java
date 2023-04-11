package model;

import model.interfaces.Slot;
import view.NewPlayerForm;
import view.PlayerGUI;

public class Values 
{
    private PlayerGUI playerGUI;
    private NewPlayerForm newPlayerForm;
    
    private Slot winSlot;
    
    /** Store the winning result here, so player can view previous winning result any time **/
    public void setWinningSlot(Slot winSlot) 
    {
        this.winSlot = winSlot;
    }
    
    public Slot getWinningSlot() 
    {
        return this.winSlot;
    }
    
    /** Prevent multiple playerGUI **/
    public void setPlayerGUI(PlayerGUI playerGUI) 
    {
        this.playerGUI = playerGUI;
    }
    
    public PlayerGUI getPlayerGUI() 
    {
        return this.playerGUI;
    }
    
    /** Prevent multiple add new player form **/
    public void setNewPlayerForm(NewPlayerForm newPlayerForm) 
    {
        this.newPlayerForm = newPlayerForm;
    }
    
    public NewPlayerForm getNewPlayerForm() 
    {
        return this.newPlayerForm;
    }
}
