package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class WordCount {

	public static void wordcount(MultiSet<String> ms)  {
		
		String file = "data/texte.txt";
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			br = null;
			System.out.println("PB ouverture du fichier");
			e.printStackTrace();
		} 
		String line; 

		try {
			while ((line = br.readLine())!=null) { 
				for (String word : line.split("\\P{L}+")) { 
					if (word.equals("")) continue; // ignore les mots vides 

					ms.add(word);
					
				} 
			}
		} catch (IOException e) {
			System.out.println("PB lecture du fichier");
			e.printStackTrace();
		}
		
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("PB fermeture du fichier");
			e.printStackTrace();
		} 
		
		List<String> list = ms.element();
		
		Collections.sort(list,new CompareOccurence<String>(ms) );
		
		for (int i =1; i<=10; i++) {
			System.out.println(i + " : " + list.get(i-1));
		}
	}
	
	
	
	
}
