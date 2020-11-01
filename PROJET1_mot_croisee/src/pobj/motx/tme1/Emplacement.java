package pobj.motx.tme1;
import java.util.*;

/**
 * Classe représentant une liste de cases formant un mot dans une grille
 * @author ttLI
 */
public class Emplacement {
	
	/** liste de cases  */
	private List<Case> lettres;
	
	
	/**
	 * construit une liste de cases
	 * @param lettres une liste de cases
	 */
	public Emplacement(List<Case> lettres) {
		this.lettres= lettres;
	}
	
	
	/**
	 * @return la liste de case
	 */
	public List<Case> getLettres(){
		return lettres;
	}
	
	/**
	 * @return la longueur de la liste
	 */
	public int size() {
		return lettres.size();
	}
	
	
	/**
	 * Affiche la liste de case 
	 */
	@Override 
	public String toString() {
		StringBuilder res = new StringBuilder();
		
		
		for(Case c : lettres) {
			res.append(c.getChar());
		}
		
		return res.toString();
	}
	
	
	/**
	 * @return true si l'emplacement a un case vide
	 */
	public boolean hasCaseVide() {
		
		for (Case c : lettres) {
			if( c.isVide() ) {
				return true ;
			}
		}
		return false;
	}
}