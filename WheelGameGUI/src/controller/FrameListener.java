package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.Values;

public class FrameListener implements ActionListener 
{

    private JFrame jFrame;
    private Values v;

    public FrameListener(JFrame jFrame, Values v) 
    {
        this.jFrame = jFrame;
        this.v = v;
    }

    public FrameListener(JFrame jFrame) 
    {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        this.jFrame.setVisible(false);
        
        if (v != null) {
            v.setPlayerGUI(null);
            v.setNewPlayerForm(null);
        }
    }
}
