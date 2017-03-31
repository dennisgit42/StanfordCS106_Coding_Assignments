/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import java.io.*;
import java.util.*;
import acm.util.*;

public class nameSurferDatabase implements nameSurferConstants {
	
	private nameSurferEntry[] array;
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public nameSurferDatabase(String filename) {
		ArrayList<nameSurferEntry> arrayList = new ArrayList<nameSurferEntry>(); 
		BufferedReader rd;
		try {
			rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				nameSurferEntry entry = new nameSurferEntry(line);
				arrayList.add(entry);
			}
			array = new nameSurferEntry[arrayList.size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = arrayList.get(i);
			}
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public nameSurferEntry findEntry(String name) {
		for(int i = 0; i < array.length; i++) {
			String entryName = array[i].getName();
			if (name.toUpperCase().equals(entryName)){
				return array[i];
			}			
		}
		return null;
	}
}

