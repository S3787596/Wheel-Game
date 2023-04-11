package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddPlayerListener;
import controller.FrameListener;
import controller.PlayerKeyListener;

import model.Values;
import model.interfaces.GameEngine;

public class NewPlayerForm  
{

    /** The add new player form **/
    public NewPlayerForm(GameEngine gameEngine, Values v) 
    {
        JFrame jFrame = new JFrame();
        JPanel contentPanel = new JPanel(new GridLayout(2, 0));
        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        infoPanel.add(new JLabel("Player Name:"));
        JTextField nameField = new JTextField();
        infoPanel.add(nameField);
        infoPanel.add(new JLabel("Initial Points:"));
        JTextField pointField = new JTextField();
        infoPanel.add(pointField);
        
        pointField.addKeyListener(new PlayerKeyListener());

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new AddPlayerListener(nameField, pointField, jFrame, v, gameEngine));
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new FrameListener(jFrame, v));
        buttonPanel.add(cancelButton);
        
        contentPanel.add(infoPanel);
        contentPanel.add(buttonPanel);
        jFrame.add(contentPanel);

        jFrame.pack();
        jFrame.setTitle("Add a new Player");
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setSize(150, 150);
        jFrame.setLocationRelativeTo(null);
    }
}
