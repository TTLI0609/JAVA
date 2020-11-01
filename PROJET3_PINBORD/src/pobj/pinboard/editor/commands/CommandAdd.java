package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command {

	/** l'interface sur laquel on travaille */
	private EditorInterface editor;
	/** les clips à ajouter*/
	private List<Clip> toAdd = new ArrayList<>();
	
	
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.editor = editor;
		this.toAdd.add(toAdd);
	}
	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		this.editor = editor;
		this.toAdd.addAll(toAdd);
	}
	
	@Override
	public void execute() {
		editor.getBoard().addClip(toAdd);
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(toAdd);
	}

}
