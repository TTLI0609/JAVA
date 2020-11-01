package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	private List<Command> redo = new ArrayList<>();
	private List<Command> undo = new ArrayList<>();
	

	public void addCommand(Command cmd) {
		//redo.clear();	// pas de redo
		//undo.clear();
		//cmd.execute();	// execute la commande
		undo.add(cmd);	// ajouter la commande qu'on vient de faire dans undo
	}
	
	public void undo() {
		if(isUndoEmpty())
			return ;
		Command cmd = undo.remove(undo.size()-1); //la derniere commande qu'on a fait et on l'enleve de undo
		cmd.undo();		// undo de la command qu'on a passé
		redo.add(cmd);	// on l'ajoute dans redo
	}
	
	public void redo() {
		if(isRedoEmpty())
			return;
		Command cmd = redo.remove(redo.size()-1); //la derniere commande qu'on a fait
		cmd.execute();		// on l'a ré-execute
		undo.add(cmd);	// on l'ajoute dans undo
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}
	
	
	public List<Command> getRedo() {
		return redo;
	}

	public List<Command> getUndo() {
		return undo;
	}
	

}