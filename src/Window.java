import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class Window extends JFrame {

	public Window() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JPanel p = new JPanel();
		MyPanel myPanel = new MyPanel();
		p.setLayout(new BorderLayout());
		p.add(myPanel, BorderLayout.CENTER);
	
		JButton b = new JButton("Re-Roll");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.resetRoll();
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(b);
		buttonPanel.setSize(100, 600);
		p.add(buttonPanel, BorderLayout.SOUTH);
		
		
		this.add(p);
	}
	public static void main(String[] args) {
		
		Window w = new Window();
		w.revalidate();
		
	}
}
