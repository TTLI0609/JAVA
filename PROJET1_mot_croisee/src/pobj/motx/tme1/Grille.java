package pobj.motx.tme1;

import java.util.*;

/**
 * Classe représentant une grille de cases
 */
public class Grille {
	
	/** matrice de cases */
	private Case[][] Cases; 
	
	/**
	 * Construit une grille avec des paramétres
	 * @param hauteur 
	 * @param largeur
	 */
	public Grille(int hauteur, int largeur) {
		Cases = new Case[hauteur][largeur];
		for(int i=0;i<nbLig();i++) {
			for(int j=0;j<nbCol();j++) {
				Cases[i][j]= new Case( i, j, ' ' );
			}
		}
	}
	
	/**
	 * @param lig
	 * @param col
	 * @return la case à la position (lig, col) dans la matrice
	 */
	public Case getCase(int lig, int col) {
		return Cases[lig][col];
	}
	
	/**
	 * @param lig
	 * @return les cases qui constituent une ligne donnée (sans les copier)
	 */
	List<Case> getLig (int lig){
		List<Case> res = new ArrayList<Case>();
	
		for(int j=0;j<this.nbCol();j++) {
			res.add(Cases[lig][j]);
		}
		return res;
	}

	/**
	 * @param col
	 * @return les cases qui constituent une colonne donnée (sans les copier)
	 */
	List<Case> getCol(int col){
	
		List<Case> res = new ArrayList<Case>();
	
		for(int i=0;i<this.nbLig();i++) {
			res.add(Cases[i][col]);
		}
		return res;
	}


	
	/**
	 * Accède au nombre de ligne
	 * @return le nombre de ligne
	 */
	public int nbLig() {
		return Cases.length;
	}
	/**
	 * Accède au nombre de colonne
	 * @return le nombre de colonne
	 */
	public int nbCol() {
		return Cases[0].length;
	}
	
	

	/**
	 * Affichage de la grille
	 */
	@Override 
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}

	
	
	/**
	 * @return  une copie à l’identique de la grille courante
	 */
	public Grille copy() {
		Grille copie = new Grille(nbLig(), nbCol());
		
		for(int i=0;i<nbLig();i++) {
			for(int j=0;j<nbCol();j++) {
				copie.Cases[i][j]= new Case( getCase(i,j).getLig(), getCase(i,j).getCol(), getCase(i,j).getChar() );
			}
		}

		return copie;
	}
	
	
}