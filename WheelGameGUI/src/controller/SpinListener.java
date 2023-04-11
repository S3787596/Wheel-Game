package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;

public class SpinListener implements ActionListener
{

    private GameEngine gameEngine;

    public SpinListener(GameEngine gameEngine) 
    {
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        new Thread() 
        {
            public void run() 
            {
                gameEngine.spin(1, 500, 25);
            }
        }.start();
    }
}
