package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	/** notre unique presse-papiers */
	private static Clipboard clipboard = new Clipboard();
	/** liste des clips */
	private List<Clip> clips =new ArrayList<>();
	/** liste des listeners*/
	private List<ClipboardListener> listeners = new ArrayList<>();
	
	
	private Clipboard() {
	}
	
	
	public static Clipboard getInstance() {
		return clipboard;
	}
	
	/**
	 * copie clip de cl dans le presse-papiers
	 * @param cl la liste des clip a recopie
	 */
	public void copyToClipboard(List<Clip> cl) {
		clear();	// on vide ce qu'on avait selectionné avant
		for(Clip e : cl) 
			clips.add(e.copy());
	}
	/**
	 * @return une copie des éléments graphiques du presse-papiers
	 */
	public List<Clip> copyFromClipboard() {
		List<Clip> res = new ArrayList<>();
		for (Clip e : clips)
			res.add(e.copy());
		//clear();	// on a deja coller donc on peut supprimer le copier
		return res;
	}
	
	/**
	 * vide le contenu du presse-papiers
	 */
	public void clear() {
		clips.clear();
		Changed();
	}
	
	/**
	 * @return indique si le presse-papiers est vide
	 */
	public boolean isEmpty() {
		return clips.isEmpty();
	}
	
	
	public void addListener(ClipboardListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ClipboardListener listener) {
		listeners.remove(listener);
	}
	
	/** appelle clipboardChanged sur chaque ClipboardListener*/
	public void Changed() {
		for(ClipboardListener cl : listeners) {
			cl.clipboardChanged();
		}
	}
	
}
