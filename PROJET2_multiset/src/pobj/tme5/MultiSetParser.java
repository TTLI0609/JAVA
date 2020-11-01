package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser  {


	/**
	 * lit un fichier et transforme son contenu en un HashMultiSet
	 * @param fileName nom du fichier placer dans le repertoire data
	 * @return un HashMultiSet<String> 
	 * @throws InvalidMultiSetFormat 
	 */
	public static HashMultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader("data/"+fileName));
		} catch (FileNotFoundException e) {
			throw new InvalidMultiSetFormat("InvalidMultiSetFormat : ", e.getCause());
		}
		
		HashMultiSet<String> m = new HashMultiSet<>();
		
		String line;
		String[] tmp;
		try {
			while ((line = br.readLine())!=null) { 
				if(line.equals("")) continue;
				tmp = line.split(":");
				m.add(tmp[0], Integer.decode(tmp[1]));

			} 
		} catch (IOException e) {
			throw new InvalidMultiSetFormat("InvalidMultiSetFormat : ", e.getCause());
		}
		try {
			br.close();
		} catch (IOException e) {
			throw new InvalidMultiSetFormat("InvalidMultiSetFormat : ", e.getCause());
		} 


		return m;

	}
}
