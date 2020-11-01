package pobj.tme6;

import java.util.*;

public class CommandList implements ICommand {

	private List<ICommand> list = new ArrayList<ICommand>();
	
	public void addCommand(ICommand command) {
		list.add(command);
	}
	
	@Override
	public void execute(IColorTurtle turtle) {
		for(int i=0;i<list.size();i++) {
			list.get(i).execute(turtle);
		}

	}

}
