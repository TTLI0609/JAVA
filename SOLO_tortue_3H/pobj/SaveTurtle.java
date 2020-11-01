package pobj.tme6;

import javafx.scene.paint.Color;

public class SaveTurtle implements IColorTurtle {
	
	private CommandList list = new CommandList();

	public CommandList getCommand() {
		return list;
	}

	@Override
	public void move(int length) {
		list.addCommand(new CommandMove(length));
	}

	@Override
	public void turn(int angle) {
		list.addCommand(new CommandTurn(angle));
	}

	@Override
	public void up() {
		list.addCommand(new CommandUp());
	}

	@Override
	public void down() {
		list.addCommand(new CommandDown());
	}

	@Override
	public void setColor(Color color) {
		list.addCommand(new CommandSetColor(color));
	}

}
