package pobj.pinboard.document;

import java.util.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
		/** un ensemble d'éléments graphiques */
		private List<Clip> listClip ;
		/** la couleur par defaut qui est noire*/
		private Color c = Color.BLACK; 
		
		
		/**
		 * construit une planche vide
		 */
		public Board() {
			listClip = new ArrayList<Clip>();
		}
		
		
		public List<Clip> getContents(){
			return listClip;
		}
		
		public void addClip(Clip clip) {
			listClip.add(clip);
		}
		public void addClip(List<Clip> clip) {
			listClip.addAll(clip);
		}
		public void removeClip(Clip clip) {
			listClip.remove(clip);
		}
		public void removeClip(List<Clip> clip) {
			listClip.removeAll(clip);
		}

		public void draw(GraphicsContext gc) {
			gc.setFill(Color.WHITE);
			gc.fillRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());
			
			for (Clip c : listClip) {
				c.draw(gc);
			}
		}
		
		public void setColor(Color c) {
			this.c = c;
		}
		public Color getColor() {
			return c;
		}

}
