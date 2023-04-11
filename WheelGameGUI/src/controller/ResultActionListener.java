package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Values;
import model.interfaces.GameEngine;
import view.DisplayResult;

public class ResultActionListener implements ActionListener 
{
    private GameEngine gameEngine;
    private Values v;

    public ResultActionListener(Values v, GameEngine gameEngine) 
    {
        this.gameEngine = gameEngine;
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
        DisplayResult display = new DisplayResult(v, gameEngine);
        display.initFrame();
    }
}