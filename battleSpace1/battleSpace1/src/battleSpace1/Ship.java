package battleSpace1;

import javax.swing.JFrame;

/**
 * Per il momento inutile, da tenere se si trova codice in comune tra la classe PlayerShip e la classe Enemy, altrimenti rimuovere
 * @author Matteo
 *
 */
public abstract class Ship extends GenericEntity {

	public Ship(double x, double y) {
		super(x, y);
	}
	
	public Ship(String bufferedImageUrl,double x, double y, JFrame frame) {
		super(bufferedImageUrl,x,y,frame);
	}

}
