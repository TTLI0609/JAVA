package pobj.motx.tme3.csp;

import java.util.*;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.*;


/**
 *
 */
public class DicoVariable implements IVariable{
	
	/** l’emplacement de mot correspondant */
	private int index; 
	/** un référence à une GrillePotentiel */
	private GrillePotentiel gp;
	
	
	public List<Dictionnaire> getMotsPot() {
		return gp.getMotsPot();
	}
	public List<IContrainte> getContraintes() {
		return gp.getContraintes();
	}



	/**
	 * construit un DicoVariable avec un index et une GrillePotentiel
	 * @param index entier representant l'index de l'emplecement du mot correspondant
	 * @param gp une GrillePotentiel 
	 */
	public DicoVariable(int index, GrillePotentiel gp) {
		this.index = index;
		this.gp = gp;
	}
	

	
	/**
	 * return une liste de String associé à l’emplacement du mot dans la GrillePotentiel correspondant à son domaine
	 */
	@Override
	public List<String> getDomain(){
		
		List<String> res = getMotsPot().get(index).getMots();
		
		return res;
	}

	/**
	 * @return la grille potentiel
	 */
	public GrillePotentiel getGp() {
		return gp;
	}
	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 *  Affichage du dictionnaire qui est associé à l’emplacement du mot dans la GrillePotentiel
	 */
	@Override
	public String toString() {
		List<String> list = getDomain();
		
		StringBuilder res = new StringBuilder();
		for (String s : list) {
			res.append(s + "\n");
		}
		return res.toString();

	}
}
