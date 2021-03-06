/**
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
**/
import java.util.Hashtable;
import java.util.ArrayList;

public class Solution {
	class Tuple {
		int x,y;
		public Tuple (int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return "("+this.x+","+this.y+")";
		}
	}
	
	//Runtime: O(n^2) 
	public ArrayList<Tuple> allPossibleCombinations(int target, ArrayList<Integer> array) {
		ArrayList<Tuple> list = new ArrayList<Tuple>();
		for (int i = 0; i < array.size(); i++) {
			for (int j = i+1; j < array.size(); j++) {
				if (array.get(i) + array.get(j) == target) {
					Tuple tuple = new Tuple(i, j);
					list.add(tuple);
				}
			}

		}
		return list;
	}

	//Runtime: O(n)
	public ArrayList<Tuple> allPossibleCombinations2(int target, ArrayList<Integer> array) {
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
		ArrayList<Tuple> list = new ArrayList<Tuple>();

		for (int i = 0; i < array.size(); i++) {
			int diff = target - array.get(i);
			if (table.containsKey(array.get(i))) {
				list.add(new Tuple(table.get(array.get(i)), i));
			}
			else {
				table.put(diff, i);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(1);
		array.add(10);
		array.add(3);
		array.add(4);
		array.add(5);
		array.add(6);
		int target = 11;

		Solution sol = new Solution();
		ArrayList<Tuple> list = sol.allPossibleCombinations2(target, array);

		StringBuilder sb = new StringBuilder();
		for (Tuple t : list) {
			sb.append(t.toString()).append(" ");
		}
		System.out.println(sb);
	}
}