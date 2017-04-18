///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             KeyWord.java
// Semester:         CS 367 - Summer 2016
//
// Author:           Jinsoo Chung jmchung2@wisc.edu
// CS Login:         jinsoo
// Lecturer's Name:  Amanda Strominger
// Lab Section:      NA
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     NA
// Email:            NA
// CS Login:         NA
// Lecturer's Name:  NA
// Lab Section:      NA
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          NA
//
// Online sources:   NA
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * KeyWords represent a word and its number of occurrences.
 *
 * <p>Bugs: None known
 *
 * @author Jinsoo Chung
 */
public class KeyWord implements Comparable<KeyWord>, Prioritizable {

	// non-empty sequence of lower-case characters
	private String word;
	// the number of occurrences of this word
	private int occurrences;
	
	/**
	 * Constructs a KeyWord with the given word (converted to lower-case) and 
	 * zero occurences. If the word is null or an empty string, an 
	 * IllegalArgumentException is thrown.
	 * 
	 * @param word the word for this KeyWord
	 * @throws java.lang.IllegalArgumentException if word is null or empty
	 */
	public KeyWord(String word) {
		if (word == null || word.equals("")) {
			throw new IllegalArgumentException();
		}
		this.word = word.toLowerCase();
		occurrences = 0;
	}
    
    /**
     * Returns the word for this KeyWord.
     *
     * @return the word for this KeyWord
     */
    public String getWord() {
        return word;
    }
    
    /**
     * Returns the number of occurrences for this KeyWord.
     *
     * @return the number of occurrences for this KeyWord
     */
    public int getOccurrences() {
        return occurrences;
    }
    
    /**
     * Adds one to the number of occurrences for this KeyWord.
     */
    public void increment() {
        occurrences++;
    }

	/**
	 * Returns the priority for this KeyWord. The priority of a KeyWord is the 
	 * number of occurrences it has.
	 * 
	 * @return the priority for this item.
	 */
	public int getPriority() {
		return occurrences;
	}
	
	/**
	 * Compares the KeyWord with the one given. Two KeyWords are compared by 
	 * comparing the word associated with the two KeyWords, ignoring case 
	 * differences in the names.
	 * 
	 * @param other the KeyWord with which to compare this KeyWord
	 */
	public int compareTo(KeyWord other) {
		return word.compareToIgnoreCase(other.getWord());
	}

	/**
	 * Compares this KeyWord to the specified object. The result is true if and 
	 * only if the argument is not null and is a KeyWord object whose word is 
	 * the same as the word of this KeyWord, ignoring case differences.
	 * 
	 * @param other the object with which to compare this KeyWord
	 */
	public boolean equals(Object other) {
		if (other == null || !(other instanceof KeyWord)) {
			return false;
		}
		KeyWord otherKeyWord = (KeyWord) other;
		return word.equalsIgnoreCase(otherKeyWord.getWord());
	}
	
}
