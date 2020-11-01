package pobj.tme6;

import javafx.scene.paint.Color;

public class ColorTurtleAdapter implements IColorTurtle{
	private ITurtle t;
	
	public ColorTurtleAdapter(ITurtle t) {
		this.t = t;
	}

	public void move(int length) {
		t.move(length);
	}

	public void turn(int angle) {
		t.turn(angle);
	}

	public void up() {
		t.up();
	}

	public void down() {
		t.down();
	}
	@Override
	public void setColor( Color color) {
	}

	
}
