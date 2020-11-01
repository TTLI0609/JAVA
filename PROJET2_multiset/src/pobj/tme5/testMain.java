package pobj.tme5;

import java.io.IOException;

import pobj.util.Chrono;

public class testMain {

	public static void main(String[] args) throws IOException {
		System.out.println(" \n Avec HashMultiSet :" );
		Chrono chronoHash = new Chrono();
		HashMultiSet<String> m = new HashMultiSet<>();
		MultiSet<String> decorator = new MultiSetDecorator<>(new HashMultiSet<>());
		WordCount.wordcount(m);
		//WordCount.wordcount(decorator);
		chronoHash.stop();
		
		/**
		System.out.println();
		System.out.println(" \n Avec NaiveMultiSet :  ");
		Chrono chronoNaive = new Chrono();
		NaiveMultiSet<String> m2 = new NaiveMultiSet<>();
		MultiSet<String> decorator2 = new MultiSetDecorator<>(new HashMultiSet<>());
		WordCount.wordcount(m2);
		WordCount.wordcount(decorator2);
		chronoNaive.stop();
		 */
		
		
		
		
	}

}
