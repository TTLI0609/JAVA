package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip  {

	
	/**
	 * construit un rectangle avec les coordonées et une couleur 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @param color
	 */
	public ClipRect(double left, double top, double right, double bottom, Color color) {
		super(left,top,right,bottom,color);
	}

	
	// Drawing
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(),getTop(), getRight()-getLeft(),getBottom()-getTop());
	}
	
	@Override
	public Clip copy() {
		return new ClipRect(new Double(getLeft()), new Double(getTop()), new Double(getRight()), new Double(getBottom()), getColor());
	}

	
	
}
