package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool {
	private double x_press,x_release,y_press,y_release;
	private double x_deb, y_deb;
	private String name;
	
	
	public ToolSelection() {
		x_deb=0; y_deb=0;
		x_press=0;
		y_release=0;
		x_press=0;
		y_release=0;
		name = "Select tool";
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x_deb=e.getX(); y_deb = e.getY();
		x_press = e.getX();
		y_press = e.getY();
		
		if(e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(), x_press, y_press);
		}else {
			i.getSelection().select(i.getBoard(), x_press, y_press);
		}
		

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		x_release = e.getX();
		y_release = e.getY();	
		for(Clip cl : i.getSelection().getContents()) {
			cl.move(x_release-x_press, y_release-y_press);
			//Command cmd =  new CommandMove(i.getSelection().getContents(), x_release-x_deb, y_release-y_deb);
			//i.getUndoStack().addCommand(cmd);
		}

		
		x_press = e.getX();
		y_press = e.getY();	
	
	
	
	
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		Command cmd =  new CommandMove(i.getSelection().getContents(),x_release-x_deb, y_release-y_deb);
		i.getUndoStack().addCommand(cmd);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
		i.getSelection().drawFeedback(gc);
	}
	
	

	@Override
	public String getName(EditorInterface editor) {
		return name;
	}

}
