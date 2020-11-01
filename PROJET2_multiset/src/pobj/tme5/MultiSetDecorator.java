package pobj.tme5;

import java.util.AbstractCollection;

import java.util.Iterator;
import java.util.List;


public class MultiSetDecorator<T> extends  AbstractCollection<T> implements MultiSet<T> {

	private MultiSet<T> decorated;


	public MultiSetDecorator(MultiSet<T> ms) {
		decorated = ms;
	}
	public MultiSet<T> getDecorated(){
		return decorated;
	}

	@Override
	public boolean add(T e, int count) {
		boolean tmp = decorated.add(e, count);

		if(! isConsistent()) {
			throw new InternalError("inconsistent object");
		}

		return tmp;
	}

	@Override
	public boolean add(T e) {
		boolean tmp = decorated.add(e);

		if(! isConsistent()) {
			throw new InternalError("inconsistent object");
		}

		return tmp;
	}

	@Override
	public boolean remove(Object e) {
		boolean tmp =decorated.remove(e);

		if(! isConsistent()) {
			throw new InternalError("inconsistent object");
		}


		return tmp;
	}

	@Override
	public boolean remove(Object e, int count) {
		boolean tmp = decorated.remove(e, count);

		if(! isConsistent()) {
			throw new InternalError("inconsistent object");
		}
		return tmp;
	}

	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public void clear() {
		decorated.clear();
	}

	@Override
	public int size() {
		return decorated.size();
	}

	@Override
	public boolean contains(Object o) {
		return decorated.contains(o);
	}

	@Override
	public List<T> element() {
		return decorated.element();
	}

	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	@Override
	public boolean isConsistent() {
		return isConsistent();
	}


}
