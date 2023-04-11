package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlaceBetListener implements ActionListener 
{

    private GameEngine gameEngine;
    private Player player;
    private JComboBox<BetType> comboBox;
    private JTextField textField;

    public PlaceBetListener(Player player, GameEngine gameEngine, JComboBox<BetType> comboBox, JTextField textField) 
    {
        this.player = player;
        this.gameEngine = gameEngine;
        this.comboBox = comboBox;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        validateBet();
    }

    private void validateBet() 
    {
        boolean valid = false;
        
        if (textField.getText().length() > 0 && comboBox.getSelectedItem() != null) 
        {
            if (Integer.valueOf(textField.getText()) > 0) 
            {
                valid = gameEngine.placeBet(player, Integer.valueOf(textField.getText()), BetType.valueOf(comboBox.getSelectedItem().toString()));
                
                if(valid) 
                {
                    JOptionPane.showMessageDialog(null, "Bet Placed.", "Success.", 1);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Insufficient points!", "Failed.", 0);
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Bet amount cannot be 0!", "Failed.", 0);
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Invalid bet!", "Failed.", 0);
        }
    }
}
