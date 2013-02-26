/**
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
**/

import java.util.Hashtable; 

public class Solution {
	public int longestNonRepeatedCharactersSubString(String string) {
		int maxLength = 0;
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
		int currLength = 0;
		int i = 0;
		while (i < string.length()) {
			char ch = string.charAt(i);
			if(table.containsKey(ch)) {
				maxLength = Math.max(maxLength, currLength);
				currLength = 0;
				i = table.get(ch)+1;
				table.clear();
				continue;
			}
			table.put(ch, i);
			currLength++;
			i++;
		}
		maxLength = Math.max(maxLength, currLength);
		return maxLength;
	}

	public static void main(String[] args) {
		String string1 = "hchzvfrkmlnozjk";
		String string2 = "aaaaaaa";
		Solution sol = new Solution();
		System.out.println(string1 + ": " + sol.longestNonRepeatedCharactersSubString(string1));
		System.out.println(string2 + ": " + sol.longestNonRepeatedCharactersSubString(string2));
	}
}