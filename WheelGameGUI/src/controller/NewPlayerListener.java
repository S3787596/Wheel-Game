package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Values;
import model.interfaces.GameEngine;

import view.NewPlayerForm;

public class NewPlayerListener implements ActionListener 
{

    private GameEngine gameEngine;
    private Values v;
    private NewPlayerForm newPlayerForm;

    public NewPlayerListener(GameEngine gameEngine, Values v) 
    {
        this.gameEngine = gameEngine;
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (v.getNewPlayerForm() == null) 
        {
            newPlayerForm = new NewPlayerForm(gameEngine, v);
            v.setNewPlayerForm(newPlayerForm);
        }
    }
}
