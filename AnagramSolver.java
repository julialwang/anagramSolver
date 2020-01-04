// Julia Wang
// CSE 143 A with Professor Schafer
// Section AG with Mino Nakura
// Assignment #6

// AnagramSolver uses a given dictionary to find all of the anagrams of an user-inputted word.
// It also allows the user to determine the maximum number of anagrams that they want to be created
// from the input. (A max of 3 would generate lists of words consisting of at most three words each)

import java.util.*;

public class AnagramSolver {

	// stores all possible case-insensitive words that can be used in creating anagrams.
	private List<String> dictionary;
	
	// stores each word in the dictionary along with the letters that make up said word. 
	// case-insensitive.
	private Map<String, LetterInventory> wordMap;
	
	// pre:  requires the list of possible case-insensitive words
	// post: sets up the dictionary of usable case-insensitive words for use in later methods.
	public AnagramSolver(List<String> dictionary) {
		
		this.dictionary = dictionary;
		wordMap = new HashMap<String, LetterInventory>();
		
		for (String word : dictionary) {
			wordMap.put(word, new LetterInventory(word));
		}
		System.out.println("test" + wordMap.keySet().toString());
		
	}
	
	// pre:  requires a case-insensitive string, which is the word that anagrams will be
	//		 generated from, as well as an integer representing the maximum number of words
	//		 desired to be generated in a single anagram for the input word.
	// post: prints all of the input string's anagrams in the order of which the words in
	//		 the anagram appeared in the dictionary of usable words. the format is comma-separated words 
	//		 that are enclosed in a set of square brackets for each individual anagram without 
	//		 trailing spaces. each new anagram is printed on a separate line.
	public void print(String text, int max) {
		
		if (max < 0) {
			throw new IllegalArgumentException();
		}
		
		if (max == 0) {
			max = text.length();
		}
		
		LetterInventory leftovers = new LetterInventory(text);
		List<String> wordList = new ArrayList<String>();
		
		for (String word : dictionary) {
			
			if (leftovers.subtract(wordMap.get(word)) != null) {
				wordList.add(word);
			}
			
		}
		
		print(leftovers, wordList, max, new Stack<String>());	
	}
	
	// pre:  requires: the letters that are left in the word to be anagrammed, the list of words
	//		 that can possibly be made from the input string, the maximum number of words in each
	//		 anagram, and the current words to be used for the result.
	// post: prints all of the input string's anagrams in the order of which the words in
	//		 the anagram appeared in the dictionary of usable words. the format is comma-separated words 
	//		 that are enclosed in a set of square brackets for each individual anagram without 
	//		 trailing spaces. each new anagram is printed on a separate line.
	private void print(LetterInventory leftovers, List<String> wordList, int max, Stack<String> wordStack) {
		
		if (max >= 0) {
		
			if (leftovers.isEmpty()){
				System.out.println(wordStack);
			}
			
			else {
				
				for (String word : wordList) {
					LetterInventory current = leftovers.subtract(wordMap.get(word));
					
					if (current != null) {
						wordStack.push(word);
						print(current, wordList, max - 1, wordStack);
						wordStack.pop();
					}
				}
			}
		}
	}	
}
