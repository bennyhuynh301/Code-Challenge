/**
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
**/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr, answer;
        int first = l1.val + l2.val;
        curr = new ListNode(first%10);
        answer = curr;
        int remember = first/10;
        while (l1.next != null && l2.next != null) {
        	l1 = l1.next;
        	l2 = l2.next;
        	int next = l1.val + l2.val;
         curr.next = new ListNode((next+remember)%10);
        	remember = (next+remember)/10;
         curr = curr.next;
        }
        while (l1.next != null) {
        	l1 = l1.next;
         curr.next = new ListNode((l1.val+remember)%10);
        	remember = (l1.val+remember)/10;
         curr = curr.next;
        }
        while (l2.next != null) {
        	l2 = l2.next;
         curr.next = new ListNode((l2.val+remember)%10);
        	remember = (l2.val+remember)/10;
         curr = curr.next;
        }
        if (remember !=0 ){
         curr.next = new ListNode(remember);
         curr = curr.next;
        }
        return answer;
    }
}