package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command {

	/** l'interface sur laquel on travaille */
	private EditorInterface editor;
	/** le regroupement des clips */
	private ClipGroup groupe;
	
	public CommandUngroup(EditorInterface editor, ClipGroup groupe) {
		this.editor = editor;
		this.groupe = groupe;
	}


	
	@Override
	public void execute() {
		editor.getBoard().removeClip(groupe);				// enlever le clipGroupe
		editor.getBoard().addClip(groupe.getClips());		// ajouter la liste des clips
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(groupe.getClips());		// enlever la liste des clips (qui sont séparrer)
		editor.getBoard().addClip(groupe);						// ajouter le clipGroupe
	}

}
