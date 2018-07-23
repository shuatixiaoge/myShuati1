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
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
//		Given 1->2->3->4->5->NULL and k = 2,
//				return 4->5->1->2->3->NULL.
        if(k==0) return head;
		ListNode fast=head;
		int len=1;
		if(head==null) return null;
		while(fast.next!=null){
			fast=fast.next;
			len++;
		}
		fast.next=head;
		k=len-k%len;
		while(--k>0) head=head.next;
		ListNode dummy=head.next;
		head.next=null;
		return dummy;
    }
}
