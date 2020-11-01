package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Un ensemble de mots.
 */
public class Dictionnaire {

	/** stockage des mots */
	private List<String> mots = new ArrayList<>();

	
	/**
	 * charge un dictionnaire depuis un fichier texte
	 * @param path l'emplacement du fichier texte
	 * @return un Dictionnaire contenant tout les mots du fichier text
	 */
	 public static Dictionnaire loadDictionnaire(String path) {
		 
		 Dictionnaire dic = new Dictionnaire();
		 
		 try (BufferedReader br = new BufferedReader(new FileReader(path))) { 
			 for (String line = br.readLine() ; line != null ; line = br.readLine() ) { 
				 dic.mots.add(line);		//ajoute le mot dans la liste de mots de dic
			 } 
			 } catch (IOException e) { 
			 // Problème d’accès au fichier. 
				 System.out.println("Probleme d'accès au fichier");
				 e.printStackTrace(); 
			 }
		 return dic;
	 }


	/**
	 * @return la liste de mots dans le dictionnaire
	 */
	public List<String> getMots(){
		return mots;
	}

	
	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}

	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}


	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}
	
	
	/**
	 *  Affichage du dictionnaire
	 */
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}


	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}

	/**
	 *  modifie le dictionnaire pour ne garder que les mots dont la i ème lettre est égale au caractère de l’argument c
	 * @param c caractere commun
	 * @param i indice de la lettre 
	 * @return le nombre de mots supprimés
	 */
	public int filtreParLettre(char c, int i) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		
		for (String mot : mots) {
			if ( mot.charAt(i) == c )
				cible.add(mot);
			else
				cpt++;
		}
		
		mots = cible;
		return cpt;
	}
	
	/**
	  * filtrer le dictionnaire par rapport à un indice i 
	  * @param ens enselble de lettres possible 
	  * @param i indice
	  * @return le nombre de mots supprimés
	  */
	public int filtreParIndice(EnsembleLettre ens, int i) {
			List<String> cible = new ArrayList<>();
			int cpt=0;
			
			for (String mot : mots) {
				for(char c : ens.getLettres()) {
					if ( mot.charAt(i) == c )
						cible.add(mot);
					else
						cpt++;
				}
	
			}
			
			mots = cible;
			return cpt;
		}


	/**
	  *  calcule l’EnsembleLettre possible à une case donné
	  * @param c indice de la case du mot
	  * @return l'EnsembleLettre possible
	  */
	 public EnsembleLettre calculLettrePosition(int c) {
		 EnsembleLettre res = new EnsembleLettre();
		 
		 for(String s : this.mots) {
			 res.add(s.charAt(c));
			 
		 }
		 
		 return res;
		 
		 
	 }
	 
	 
	 
	 
	 
	 
}
