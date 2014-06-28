import javax.swing.JFrame;


public class Window extends JFrame {

	public Window() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(new MyPanel());
	}
	public static void main(String[] args) {
		
		Window w = new Window();
		
	}
}
