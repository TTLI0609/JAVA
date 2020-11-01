package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage  implements Clip {
	private double left, top;
	private Image image;
	private File fname;
	
	public ClipImage(double left , double top , File fname){
		this.left = left;
		this.top = top; 
		this.fname = fname ;
		image = new Image("file://" + fname.getAbsolutePath());
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(image, left, top);
	}

	@Override
	public Clip copy() {
		return new ClipImage(left, top, fname);
	}

	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return top + image.getHeight();
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return left + image.getWidth();
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
	}

	@Override
	public void move(double x, double y) {
		this.left = x;
		this.top = y;
	}

	@Override
	public boolean isSelected(double x, double y) {
		if( (left <= x) && (x <= getRight()) && (top <= y) && (y <= getBottom()))
			return true;
		else 
			return false ;
	}

	@Override
	public void setColor(Color c) {
	}

	@Override
	public Color getColor() {
		return null;
	}


}
