package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;

public class Selection {
	/** une liste d’objets Clip sélectionnés*/
	private List<Clip> listClip = new ArrayList<>();

	/**
	 * modélise une sélection simple au point (x,y)
	 * @param board la planche de dessin
	 * @param x 
	 * @param y
	 */
	public void select(Board board, double x, double y) {
		clear();	//on vide la selection
		List<Clip> l = new ArrayList<>();
		l = board.getContents();
		
		for(Clip e: l) {	//parcours tous les clip de board
			if(e.isSelected(x, y)) {
				listClip.add(e);
				break;
			}
		}
	}

	/**
	 * modélise une sélection multiple au point (x,y)
	 * @param board la planche de dessin
	 * @param x
	 * @param y
	 */
	public void toogleSelect(Board board, double x, double y) {
		//clear();
		
		for(Clip e: board.getContents()) {
			
			if(e.isSelected(x, y)) {

				if(!listClip.contains(e)) { //si cesr pas dans la selection, on l'ajoute
					listClip.add(e);
					
				}
				//marche pas avce le else ??
				else {								//si cest deja dans la selection, on retire
					listClip.remove(e);
				}
			}
		}

	}

	/**
	 * vide la sélection
	 */
	public void clear() {
		listClip.clear();
	}

	/**
	 * @return retourne la sélection
	 */
	public List<Clip> getContents(){
		return listClip;


	}
	
	/**
	 * dessine le rectangle englobant de tous les éléments de la sélection
	 * @param gc 
	 */
	public void drawFeedback(GraphicsContext gc){
		gc.setFill(Color.DARKVIOLET);
		for(Clip e : listClip ) {
			if (e instanceof ClipRect)
				gc.fillRect(e.getLeft(), e.getTop(), e.getRight()-e.getLeft(), e.getBottom()-e.getTop()); 
			if ( e instanceof ClipImage) 
				gc.fillRect(e.getLeft(), e.getTop(), e.getRight(), e.getBottom()); 
			if (e instanceof ClipEllipse) 
				gc.fillOval(e.getLeft(),e.getTop(), e.getRight()-e.getLeft() ,e.getBottom()-e.getTop());
				}
				/*
			for(Clip c : listClip) {
				c.setColor(Color.DARKVIOLET);
				c.draw(gc);
			}
			*/
			/*
			Clip tmp = e.copy();
			tmp.setColor(Color.DARKVIOLET);
			tmp.draw(gc);
			*/
		
	}
}
