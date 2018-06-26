package battleSpace1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable,KeyListener{
		private static final long serialVersionUID = 1L;
		
		private PlayerShip player;
		private Thread animator;
		private boolean running=false;
		//private boolean gameOver=false;
		private BufferedImage background;
		private long lastTime;//ultima volta che ho sparato
		private JFrame frame;
		private ArrayList<Enemy> enemy;
		private BulletGraphic friendlyFire,enemyFire;
		boolean enemyAlive = true;
		
		public GamePanel(JFrame frame) {
			try {
				background=ImageIO.read(getClass().getResource("Background-4.png"));
			} catch (IOException e) {
				this.setBackground(Color.BLACK);
			}
			this.frame=frame;
			friendlyFire=new BulletGraphic(frame);
			
			enemyFire=new BulletGraphic(frame);
			enemy=new ArrayList<Enemy>();
			
			/*
			 * Meglio inserire le coordinate con dei calcoli che con dei numeri
			 */
			player=new PlayerShip("shipResized.png",frame.getWidth()/2-40,frame.getHeight()-100,frame);
			enemy.add(new Enemy("ship.png",frame.getWidth()/2-40,30,frame));
			enemy.add(new Enemy("ship.png",0,30,frame));
			enemy.add(new Enemy("ship.png",frame.getWidth()-20,30,frame));
			
			lastTime=0;
		}
		/*
		 * Ereditato dalla classe JComponent
		 * Uso per aspettare che il panel sia inserito nel frame
		 * N.B. si esegue automaticamente, non va chiamato
		 */
		public void addNotify() {
			super.addNotify();
			startGame();
		}
		
		/*
		 * StartGame crea un nuovo Thread
		 */
		public void startGame() {
			if(animator==null || !running) {
				animator=new Thread(this);
				animator.start();
			}
			
		}
		
		public void stopGame() {
			running=false;
		}
		
		public void run() {
			long sleepTime;
			running=true;
			while(running) {
				update();
				repaint();
				sleepTime=5; 
				////valore di sleeptime sostanzialmente imposta la velocità del gioco 
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					
				}
			}
			System.exit(0);
		}

		/**
		 * TODO:Da sistemare
		 * 			 
		 */
		
		public void fire() {
			
			if(System.nanoTime()-lastTime>350000000) { //sparo 1 colpo ogni 0.035 secondi
			
				friendlyFire.addBullet("BF.png",player.getX()+23,player.getY()-12,0,-2.5);
			}
			lastTime=System.nanoTime();
		}
		
		public void update() {
			enemyAlive = false;
			if(!player.playerDead()) { //il gioco aggiorna fintanto che il player è vivo
				player.update();
				for(int i=0;i<enemy.size();i++) {
					if(!enemy.get(i).isDead()) {
						enemyAlive = true;
						enemy.get(i).update();
						friendlyFire.entityCollision(enemy.get(i));
						enemyFire.entityCollision(player);
						if(this.enemy.get(i).randomFire()) 
							enemyFire.addBullet("enemybullet.png", enemy.get(i).getX()+24, enemy.get(i).getY()+70, 0, 1.5);
						
					}
				}
				friendlyFire.update();
				enemyFire.update();	
				
				//se sono finiti i nemici
				if (!enemyAlive) {
					Object[] op= {"Nuova partita","Basta Così"};
					JOptionPane pane=new JOptionPane("Che facciamo?)",JOptionPane.INFORMATION_MESSAGE);
					pane.setOptions(op);
					JDialog dialog=pane.createDialog( "Hai vinto!");
					dialog.setVisible(true);
					if(pane.getValue()==null)
						System.exit(0);
					if(pane.getValue().equals(op[1]))
						System.exit(0);
					//nuova partita
					if(pane.getValue().equals(op[0]))
						this.reset();
					
					this.running=false;					
				}
				
			}
			else {
				Object[] op= {"Nuova partita","Basta Così"};
				JOptionPane pane=new JOptionPane("Che facciamo?)",JOptionPane.INFORMATION_MESSAGE);
				pane.setOptions(op);
				JDialog dialog=pane.createDialog( "Ops Sei Morto");
				dialog.setVisible(true);
				if(pane.getValue()==null)
					System.exit(0);
				if(pane.getValue().equals(op[1]))
					System.exit(0);
				//nuova partita
				if(pane.getValue().equals(op[0]))
					this.reset();
				
				this.running=false;
			}
		}
		
		/*
		 * Disegna tutti gli sprite che ci servono
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		public void paintComponent(Graphics g) {
			Graphics2D g2d=(Graphics2D)g;
			super.paintComponent(g2d);
			g2d.drawImage(background, 0, 0, null);
			player.paint(g2d);
			for(int i=0;i<enemy.size();i++) {
				if(!enemy.get(i).isDead())
					enemy.get(i).paint(g2d);
			}
			friendlyFire.paint(g2d);
			enemyFire.paint(g2d);
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			player.pressed(e.getKeyCode());
			if(e.getKeyCode()==KeyEvent.VK_SPACE)
				fire();
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			player.released(e.getKeyCode());
			
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			return;
		}
		
		/**
		 * TODO 
		 */
		public void reset() {
			this.startGame();
			this.run();
			player.reset();
			
			//se sono state rimosse come le recuperiamo?
			for(int i=0;i<enemy.size();i++) {
					enemy.get(i).reset();
			}
			repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
		}
}

