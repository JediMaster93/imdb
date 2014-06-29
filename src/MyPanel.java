import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class MyPanel extends JPanel{
	
	public final static int HEIGHT_PER_ELEMENT = 50;
	private ArrayList<MovieElement> movieList;
	private float speed = 4.0f;
	private float slowdownAmount = 0.01f;
	private Timer updateTimer;
	private final int SPEED_BASE = 15;
	public MyPanel()
	{
		movieList = new ArrayList<>();
		updateTimer = new Timer(16, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();

			}
		});
		
		movieList.add(new MovieElement(this, new RoundRectangle2D.Double(0, 0, this.WIDTH, 50,10,10),"0"));
		try {
			initaliseFromExcell();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateTimer.start();
	
	}
	public void update()
	{
	  /*  for (MovieElement movie : movieList)
        {
            movie.moveY(speed);

        }*/
		for(int i = 0; i < movieList.size(); i++)
		{
			
			movieList.get(i).moveY(speed);
		}
        if(speed > 0)
        {
            speed -= slowdownAmount;
        }
        else
        {
        	stopRoll();
            //GET DA WINNAR
            //Initate second state
        }

        if(speed > 15 && speed < 20)
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
        }
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
        g.drawLine(0, this.getHeight() / 2 , this.getWidth() , this.getHeight() / 2 );

	}
	
	public void resetRoll()
	{
		//stopRoll();
		resetStartSpeed();
		Collections.shuffle(movieList);
		this.repositionMovieElements();
		updateTimer.start();
	}
	public void resetStartSpeed()
	{
		speed = SPEED_BASE;
		if((Math.random() * 100) < 50)
		{
			speed += Math.random() * 5;
		}
		else
		{
			speed -= Math.random() * 5;

		}
		System.out.println(speed);
	}
	public void repositionMovieElements()
	{
		movieList.get(0).rec.y = 0;
		for(int i = 1; i < movieList.size(); i++)
		{
			movieList.get(i).rec.y = movieList.get(i-1).rec.y - 50;
		}
	}
	public MovieElement findWinner()
	{
		for (MovieElement movie : movieList)
		{
			if(movie.rec.contains(new Point(this.getWidth() / 2, this.getHeight() / 2)))
			{
				return movie;
			}
		}
		return null;
	}
	public void stopRoll()
	{
		speed = 0;
		MovieElement winner = findWinner();
		System.out.println(winner.getTxt());
		updateTimer.stop();
		
		//POPUP WITH MOVIE STUFF!
		JFrame popup = new JFrame();
		popup.setSize(400,400);
		popup.setLocationRelativeTo(null);
		popup.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		JPanel popupPanel = new JPanel(new GridLayout(3,1));

		popupPanel.add(new JLabel("Movie  " +winner.getTxt() + " won!"));
		popupPanel.add(new JLabel("Trailer"));
		popupPanel.add(new JLabel("IMDB"));
		changeFont(popupPanel, new Font("Arial",Font.PLAIN, 20 ));
		
		popup.add(popupPanel);
		popup.setVisible(true);
	
	}
	
	public static void changeFont ( Component component, Font font )
	{
	    component.setFont ( font );
	    if ( component instanceof Container )
	    {
	        for ( Component child : ( ( Container ) component ).getComponents () )
	        {
	            changeFont ( child, font );
	        }
	    }
	}
	
	private  void initaliseFromExcell() throws FileNotFoundException, IOException {
		InputStream inp = new FileInputStream("250.xls");
	    
		Workbook wb = new HSSFWorkbook(inp);

	    Sheet sheet1 = wb.getSheetAt(0);
	    String str = "";
	    for (Row row : sheet1) 
	    {
	    	str = "";
	        for (Cell cell : row) 
	        {
	        	switch (cell.getCellType())
	        	{
                case Cell.CELL_TYPE_STRING:
                	str += " " + cell.getStringCellValue();
                	break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                    } else {
                    	str += " " + cell.getNumericCellValue() + " ";
               
                    }
                    break;

	        	}
	        	
	        }
	        if(str != "")
	        {
	        	movieList.add(new MovieElement(this, new RoundRectangle2D.Double(0, movieList.get(movieList.size()- 1).rec.y - 50, this.getWidth(), 50,10,10), str));
	        }
	    }

	}
}
