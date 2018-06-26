package battleSpace1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class PlayerShip extends Ship {
	private boolean isDead;
	private int life;
	
	private static double VelDefX=5;
	private static double VelDefY=5;
	
	private double startingX;
	private double startingY;
	
	
	public PlayerShip(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		life = 3;
		isDead=false;
	}
	
	public PlayerShip(String string, int x, int y, JFrame frame) {
		// TODO Auto-generated constructor stub
		super(string,x,y,frame);
		life = 3;
		isDead=false;
		startingX=x;
		startingY=y;
		
	}

	public void pressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_W:
			this.xspeed=0;
			this.yspeed=- VelDefY;
			break;
		case KeyEvent.VK_S:
			this.xspeed=0;
			this.yspeed=VelDefY;
			break;
		case KeyEvent.VK_D:
			this.xspeed=VelDefX;
			this.yspeed=0;
			break;
		case KeyEvent.VK_A:
			this.xspeed=-VelDefX;
			this.yspeed=0;
			break;
		case KeyEvent.VK_UP:
			this.xspeed=0;
			this.yspeed=-VelDefY;
			break;
		case KeyEvent.VK_DOWN:
			this.xspeed=0;
			this.yspeed=VelDefY;
			break;
		case KeyEvent.VK_RIGHT:
			this.xspeed=VelDefX;
			this.yspeed=0;
			break;
		case KeyEvent.VK_LEFT:
			this.xspeed=-VelDefX;
			this.yspeed=0;
			break;
		}
	}
	
	public void released(int keyCode) {
		if(keyCode==KeyEvent.VK_W ||keyCode==KeyEvent.VK_A ||keyCode==KeyEvent.VK_D ||keyCode==KeyEvent.VK_S) {
			this.xspeed=0;
			this.yspeed=0;
		}
		if(keyCode==KeyEvent.VK_LEFT ||keyCode==KeyEvent.VK_RIGHT ||keyCode==KeyEvent.VK_DOWN ||keyCode==KeyEvent.VK_UP) {
			this.xspeed=0;
			this.yspeed=0;
		}
	}

	public void hit() {
		life--;
		if(life<=0) {
			isDead=true;
		}
	}
	public boolean playerDead() {
		return this.isDead;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(this.sprite, (int)x, (int)y, sprite.getWidth(),sprite.getHeight(), null);
		
	}
	
	public void reset() {
		yspeed=0;
		xspeed=0;
		life = 3;
		isDead=false;
		this.x = startingX;
		this.y = startingY;
	}

}
