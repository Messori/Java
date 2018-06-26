package battleSpace1;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public abstract class GenericEntity {

	protected double x,xspeed,y,yspeed;
	protected JFrame frame;
	protected BufferedImage sprite;
	protected boolean hasSprite;
	
	//Costruttore che poi verrà ereditato
	public GenericEntity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public GenericEntity(String bufferedImageUrl,double x,double y,JFrame frame) {
		try {
			sprite=ImageIO.read(getClass().getResource(bufferedImageUrl));
			hasSprite=true;
		} catch (IOException e) {
			hasSprite=false;
		}
		this.frame=frame;
		this.x=x;
		this.y=y;
		xspeed=0;
		yspeed=0;
		
	}
	
	//Funzione update che poi verrà ereditatà
	public void update() {
		//impediamo di andare fuori dalla schermo tenendo conto che prende le coordinate dell'angolo in alto a sinistra
		if(x<0)
			x=0;
		if(y<0)
			y=0;
		if(x>frame.getWidth()-sprite.getWidth())
			x=frame.getWidth()-sprite.getWidth();
		if(y>frame.getHeight()-1.5*sprite.getHeight())
			y=frame.getHeight()-1.5*sprite.getHeight();
		//effettivo spostamento
		x+=xspeed;
		y+=yspeed;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getXspeed() {
		return xspeed;
	}

	public void setXspeed(double xspeed) {
		this.xspeed = xspeed;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getYspeed() {
		return yspeed;
	}

	public void setYspeed(double yspeed) {
		this.yspeed = yspeed;
	}
	
	public Rectangle getBounds() {
		return (new Rectangle((int)x,(int)y,sprite.getWidth(),sprite.getHeight()));
	}
	
	public boolean checkCollision(GenericEntity ge2) {
		return (this.getBounds().intersects(ge2.getBounds()));
	}
	
}
