package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import model.SimplePlayer;
import model.Values;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class AddPlayerListener implements ActionListener
{
	private GameEngine gameEngine;
    private JTextField nameField;
    private JTextField pointField;
    private JFrame jFrame;
    private Values v;
    private int IDnum;

    public AddPlayerListener(JTextField nameField, JTextField pointField, JFrame jFrame, Values v, GameEngine gameEngine) 
    {
        this.nameField = nameField;
        this.pointField = pointField;
        this.jFrame = jFrame;
        this.gameEngine = gameEngine;
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        initialize(nameField.getText(), pointField.getText());
    }

    public void initialize(String name, String point) 
    {
        IDnum = gameEngine.getAllPlayers().size(); 

        if (name.length() > 0 && point.length() > 0) 
        {
            if (Integer.parseInt(point) > 0 && this.gameEngine != null) 
            {
                IDnum = (IDnum != 0) ? ++IDnum : 1;

                Player player = new SimplePlayer(String.valueOf(IDnum), name, Integer.parseInt(point));

                this.gameEngine.addPlayer(player); 

                /** If playerGUI form is close, there is no need to redraw be called **/
                if (v.getPlayerGUI() != null)  //
                {
                    v.getPlayerGUI().redraw();
                    v.setNewPlayerForm(null);
                }
                jFrame.setVisible(false); // Close the add player form after add new player.
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Add a new Player", "Error!", 0);
        }
    }
}
