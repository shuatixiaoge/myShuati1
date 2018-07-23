public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail=head;

        if(head==null || head.next==null) return head;
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
            tmphead.next=head;
            prev=tmphead;
        }
    }
}
