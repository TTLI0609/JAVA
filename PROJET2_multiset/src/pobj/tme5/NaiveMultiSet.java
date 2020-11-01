package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	/** liste contenant les objets */
	private List<T> list = new ArrayList<T>();
	private int size =0;
	

	@Override public boolean add(T e, int count) {
		for(int i =0; i<count; i++) {
			list.add(e);
		}
		size += count;
		return true;
	}
	@Override public boolean add(T e) {
		list.add(e);
		size ++;
		return true;
	}
	
	@Override public boolean remove(Object e, int count) {
		for(int i =0; i<count; i++) {
			list.remove(e);
		}
		size -= count;
		return true;
	}
	@Override public boolean remove(Object e) {
		list.remove(e);
		size--;
		return true;
	}

	@Override public int count(T o) {
		int i =0;
		for (T e : list) {
			if(e.equals(o)) {
				i++;
			}
		}
		return i;
	}
	@Override public void clear() {
		list.clear();
	}
	@Override public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		return list.contains((T)o);
	}
	
	@Override
	public List<T> element() {
		List<T> res =  new ArrayList<T>();
		for(T e: list) {
			if(!res.contains(e))
				res.add(e);
		}
		return res;
	}
	@Override
	public Iterator<T> iterator() {
		return new NaiveMultiSetIterator(list);
	}

	
	/**
	 * Iterator de NaiveMultiSet
	 */
	public class NaiveMultiSetIterator implements Iterator<T>{
		private List<T> list ;
		private int i=0;
		public NaiveMultiSetIterator(List<T> l) {
			list = l;
		}
		
		
		@Override
		public boolean hasNext() {
			return i < list.size();
		}

		@Override
		public T next() {
			return list.get(i++);
		}
	}


	@Override
	public boolean isConsistent() {
		if(size != list.size()) {
			System.out.println("error size total :"+size+" "+list.size());
			return false;
		}
		return true;
	}


}
