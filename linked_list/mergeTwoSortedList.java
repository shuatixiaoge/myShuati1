class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      if (l1 == null) return l2;
      if (l2 == null) return l1;
      if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
      } else {
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
      }
    }

    // if want to do it iteratively, then you need a prehead
    //Time complexity is the same but the space Complexity here is O(1), recursive is O(M+N)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while(l1 != null && l2 != null) {
          if (l1.val < l2.val) {
            pre.next = l1;
            l1 = l1.next;
          } else {
            pre.next = l2;
            l2 = l2.next;
          }
          prev = prev.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
}
