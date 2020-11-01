package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Case;

public class EnsembleLettre {
	
	/** liste de lettres */
	private List<Character> lettres = new ArrayList<Character>();

	
	/**
	 * ajouter une lettre (sans doublon)
	 * @param c le char à ajouter
	 */
	public void add(char c) {
		if(! lettres.contains(c))
			lettres.add(c);
	}
	
	/**
	 * @return la liste de lettres
	 */
	public List<Character> getLettres() {
		return this.lettres;
	}
	
	
	/**
	 * @return le nombre d'element dans la liste
	 */
	public int size() {
		return lettres.size();
	}
	
	
	/**
	 * Calcul l'ensemble d'intersection de la liste courant avec la liste passer en argument
	 * @param ens l'ensemble à conparer
	 * @return l'ensemble d'intersection
	 */
	public EnsembleLettre intersection(EnsembleLettre ens) {
		EnsembleLettre res = new EnsembleLettre();
		
		for (char c : this.lettres) {
			if(ens.lettres.contains(c)) {
				res.add(c);
			}
		}	
		return res;
	}
	
	/**
	 * déterminer la présence d’une lettre particulière
	 * @param c lettre à determiner la presence
	 * @return true si c est dans la liste 
	 */
	public boolean contains(char c) {
		return this.lettres.contains(c);
	}
	
}
