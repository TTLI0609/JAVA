package pobj.tme5;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HashMultiSet<T>   extends  AbstractCollection<T> implements MultiSet<T>{
	/**associant à un élément apparaissant au moins une fois 
	 * dans le multi-ensemble un nombre d’occurrences strictement positif */
	private HashMap<T,Integer> hMap;
	/**la somme des nombres d’occurrences des éléments*/
	private int size;


	/**
	 * Construit un multi-ensemble vide
	 */
	public HashMultiSet() {
		hMap = new HashMap<T, Integer>();
		size = 0;
	}

	
	/**
	 * constructuit par copie
	 * initialisant le nouveau multi-ensemble 
	 * avec le contenu de la collection col
	 * @param col un objet Collection<T>
	 */
	public HashMultiSet(Collection<T> col) {
		size  = col.size();

		for(T e : col) {
			if(hMap.containsKey(e)) {
				hMap.put(e, hMap.get(e)+1);
			}
			else {
				hMap.put(e, 1);
			}
		}
	}


	/**
	 * ajoute count occurence d'objet e dans hMap 
	 * (si e n'existe pas dans hMap, on le creer avec une occurence count)
	 * @param e l’objet e
	 * @param count nombre d'occurence à ajouter
	 * @return true
	 */
	@Override public boolean add(T e, int count) {
		if( count <= 0) {
			throw new IllegalArgumentException("count negative ou nul");
		}

		if(hMap.containsKey(e) ) { //si hMap contient deja des ocurences de e
			hMap.put(e, (hMap.get(e)+count));
		}else {
			hMap.put(e, count);
		}
		size = size+ count;
		assert isConsistent();
		return true;
	}

	
	
	

	/**
	 * ajoute 1 occurence d'objet e dans hMap 
	 * (si e n'existe pas dans hMap, on le creer avec une occurence de 1)
	 * @param e l’objet e
	 * @return true
	 */
	@Override public boolean add(T e) {
		if(hMap.containsKey(e) ) { //si hMap contient deja des ocurences de e
			hMap.put(e, hMap.get(e)+1);
		}else {
			hMap.put(e, 1);
		}
		size++;
		
		assert isConsistent();
		return true;
	}



	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e) {
		if( size <= 0 || !hMap.containsKey(e)) {
			throw new IllegalArgumentException("on ne peut rien enlever");
		}

		hMap.put( (T) e, hMap.get(e)-1);

		if(hMap.get(e) == 0) {
			hMap.remove(e);
		}
		size--;
		
		assert isConsistent();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e, int count) {
		
		if( size <= 0 || !hMap.containsKey(e) || count <=0 ) {
			throw new IllegalArgumentException("on ne peut rien enlever");
		}
		
		

		if( hMap.get(e)<= count) {
			hMap.remove(e);
			size -= hMap.get(e); 
		}else {
			hMap.put( (T) e, hMap.get(e)-count);
		}

		
		size -= count;
		
		assert isConsistent();
		return true;
	}

	@Override
	public int count(T o) {
		if(! hMap.containsKey(o)) {
			return 0;
		}
		return hMap.get(o);
	}

	@Override
	public void clear() {
		hMap.clear();
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		return hMap.containsKey((T)o);
	}


	@Override
	public List<T> element() {
		List<T> res = new ArrayList<T>();
		for(T e : hMap.keySet()) {
			res.add(e);			
		}
		return res;
	}



	@Override public String toString() {
		int i =0;
		StringBuilder b = new StringBuilder();
		b.append("[ ");
		for (Map.Entry<T, Integer> entry : hMap.entrySet())  {
			b.append(entry.getKey());
			b.append(": ");
			b.append(entry.getValue());
			b.append("; ");
			
			i++;
			if (i==50) {
				i=0;
				b.append("\n");
			}
		}
		b.deleteCharAt(b.length()-2);
		b.append("]");
		return b.toString();
	
	
	}


	/**
	 * @return un iterator 
	 */
	@Override public Iterator<T> iterator() {
		return new HashMultiSetIterator(hMap);
	}


	/**
	 * Iterator de HashMultiSet
	 */
	public class HashMultiSetIterator implements Iterator<T>{

		private Iterator<Map.Entry<T,Integer>> ite ;
		private Map.Entry<T, Integer> entry;
		private int i ;

		public HashMultiSetIterator(HashMap<T,Integer> hMap) {
			ite = hMap.entrySet().iterator();
			entry =ite.next();
			i=0;
		}

		@Override public boolean hasNext() {
			return ite.hasNext() ;
		}

		@Override public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			i++;
			if( i < entry.getValue()  ) {
				return entry.getKey();
			}else {
				T tmp = entry.getKey();
				i=0;
				entry = ite.next();
				return tmp;
			}
		}
	}

	/**
	 * @return true si la structure est cohéernte, false sinon
	 */
	public boolean isConsistent() {
		int res = 0;
		
		for (Map.Entry<T, Integer> entry : hMap.entrySet())  {
			res += entry.getValue() ;
			if(entry.getValue() <= 0) {
				System.out.println("error value"+entry.getValue() );
				return false;
			}
		}
		if(size != res) {
			System.out.println("error size total :"+size+" "+res);
			return false;
		}
		return true;
	}

}