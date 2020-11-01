package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {

	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left,top,right,bottom,color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillOval(getLeft(),getTop(), getRight()-getLeft() ,getBottom()-getTop());
		
	}
	
	
	@Override 
	public boolean isSelected(double x, double y) {
		double cx = (getLeft() + getRight()) / 2;
		double cy = (getTop() + getBottom()) / 2;
		double rx = (getRight() - getLeft()) / 2;
		double ry = (getBottom() - getTop()) / 2;
		
		double tmp = (((x - cx)/rx)*((x - cx)/rx))+ (((y-cy)/ry)*((y-cy)/ry));
		if(tmp <= 1) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public Clip copy() {
		return new ClipEllipse(getLeft(), getTop(), getRight(), getBottom(), getColor());
	}
	
	

}
