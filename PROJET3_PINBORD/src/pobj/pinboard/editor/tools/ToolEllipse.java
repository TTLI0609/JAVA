package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;


import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool {

	private double deb_x, deb_y;
	private double fin_x, fin_y;
	private String name;


	public ToolEllipse() {
		deb_x = 0;
		deb_y = 0;
		fin_x = 0;
		fin_y = 0;
		name = "Filled ellipse tool";
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		deb_x= e.getX();
		deb_y= e.getY();
	}
	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		fin_x= e.getX();
		fin_y= e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		Board b = i.getBoard();
		Color c = b.getColor();		// couleur 
		
		if(deb_x < fin_x && deb_y > fin_y) { // cas 1 : mvt haut-droite
			Clip add = new ClipEllipse(deb_x,e.getY() ,e.getX(), deb_y, c );
			i.getBoard().addClip(add);
			i.getUndoStack().addCommand(new CommandAdd(i, add));
		}
		else if (deb_x > fin_x && deb_y < fin_y) { //cas 2 : mvt bas-gauche
			Clip add =new ClipEllipse(e.getX(), deb_y, deb_x, e.getY(), c );
			i.getBoard().addClip(add);
			i.getUndoStack().addCommand(new CommandAdd(i, add));
		}
		else if(deb_x > fin_x &&  deb_y > fin_y) { // cas 3 :  mvt haut-gauche	
			Clip add =new ClipEllipse(e.getX(),e.getY(),deb_x,deb_y, c );
			i.getBoard().addClip(add);
			i.getUndoStack().addCommand(new CommandAdd(i, add));
		}
		else { //cas 4 :  mvt bas-droite
			Clip add =new ClipEllipse(deb_x,deb_y,e.getX(),e.getY(), c );
			i.getBoard().addClip(add);
			i.getUndoStack().addCommand(new CommandAdd(i, add));
		}
	}
	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		//set couleur du contour
		gc.setStroke(Color.DARKRED);

		if (deb_x > fin_x && deb_y > fin_y)
			gc.strokeOval(((fin_x+deb_x)/2) - ((deb_x-fin_x)/2),((fin_y+deb_y)/2) - ((deb_y - fin_y)/ 2) ,(deb_x - fin_x),(deb_y - fin_y)); 
		if (deb_x > fin_x && deb_y < fin_y)
			gc.strokeOval(((fin_x+deb_x)/2) - ((deb_x-fin_x)/2),((deb_y+fin_y)/2) - ((fin_y - deb_y)/ 2) ,(deb_x - fin_x),(fin_y - deb_y));  
		if(deb_x < fin_x && deb_y > fin_y)
			gc.strokeOval(((deb_x+fin_x)/2) - ((fin_x-deb_x)/2),((fin_y+deb_y)/2) - ((deb_y - fin_y)/ 2) ,(fin_x - deb_x),(deb_y - fin_y)); 
		if(deb_x < fin_x && deb_y < fin_y)
			gc.strokeOval(((deb_x+fin_x)/2) - ((fin_x-deb_x)/2),((deb_y+fin_y)/2) - ((fin_y - deb_y)/ 2) ,(fin_x - deb_x),(fin_y - deb_y));  
	}

	@Override
	public String getName(EditorInterface editor) {
		return name;
	}


}
