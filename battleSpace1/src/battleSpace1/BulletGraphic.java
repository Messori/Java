package battleSpace1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class BulletGraphic {

	protected List<Bullet> bullets;
	protected JFrame frame;
	
	public BulletGraphic(JFrame frame) {
		this.frame=frame;
		bullets=new ArrayList<Bullet>();
	}
	
	public void addBullet(String spriteUrl,double x,double y,double xspeed,double yspeed) {
		Bullet b=new Bullet(spriteUrl,x,y,frame);
		
		b.setXspeed(xspeed);
		b.setYspeed(yspeed);
		bullets.add(b);
	}
	
	public void update() {
		for(int i=0;i<bullets.size();i++) {
			bullets.get(i).update();
			 if(bullets.get(i).y<0 || bullets.get(i).y>frame.getHeight())
				bullets.remove(i);
		}
	}
	
	public void entityCollision(GenericEntity entity) {
		for(int i=0;i<bullets.size();i++) {
			if(bullets.get(i).checkCollision(entity)) {
				bullets.remove(i);
				if(entity instanceof Enemy) 
					((Enemy) entity).hit();
				if(entity instanceof PlayerShip)
					((PlayerShip) entity).hit();
			}
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		//for each
		for(Bullet b:bullets)
			b.paint(g2d);
		
	}
}
