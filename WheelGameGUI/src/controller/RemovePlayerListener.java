package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.PlayerGUI;

public class RemovePlayerListener implements ActionListener 
{
    
    private Player player;
    private GameEngine gameEngine;
    private PlayerGUI playerGUI;

    public RemovePlayerListener(Player player, GameEngine gameEngine, PlayerGUI playerGUI) 
    {
        this.player = player;
        this.gameEngine = gameEngine;
        this.playerGUI = playerGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        gameEngine.removePlayer(player);
        playerGUI.redraw();
    }
}
