/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class nameSurferEntry implements nameSurferConstants {

	private String name;
	private int[] ranks;
	
	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public nameSurferEntry(String line) {
		String[] nameList = line.split(" ");
		name = nameList[0].toUpperCase();
		ranks = new int[12];
		for (int i = 1; i < nameList.length; i++) {
			int rank = Integer.parseInt(nameList[i]);
			ranks[i - 1] = rank;
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		return ranks[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		String ranksToString = name + ": ";
		for (int i = 0; i < ranks.length; i++) {
			int year = START_DECADE + i * 10;
			String yearRank = " Rank for year " + year + " is " + ranks[i] + ((i == 11) ? " " : ","); 
			ranksToString += yearRank;
		}
		// You need to turn this stub into a real implementation //
		return ranksToString;
	}
}

