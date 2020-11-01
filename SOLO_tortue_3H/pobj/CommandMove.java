package pobj.tme6;

public class CommandMove implements ICommand {
	private int length;

	public CommandMove(int l) {
		length = l;
	}

	@Override
	public void execute(IColorTurtle turtle) {
		turtle.move(length);
	}

}
