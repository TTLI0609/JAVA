package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup  implements Composite {

	/*liste de Clip composant le groupe */
	private List<Clip> clips = new ArrayList<>();


	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip c : clips) {	// dessine chaque Clip
			c.draw(ctx);
		}
	}

	@Override
	public double getTop() {	//retourne le plus grand Top parmi tous les clips
		double top = clips.get(0).getTop();
		for (Clip c : clips) {
			if(c.getTop() < top)
				top = c.getTop();		
		}
		return top;
	}

	@Override
	public double getLeft() {
		double left = clips.get(0).getLeft();
		for (Clip c : clips) {
			if(c.getLeft() < left)
				left = c.getLeft();		
		}
		return left;
	}

	@Override
	public double getBottom() {
		double bottom = clips.get(0).getBottom();
		for (Clip c : clips) {
			if(c.getBottom() > bottom )
				bottom = c.getBottom();		
		}
		return bottom;
	}

	@Override
	public double getRight() {
		double right = clips.get(0).getRight();
		for (Clip c : clips) {
			if(c.getRight() > right )
				right = c.getRight();		
		}
		return right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		for(Clip c : clips) {	// on fait setGeometry pour chaque Clip
			c.setGeometry(left, top, right, bottom);
		}
	}

	@Override
	public void move(double x, double y) {
		for(Clip c : clips) {	// on fait move pour chaque Clip
			c.move(x, y);
		}
	}

	@Override
	public boolean isSelected(double x, double y) {	
		//le rectangle englobant d’un groupe est le plus petit rectangle 
		//qui englobe les rectangles englobant de tous ses éléments
		
		if(x> getLeft() && x < getRight() && y>getTop() && y<getBottom())
			return true;
		return false;
	}

	@Override
	public void setColor(Color c) {
		for(Clip clip : clips) {	
			clip.setColor(c);
		}
	}

	@Override
	public Color getColor() { //chaque clip a la meme couleur
		return clips.get(0).getColor();
	}

	@Override
	public Clip copy() {
		ClipGroup copy = new ClipGroup();
		for(Clip c : clips) 
			copy.addClip(c.copy());
		return copy;
	}

	
	//methode de Composoite
	@Override
	public List<Clip> getClips() {
		return clips;
	}


	@Override
	public void addClip(Clip toAdd) {
		clips.add(toAdd);
	}

	public void addClip(List<Clip> toAdd) {
		clips.addAll(toAdd);
	}

	
	@Override
	public void removeClip(Clip toRemove) {
		clips.remove(toRemove);	
	}



}
