package pobj.motx.tme2;

public class CroixContrainte implements IContrainte {
	
	/** indice ((m1, c1),(m2, c2)) */
	private int m1,c1, m2,c2;
	
	
	/**
	 * construit CroixContrainte avec les 4 indices
	 * @param m1 numéro de l'emplacement 1
	 * @param c1 case 
	 * @param m2
	 * @param c2
	 */
	public CroixContrainte (int m1, int c1, int m2, int c2) {
		this.m1 = m1;
		this.c1 = c1;
		this.m2 = m2;
		this.c2 = c2;
	}


	@Override
	public int reduce(GrillePotentiel grille) {
		int nbFiltre = 0;
		
			//l’ensemble des lettres l1 / l2 pouvant figurer dans la case c1 
			//de l’emplacement m1 / m2 d’après les mots potentiels pour cet emplacement
			EnsembleLettre l1 = grille.getMotsPot().get(this.m1).calculLettrePosition(this.c1);
			EnsembleLettre l2 = grille.getMotsPot().get(this.m2).calculLettrePosition(this.c2);
			
			
			//s = l1 ∩ l2
			EnsembleLettre s = l1.intersection(l2);
			
		
			if(l1.size() > s.size()) {
				nbFiltre += grille.getMotsPot().get(this.m1).filtreParIndice(s, this.c1);				
			}
			if(l2.size() > s.size()) {
				nbFiltre += grille.getMotsPot().get(this.m2).filtreParIndice(s, this.c2);		
			}
			
		return nbFiltre;
	}
	
	/**
	 * @return true si les deux contraintes sont égaux
	 */
	@Override
	public  boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if (getClass() != obj.getClass())
		    return false;
		
		CroixContrainte other = (CroixContrainte) obj;
		
		if(this.m1 == other.m1 && this.m2==other.m2 && this.c1==other.c1 && this.c2==other.c2)
			return true;
		else 
			return false;
		
	}
}
