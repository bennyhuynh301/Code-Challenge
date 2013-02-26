/**
Implement an algorithm to determine if a string has all unique characters.
**/

import java.util.Hashtable;

public class Solution {
	public boolean isAllUniqueCharacters(String string){
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (table.containsKey(ch)) {
				return false;
			}
			else {
				table.put(ch, 1);
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String[] testStrings = new String[4]; 
		testStrings[0] = "hello";
		testStrings[1] = "abcdef";
		testStrings[2] = "";
		testStrings[3] = "aa";

		Solution sol = new Solution();
		for (int i =0; i < testStrings.length; i++){
			System.out.println(testStrings[i] + ": " + sol.isAllUniqueCharacters(testStrings[i]));
		}
	}
}