package pobj.motx.tme1;

/**
 *Classe representant une case d'une grille 
 */
public class Case {
	
	/** position de la case dans la grille */
	private int lig,col;
	/** Contenue de la case */
	private char c;
	
	
	/**
	 *  Construit une case par défaut
	 */
	public Case() {
		lig=0;
		col=0;
		c='a';
	}
	
	/** 
	 * Construit une case avec des données spécifiés 
	 * @param lig l'abscisse de la case
	 * @param col l'ordonnée de la case
	 * @param c   le contenu iniciale de la case 
	 */
	public Case (int lig, int col, char c) {
		this.lig=lig;
		this.col=col;
		this.c=c;	
	}
	
	
	/**
	 * Accède à l'abscisse de la case
	 * @return l'abscisse du position de la case
	 */
	public int getLig() {
		return lig;
	}
	
	/**
	 * Accède à l'ordonnée de la case
	 * @return l'ordonnée du position de la case
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Accède au contenu de la case
	 * @return le contenu du position de la case
	 */
	public char getChar() {
		return c;
	}
	
	
	/**
	 * Changer le contenu de la case
	 * @param c valeur à changer
	 */
	public void setChar(char c) {
		this.c=c;
	}
	
	
	/**
	 * Verifier si une case est vide ( contenant ' ')
	 * @return true si la case est vide
	 */
	public boolean isVide() {
		if( c == ' ') 
			return true;
		else 
			return false;
	}
	
	/**
	 * Verifier si une case est pleine ( contenant '*')
	 * @return true si la case est pleine
	 */
	public boolean isPleine() {
		if( c == '*')
			return true;
		else 
			return false;
	}
	
	
}
