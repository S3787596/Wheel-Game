package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Slot;

@SuppressWarnings("serial")
public class WheelDisplay extends JPanel 
{
    private Image image;
    private ImageObserver imageObserver;
    private float degrees = 1;
    private int post = 0;
    HashMap<Integer, Integer> de = new HashMap<Integer, Integer>();
    private GameEngine gameEngine;

    public WheelDisplay(GameEngine gameEngine) 
    {
        ImageIcon icon = new ImageIcon("images\\Basic_roulette_wheel_1024x1024.png");
        image = icon.getImage();
        imageObserver = icon.getImageObserver();
        this.setGameEngine(gameEngine);
        setDegree(post);
    }

    /** Credit to stackoverflow forum : https://stackoverflow.com/questions/25923480/simple-circle-rotation-simulate-motion **/
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int circleDiameter = Math.min(getWidth(), getHeight());
        double circleRadius = circleDiameter / 2;
        int offSetX = (getWidth() - circleDiameter) / 2;
        int offSetY = (getHeight() - circleDiameter) / 2;
        
        g2d.drawImage(image, offSetX, offSetY, circleDiameter, circleDiameter, imageObserver);
        
        g2d.setColor(Color.YELLOW);
        int ballDiameter = (int) (circleDiameter * 0.02);
        int ballRadius =  ballDiameter / 2;

        Point p = getPointOnCircle(this.degrees, circleRadius * 0.9, circleRadius);

        int valueX = offSetX + p.x - ballRadius;
        int valueY = offSetY + p.y - ballRadius;
        g2d.fillOval(valueX, valueY, ballDiameter, ballDiameter);

        g2d.dispose();
    }
    
    private Point getPointOnCircle(float degress, double circleRadius, double innerCircleRadius) 
    {
       
        double rads =  ((Math.PI  * degress) / Slot.WHEEL_SIZE);
        
        int xCordinate = Math.round((float) (innerCircleRadius + Math.cos(rads) * circleRadius));
        int yCordinate = Math.round((float) (innerCircleRadius + Math.sin(rads) * circleRadius));
        
        return new Point(xCordinate, yCordinate);
    }
    
    
    public void setDegree(int x) 
    {
        this.degrees += 2;
        this.post = x;
    }

	public GameEngine getGameEngine()
	{
		return gameEngine;
	}

	public void setGameEngine(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
	}
}
