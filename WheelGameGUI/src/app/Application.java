package app;


import javax.swing.SwingUtilities;

import view.AppFrame;

public class Application {

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new AppFrame();
			}
		});

	}

}