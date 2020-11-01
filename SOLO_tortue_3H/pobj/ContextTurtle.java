package pobj.tme6;

public class ContextTurtle extends ColorTurtle implements IColorTurtle {
	
	private IContext c;
	
	public ContextTurtle(IContext c) {
		this.c = c;
	}

	@Override
	void draw(int x1, int y1, int x2, int y2) {
		c.drawLine(x1, y1, x2, y2, getColor());
	}
	

}
