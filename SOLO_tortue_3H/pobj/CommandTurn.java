package pobj.tme6;

public class CommandTurn implements ICommand {

	private int deg;
	public CommandTurn(int d) {
		deg =d;
	}
	
	@Override
	public void execute(IColorTurtle turtle) {
		turtle.turn(deg);
	}

}
