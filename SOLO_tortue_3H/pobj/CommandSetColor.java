package pobj.tme6;

import javafx.scene.paint.Color;

public class CommandSetColor implements ICommand {
	
	private Color c;
	
	public CommandSetColor(Color co) {
		c=co;
	}
	@Override
	public void execute(IColorTurtle turtle) {
		turtle.setColor(c);

	}

}
