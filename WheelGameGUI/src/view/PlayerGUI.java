package view;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FrameListener;
import controller.NewPlayerListener;
import controller.PlaceBetListener;
import controller.PlayerKeyListener;
import controller.RemovePlayerListener;

import model.Values;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlayerGUI  
{

    private JFrame jFrame;
    private JPanel playerTablePanel;
    private JPanel contentPanel;
    private GameEngine gameEngine;

    /** Main frame of the player view **/
    public PlayerGUI(GameEngine gameEngine, Values v) 
    {
        this.gameEngine = gameEngine;

        jFrame = new JFrame();
        contentPanel = new JPanel(new GridLayout(2, 0));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton addNewPlayerButton = new JButton("Add New Player");
        addNewPlayerButton.addActionListener(new NewPlayerListener(gameEngine, v));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new FrameListener(jFrame, v));

        buttonPanel.add(addNewPlayerButton);
        buttonPanel.add(closeButton);

        contentPanel.add(initPlayerTable());
        contentPanel.add(buttonPanel);
        jFrame.add(contentPanel);

        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setTitle("");
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
    }
 
    public JPanel initPlayerTable() 
    {
        playerTablePanel = new JPanel();
        Collection<Player> playerList = gameEngine.getAllPlayers();

        if (playerList.size() > 0) 
        {
            playerTablePanel.setLayout(new GridLayout(playerList.size(), 10));

            for (Player player : playerList) 
            {
                playerTablePanel.add(new JLabel("ID : " + player.getPlayerId()));
                playerTablePanel.add(new JLabel("Name : "));
                playerTablePanel.add(new JLabel(player.getPlayerName()));
                playerTablePanel.add(new JLabel("Points : "));
                playerTablePanel.add(new JLabel(String.valueOf(player.getPoints())));

                playerTablePanel.add(new JLabel("Bet type : "));
                JComboBox<BetType> comboBox = new JComboBox<BetType>(BetType.values());
                comboBox.setSelectedItem(player.getBetType());
                playerTablePanel.add(comboBox);

                playerTablePanel.add(new JLabel("Place bet : "));
                JTextField textField = new JTextField();
                textField.setText(String.valueOf(player.getBet()));
                playerTablePanel.add(textField);
                textField.addKeyListener(new PlayerKeyListener());
                
                JButton betButton = new JButton("Bet");
                betButton.addActionListener(new PlaceBetListener(player, gameEngine, comboBox, textField));
                playerTablePanel.add(betButton);
                
                JButton removeButton = new JButton("Remove Player");
                removeButton.addActionListener(new RemovePlayerListener(player, gameEngine, this));
                playerTablePanel.add(removeButton);
            }
        } 
        else 
        {
        	playerTablePanel.add(new JLabel("No players are in the game."));
        }
        return playerTablePanel;
    }
    
    public void redraw() 
    {
        contentPanel.remove(playerTablePanel);
        contentPanel.add(initPlayerTable(), 0, 0);
        contentPanel.revalidate();
        contentPanel.repaint();
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }
}
