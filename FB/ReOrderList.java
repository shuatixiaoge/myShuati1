public class Solution {
    public void reorderList(ListNode head) {
        if(head==null|| head.next==null) return;
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid;
        if(head==null||head.next == null) mid=head;
        else mid = slow;
        ListNode left = head;
        ListNode tmp = mid.next;
        mid.next = null;
        ListNode right = reverse(tmp);
        merge(left,right);
    }
    public ListNode reverse(ListNode node){
        if(node==null||node.next ==null) return node;
        ListNode prev = null;
        while(node!=null){
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
    public ListNode merge(ListNode left,ListNode right){
        ListNode dummy = new ListNode(-1);
        dummy.next = left;
        while(right!=null){
            ListNode tmp1 = left.next;
            left.next = right;
            ListNode tmp2 = right.next;
            right.next = tmp1;
            left = tmp1;
            right = tmp2;
        }
        return dummy.next;
    }
}
