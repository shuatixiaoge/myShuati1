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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        PriorityQueue<ListNode> pq=new PriorityQueue<ListNode>(lists.length,new com());
        for(ListNode list:lists){
            if(list!=null)
                pq.add(list);
        }
        ListNode dummy=new ListNode(-1);
        ListNode node=dummy;
        while(!pq.isEmpty()){
            node.next=pq.remove();
            node=node.next;//This position is important
            if(node.next!=null)
                pq.add(node.next);

        }
        return dummy.next;
	}
	public class com implements Comparator<ListNode>{
	    public int compare(ListNode l1, ListNode l2){
	        return l1.val-l2.val;
	    }
	}

//Merge K Lists, mergeSort
    public ListNode mergeKLists(ListNode[] lists) {
    	if (lists == null || lists.length == 0)
    		return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }
    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
    	if (start == end) {
    		return lists[start];
    	} else if (start < end){
    		int mid = (end - start) / 2 + start;
    		ListNode left = mergeKLists(lists, start, mid);
    		ListNode right = mergeKLists(lists, mid + 1, end);
    		return mergeTwoLists(left, right);
    	} else {
    		return null;
    	}
    }
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }
}
