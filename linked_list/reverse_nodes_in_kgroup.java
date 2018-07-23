/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
 */
 public class Solution {
     public ListNode reverseKGroup(ListNode head, int k) {
         if(head==null || head.next==null) return head;
         ListNode tail=head;
         ListNode next=head.next;
         ListNode dummy=new ListNode(-1);
         dummy.next=head;
         ListNode prev=dummy;
         while(true){
             //find tail
             int count=k;
             while(count>0){
                 if(tail==null) return dummy.next;
                 tail=tail.next;
                 count--;
             }
             //prev=head;
             //reverse k lists
             ListNode pre=prev;
             ListNode tmphead=head;
             while(head!=tail){
                 next=head.next;
                 head.next=pre;
                 pre=head;
                 head=next;
                 System.out.print(pre.val);

             }
             prev.next=pre;
                             System.out.print(prev.val);
                 System.out.print(pre.val);

             tmphead.next=head;
                             System.out.print(tmphead.val);
                                 //System.out.print(head.val);


             prev=tmphead;
         }
     }
 }
