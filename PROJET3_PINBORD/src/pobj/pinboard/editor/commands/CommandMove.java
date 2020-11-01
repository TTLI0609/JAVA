package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {
	
	/** l'interface sur laquel on travaille */
	private EditorInterface editor;
	/** les clips à bouger */
	private List<Clip> toMove = new ArrayList<>();
	/** coordonnées */
	private double x, y;
	
	public CommandMove(EditorInterface editor, Clip toMove, double x, double y) {
		this.editor = editor;
		this.toMove.add(toMove);
		this.x = x;
		this.y = y;
	}
	public CommandMove(List<Clip> toMove, double x, double y) {
		this.toMove.addAll(toMove);
		this.x = x;
		this.y = y;
	}

	
	@Override
	public void execute() {
		for(Clip c : toMove)
			c.move(x,y);
	}

	@Override
	public void undo() {
		for(Clip c : toMove)
			c.move(-x,-y);
	}
}
