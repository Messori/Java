package battleSpace1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDHT=500,HEIGHT=700;
	private GamePanel panel;
	
	public Game() {
		super("Battle Space 1");
		this.setSize(WIDHT, HEIGHT);
		panel=new GamePanel(this);
		this.add(panel);
		
		this.setFocusable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.addKeyListener(panel);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
			new Game();
		}
		});
	}
}