package battleSpace1;

import javax.swing.JFrame;

public abstract class Ship extends GenericEntity {

	public Ship(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Ship(String bufferedImageUrl,double x, double y, JFrame frame) {
		super(bufferedImageUrl,x,y,frame);
	}

}
