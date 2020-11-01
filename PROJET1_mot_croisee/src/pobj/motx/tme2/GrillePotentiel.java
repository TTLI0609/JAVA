package pobj.motx.tme2;

import java.util.*;

import pobj.motx.tme1.*;

/**
 * liste de mots potentiels d'une grille
 */
public class GrillePotentiel {
	
	/**  la grille actuelle */
	private GrillePlaces grille;	
	/** le dictionnaire français complet */
	private Dictionnaire dic;
	/**  le domaine de chaque emplacement de la grille, dans le même ordre que la grille */
	private List<Dictionnaire> motsPot = new ArrayList<Dictionnaire>();
	/** liste contraintes*/
	private List<IContrainte> contraintes = new ArrayList<IContrainte>();;
	
	
	/**
	 * Construit les domaines potentiels de tout les mots dans la grille
	 * @param grille
	 * @param dicoComplet dictionnaire français complet
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grille = grille;
		dic = dicoComplet;
		
		for (Emplacement e : this.grille.getPlaces()) {	//pour chaque mot dans la grille
			Dictionnaire tmp = dic.copy(); 
			tmp.filtreLongueur(e.size());				//filtrer les mots de taille differnt
			
			for (Case c : e.getLettres()) {
				if(! c.isPleine() && ! c.isVide()) 		// donc si la case contient une lettre 
					tmp.filtreParLettre(c.getChar(), e.getLettres().indexOf(c));
			}
			
			motsPot.add(tmp); 
		}
		
		//tester tous les emplacements horizontaux contr tous les emplacements verticaux.
		for(int i =0; i<grille.getNbHorizontal(); i++) {
			for (int j = grille.getNbHorizontal(); j< grille.getPlaces().size(); j++) {
				
				//pour chaque case des horizontal
				for(int k =0; k<grille.getPlaces().get(i).getLettres().size(); k++) {
					//pour chaque case des vertical
					for(int l = 0 ; l< grille.getPlaces().get(j).getLettres().size(); l++) {
						Case ch = grille.getPlaces().get(i).getLettres().get(k);
						Case cv = grille.getPlaces().get(j).getLettres().get(l);
						
						//si on a croisement
						if(ch.getLig()== cv.getLig() && ch.getCol() == cv.getCol()) {
							//si la case contient deja une lettre, on ne fait rien
							if(ch.isVide()) {
								CroixContrainte cont = new CroixContrainte(i,k,j,l);
								cont.reduce(this);
								this.contraintes.add(cont); 
							}
						}
						
				
						
					}
				}
				
			}
		}
		
		
		propage();
		
	}
	
	
	
	/**
	 * @return motsPot de la grille courant
	 */
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	/**
	 * @return la liste de contraintes
	 */
	public List<IContrainte> getContraintes() {
		return contraintes;
	}
	/**
	 * @return la grille actuelle
	 */
	public GrillePlaces getGrillePlaces() {
		return grille;
	}
	
	
	/**
	 * @return true s'il y a un emplacement de la grille qui a un un domaine potentiel vide.
	 */
	public boolean isDead() {
		for(Dictionnaire d : this.motsPot) {
			if(d.size() == 0) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 * initialise une nouvelle GrillePotentiel avec la grille résultant de l’affectation
	 * @param m indice dans la liste des emplacements de mots de la grille telle que retournée par getPlaces()
	 * @param soluce le mot solution
	 * @return une nouvelle GrillePotentiel après l'affectation 
	 * */
	public GrillePotentiel fixer(int m, String soluce) {
		GrillePotentiel nouv = new GrillePotentiel(this.grille.fixer(m, soluce), this.dic);
		return nouv;
	}
	
	/**
	 * itérer jusqu'à stabilité
	 * @return false quand c'est stable
	 */
	private boolean propage() {
		
		while (true) {
			int nbFiltre =0;
			for (IContrainte cc : this.contraintes){
				nbFiltre += cc.reduce(this);
			}
			
			if(this.isDead())
				return false;
			if(nbFiltre == 0)
				return false;
		}

	}



}
