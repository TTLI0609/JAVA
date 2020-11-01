package pobj.motx.tme1;

import java.util.*;

/**
 * Classe qui explorer une Grille pour trouver tous les emplacements des mots qu’ellecontient
 */
public class GrillePlaces {
	
	/**grille initial */
	private Grille grille;
	/** liste d'emplacement*/
	private List<Emplacement> places = new ArrayList<Emplacement>();
	/** nombre de mot horizontal dans une grille donnée */ 
	private int nbHorizontal=0;
	
	
	/**
	 * Construit GrillePlace avec une grille donné
	 * @param grille
	 */
	public GrillePlaces (Grille grille) {
		this.grille = grille;
		
		for(int i = 0; i<grille.nbLig(); i++) {	//chercher les emplacements de mots horizontaux
			cherchePlaces(grille.getLig(i));
		}
		nbHorizontal = getPlaces().size();
		
		for(int j = 0; j<grille.nbCol(); j++) {
			cherchePlaces(grille.getCol(j));
		}
	}
	
	
	/**
	 * @return la liste d'emplacement 
	 */
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	/**
	 * @return le nombre de mot horizontal dans la grille
	 */
	public int getNbHorizontal() {
		//getPlaces().size() =nb tot mots détectés
		return nbHorizontal;
	}
	
	/**
	 * affiche chaque emplacement 
	 */
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Emplacement e : places) {
			res.append(e.toString() + "\n/FIN");
		}
		return res.toString();
	}


	
	/**
	 * Cherche tous les mots contenus dans une liste cases ( qui peut soit être une ligne de la grile ou un colonne)
	 * @param cases liste de case
	 */
	 private void cherchePlaces(List<Case> cases) {
		 Emplacement e = new Emplacement(new ArrayList<Case>());
		 
		 for(int i = 0 ; i<cases.size(); i++) {
			 if(! cases.get(i).isPleine()) {
				 e.getLettres().add(cases.get(i));
			 }
			 else{
					 if(e.getLettres().size() >= 2) {
						 places.add(e);
						 e = new Emplacement(new ArrayList<Case>());
					 }else {
						 e = new Emplacement(new ArrayList<Case>());		//réinitialise la liste
					 }
			 }
		 }
		 if((e.getLettres().size()) >= 2 ) {
			 places.add(e);
		 }
	 }
	 
	/**
	 * ajouter un mot solution dans la grille à un index donné
	 * @param m indice dans la liste des emplacements de mots de la grille telle que retournée par getPlaces()
	 * @param soluce le mot solution
	 * @return une nouvelle grille où les cases constituant l’emplacement de mot d’indice m ont pour contenu les lettres de soluce
	 */
	public GrillePlaces fixer(int m, String soluce) {
		//copie de la grille stockée
		Grille g = this.grille.copy();
		GrillePlaces gp=  new GrillePlaces (g);
		
		int i =0;
		for(Case c : gp.places.get(m).getLettres()) {	//on itère sur chaque cases de l'emplacement d'index m
			c.setChar(soluce.charAt(i));	
			i++;
		}
			
		return gp;	
	}
	
	
	
}
