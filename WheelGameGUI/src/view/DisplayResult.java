package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FrameListener;
import model.Values;
import model.interfaces.GameEngine;
import model.interfaces.Player;


public class DisplayResult
{
	private Values value;
	private GameEngine gameEngine;
	private DefaultTableModel defaultTablemodel;
	
	public DisplayResult(Values value, GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		this.value = value;
	}
	
	public void initFrame()
	{
		JFrame jFrame = new JFrame();
		JPanel contentPanel = new JPanel(new BorderLayout());
		JPanel resultPanel = new JPanel(new GridLayout(0, 6));
		JPanel buttonPanel = new JPanel();
		
		String position = "";
		String color = "";
		String number = "";
		
		if(value.getWinningSlot() !=null)
		{
			position = String.valueOf(value.getWinningSlot().getPosition());
			color = String.valueOf(value.getWinningSlot().getColor()); 
			number = String.valueOf(value.getWinningSlot().getNumber());
		}
		
		resultPanel.add(new JLabel("Position: "));
		resultPanel.add(new JLabel(position));
		resultPanel.add(new JLabel("Color: "));
		resultPanel.add(new JLabel(color));
		resultPanel.add(new JLabel("Number: "));
		resultPanel.add(new JLabel(number));
		
		if (gameEngine.getAllPlayers().size() > 0)
		{
			String[] name = { "ID", "Name", "Bet Amount", "Bet Color", "Points"};
			
			defaultTablemodel = new DefaultTableModel();
			defaultTablemodel.setColumnIdentifiers(name);
			JTable jTable = new JTable(defaultTablemodel);
			
			getTableData();
			
			contentPanel.add(new JScrollPane(jTable), BorderLayout.CENTER);
		}
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new FrameListener(jFrame));
		
		buttonPanel.add(closeButton);
		contentPanel.add(resultPanel, BorderLayout.PAGE_START);
		contentPanel.add(buttonPanel, BorderLayout.PAGE_END);
		
		jFrame.add(contentPanel);
		jFrame.pack();
		jFrame.setVisible(true);
        jFrame.setTitle("Previous Result");
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
	}
	
	public void getTableData()
	{
		for (Player player : gameEngine.getAllPlayers())
		{
			Vector<String> v = new Vector<String>();
			v.add(player.getPlayerId());
			v.add(player.getPlayerId());
			v.add(String.valueOf(player.getBet()));
			v.add(String.valueOf((player.getBetType() == null)? "-" : player.getBetType()));
			v.add(String.valueOf(player.getPoints()));
			defaultTablemodel.addRow(v);
		}
	}
}
