import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


public class MovieElement {

	public Rectangle2D.Double rec;
	private String txt;
	MyPanel p;
	public MovieElement(MyPanel p,Rectangle2D.Double rec, String txt)
	{
		this.p = p;
		this.rec = rec;
		this.txt = txt;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		
		g2d.drawRect((int)rec.x, (int)rec.y, p.getWidth(), MyPanel.HEIGHT_PER_ELEMENT);
        g2d.drawString(txt,(int) (rec.x + rec.width / 2), (int) (rec.y + rec.height / 2)); //Dem coordinates
	}
	public void moveY(float value)
	{
		rec.y += value;
		

	}
	public boolean containts(int x, int y)
	{
		return rec.contains(x, y);
	}

}
