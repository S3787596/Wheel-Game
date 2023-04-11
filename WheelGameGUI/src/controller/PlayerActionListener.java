package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Values;
import model.interfaces.GameEngine;
import view.PlayerGUI;

public class PlayerActionListener implements ActionListener 
{
    private GameEngine gameEngine;
    private Values v;
    private PlayerGUI playerGUI;

    public PlayerActionListener(GameEngine gameEngine, Values v) 
    {
        this.gameEngine = gameEngine;
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (v.getPlayerGUI() == null) 
        {
            playerGUI = new PlayerGUI(gameEngine, v);
            v.setPlayerGUI(playerGUI);
        }
    }
}
