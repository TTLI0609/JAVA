package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command {
	/** l'interface sur laquel on travaille */
	private EditorInterface editor;
	/** le regroupement des clips */
	private ClipGroup groupe;
	
	
	public CommandGroup(EditorInterface editor, List<Clip> clips) {
		this.editor = editor;
		
		// construit le ClipGroupe avec clips
		this.groupe = new ClipGroup();
		this.groupe.addClip(clips);
		
	}

	
	@Override
	public void execute() {
		editor.getBoard().removeClip(groupe.getClips());	// enlever les clips qui sont séparer
		editor.getBoard().addClip(groupe);					// ajouter le ClipGroup 
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(groupe);				// enlever le ClipGroup 
		editor.getBoard().addClip(groupe.getClips());		// ajouter les clips qui sont séparer
		
	}

}
