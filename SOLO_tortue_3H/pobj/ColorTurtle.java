package pobj.tme6;

import javafx.scene.paint.Color;

public class ColorTurtle extends Turtle implements IColorTurtle{
	
	private Color color;
	
	public ColorTurtle() {
		super();
		color = Color.BLACK;
	}

	
	public Color getColor() {
		return color;
	}


	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	void draw(int x1, int y1, int x2, int y2) {
		System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+color);
	}
	
	

}
