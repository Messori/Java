package battleSpace1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;
/*
 * Controllare anche metodo update
 */
public class Enemy extends Ship implements ActionListener {	
		//private Timer timer = new Timer(15, this);
		private boolean isDead=false;
		private int hitsTaken=0;
		protected JFrame frame;
		double xspeed=0;
		double yspeed=5;
		private double startingX;
		private double startingY;
		
		/*public Enemy(double x, double y) {
			super(x, y);
			xspeed = 0;
			yspeed = 10;
			x = frame.getWidth();
			Random random = new Random();
			y = random.nextInt(frame.getHeight());
		}*/
		
		public Enemy(String bufferedImageUrl,double x,double y,JFrame frame) {
			super(bufferedImageUrl,x,y,frame);
			startingX=x;
			startingY=y;
		}
		
		//non spariamo a intervalli di tempo regolari ma a caso in modo che ci siano dei buchi senza proiettili in cui colpire le navicelle
		public boolean randomFire() {
			Random rand = new Random();
			int r = rand.nextInt(100);
			boolean b;
			if (r == 1)
				b=true;
			else b = false;
			
			return b;
		}
		
		public boolean isDead() {
			return this.isDead;
		}
		
		public void hit() {
			this.hitsTaken++;
			if(this.hitsTaken>=2)
				this.isDead=true;
			
		}
		public void actionPerformed(ActionEvent e) {
			yspeed = 100;
			update();
			
		}
		public void actionListened() {
			
		}
		
		public void paint(Graphics g) {			
				Graphics2D g2d=(Graphics2D)g;
				g2d.drawImage(this.sprite, (int)this.x, (int)this.y, this.sprite.getWidth(), this.sprite.getHeight(), null);
			
		}
		
		/**
		 * TODO
		 */
		public void reset() {
			isDead=false;
			hitsTaken=0;
			double xspeed=0;
			double yspeed=5;
			x = startingX;
			y = startingY;
		}


}
