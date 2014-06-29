import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Double;


public class MovieElement {

	public RoundRectangle2D.Double rec;
	private String txt;
	MyPanel p;
	public RoundRectangle2D.Double originalRectangle;
	public MovieElement(MyPanel p,RoundRectangle2D.Double rec, String txt)
	{
		this.p = p;
		this.rec = rec;
		this.txt = txt;
		this.rec.width = p.getWidth();
		this.originalRectangle = (Double) rec.clone();
	}
	
	public void draw(Graphics g)
	{
		this.rec.width = p.getWidth();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(rec);
		System.out.println(txt);
		//g2d.drawRect((int)rec.x, (int)rec.y, p.getWidth(), MyPanel.HEIGHT_PER_ELEMENT);
		g2d.setFont(new Font("Arial",Font.PLAIN, 20 ));
        g2d.drawString(txt,(int) (rec.x ), (int) (rec.y + rec.height / 2)); //Dem coordinates
	}
	public void moveY(float value)
	{
		rec.y += value;
		

	}
	public boolean containts(int x, int y)
	{
		return rec.contains(x, y);
	}
	public void resetPosition()
	{
		this.rec = (Double) originalRectangle.clone();
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
	

}
