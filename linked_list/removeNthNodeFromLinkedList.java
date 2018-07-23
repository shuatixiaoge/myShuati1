/**
 Definition for singly-linked list.
 public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
 Given a linked list, remove the nth node from the end of list and return its head.

 For example,

    Given linked list: 1->2->3->4->5, and n = 2.

    After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.

 */


class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
      ListNode start = new ListNode(0); // dummy node is necessary to get rid of the corner case
      ListNode fast = start, slow = start;
      start.next = head;
      for (int i = 0; i <= n; i++) {
        fast = fast.next;
      }
      while(fast != null) {
        fast = fast.next;
        slow = slow.next;
      }
      slow.next = slow.next.next;
      // return head;  !!!Cannot return head which will ignore the base case, [1], 1, however return 1
      return start.next;
    }
}
