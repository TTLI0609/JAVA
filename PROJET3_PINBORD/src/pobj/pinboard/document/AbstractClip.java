package pobj.pinboard.document;

import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip{

	/** coordonées */
	private double left,top,right,bottom;
	/** courleur de remplissage */
	private Color color;
	
	
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}
	
	public double getTop() {
		return top;
	}
	public double getLeft() {
		return left;
	}
	public double getBottom() {
		return bottom;
	}
	public double getRight() {
		return right;
	}
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	public void move(double x, double y) {
		setGeometry(left+x,top+y,right+x,bottom+y);
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	public Color getColor() {
		return color;
	}
	
	/***
	 * si le point (x, y) est dans  le rectangle englobant
	 */
	public boolean isSelected(double x, double y) {
		if( left <= x  && x <= right && top <= y && y<=bottom) { 
			return true;
		}
		return false;
	}


}
