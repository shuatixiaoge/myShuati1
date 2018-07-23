// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
//

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      ListNode prev = new ListNode(-1);
      ListNode head = prev;
      int carry = 0;
      while(l1 != null || l2 != null || carry != 0) {
        int v1 = l1 == null ? 0 : l1.val;
        int v2 = l2 == null ? 0 : l2.val;
        int sum = v1 + v2 + carry;
        carry = sum/10;
        head.next = new ListNode(sum%10);
        l1 = l1 == null ? null : l1.next;
        l2 = l2 == null ? null : l2.next;
        head = head.next;
      }

      return prev.next;
    }
}
