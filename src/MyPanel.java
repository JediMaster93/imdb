import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	
	public final static int HEIGHT_PER_ELEMENT = 50;
	private ArrayList<MovieElement> movieList;
	private float speed = 5.0f;
	private float slowdownAmount = 0.01f;
	private Timer updateTimer;
	public MyPanel()
	{
		movieList = new ArrayList<>();
		updateTimer = new Timer();
		movieList.add(new MovieElement(this, new Rectangle2D.Double(0, 0, this.WIDTH, 50),"0"));
		for(int i = 1; i < 200; i++)
		{
			movieList.add(new MovieElement(this, new Rectangle2D.Double(0, movieList.get(i- 1).rec.y - 50, this.WIDTH, 50), Integer.toString(i)));
			
		}
		TimerTask t = new TimerTask() {
			
			@Override
			public void run() {
				update();
			}
		};
		updateTimer.scheduleAtFixedRate(t, 0, 16);
	}
	public void update()
	{
	  /*  for (MovieElement movie : movieList)
        {
            movie.moveY(speed);

        }*/
		for(int i = 0; i < 30; i++)
		{
			
			movieList.get(i).moveY(speed);
			System.out.println(i + " " + speed);
		}
        if(speed > 0)
        {
            speed -= slowdownAmount;
        }
        else
        {
            speed = 0;
            //GET DA WINNAR
            //Initate second state
        }

      /*  if(speed > 15 && speed < 20)
        {
            slowdownAmount = 0.07f;
        }
        if (speed > 10 && speed < 15)
        {
            slowdownAmount = 0.05f;
        }
        if (speed < 10)
        {
            slowdownAmount = 0.01f;
        }*/
        repaint();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (MovieElement movie : movieList) {
			
			movie.draw(g);
		}
		g.setColor(Color.red);
      //  g.drawLine(0, this.HEIGHT / 2 , this.WIDTH , this.HEIGHT / 2 );
        

		
	}
}
