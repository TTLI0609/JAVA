package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.*;
import pobj.motx.tme2.*;

public class MotX implements ICSP{
	
	/** une liste de DicoVariable */
	private List<IVariable> dicoVar;
	
	/**
	 * construit MotX tq une liste de DicoVariable qui pour DicoVariable on a l'Emplacement correspondant de la grille comporte au moins une case vide
	 * @param gp GrillePotentiel
	 */
	public MotX(GrillePotentiel gp) {
		//parcours de tout les emplacement dans gp
		List<Emplacement> listEmp = gp.getGrillePlaces().getPlaces();
		dicoVar = new ArrayList<>();
		for(int i =0; i<listEmp.size() ; i++) {
			if (listEmp.get(i).hasCaseVide()) {
				DicoVariable d = new DicoVariable(i, gp);
				dicoVar.add(d);	
			}
		}
	}
	
	
	/**
	 * @return dicoVar
	 */
	@Override
	public List<IVariable> getVars() {
		return dicoVar;
	}

	/**
	 * @return true si un problème est encore satisfiable
	 */
	@Override
	public boolean isConsistent() {
		for(IVariable i : dicoVar) {
			if(i.getDomain().size() == 0) {
				return false;
			}
		}
		return true;
	}

	
	@Override
	public ICSP assign(IVariable vi, String val) {
		if (vi instanceof DicoVariable ) {
			DicoVariable nvi = (DicoVariable) vi;
			
			GrillePotentiel ngp = nvi.getGp().fixer(nvi.getIndex(), val);
			
			return new MotX(ngp);
		}else {
			return null;
		}
		
	}
	

}
