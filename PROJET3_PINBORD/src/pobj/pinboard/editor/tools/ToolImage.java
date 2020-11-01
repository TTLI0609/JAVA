package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolImage implements Tool {

	private double x,y;
	private File file;
	private ClipImage ci;
	private String name;
	
	public ToolImage(File file){
		this.file = file;
		name = "Click on a spot to put the image.";
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x = e.getX();
		y = e.getY();
		ci = new ClipImage(x, y, file);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		Board br = i.getBoard();
		x = e.getX();
		y = e.getY();
		ci.move(x, y);
		br.addClip(ci);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		Board br = i.getBoard();
		br.removeClip(ci);
		Clip add = new ClipImage(x, y, file);
		br.addClip(add);		//ajoute l'image
		i.getUndoStack().addCommand(new CommandAdd(i, add));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return name;
	}

}
