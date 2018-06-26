package battleSpace1;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Bullet extends GenericEntity{

	public Bullet(String spriteUrl, double x, double y, JFrame frame) {
		super(spriteUrl,x,y,frame);
	}
	
	public void update() {
		x+=xspeed;
		y+=yspeed;
	}

	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(sprite, (int)x, (int)y, sprite.getWidth(), sprite.getHeight(), null);
	}

}
