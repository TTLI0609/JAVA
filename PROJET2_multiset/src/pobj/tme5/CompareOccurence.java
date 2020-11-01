package pobj.tme5;

import java.util.Comparator;
import java.util.HashMap;

public class CompareOccurence<T> implements Comparator<T>{

		MultiSet<T> ms ;
		
		public CompareOccurence(MultiSet<T> ms) {
			this.ms = ms;
		}
		
		@Override
		public int compare(T o1, T o2) {
			if( ms.count(o1) < ms.count(o2) ) 
				return 1;
			else if(  ms.count(o1) == ms.count(o2)  ) 
				return 0;
			else
				return -1;

		}

	
}
