public class Solution {
    //best solution
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
        	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }

    //soluction 2
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA=getlen(headA);
        int lenB=getlen(headB);
        int diff=lenA-lenB;
        if(diff>=0){
            while(diff>0){
                headA=headA.next;
                diff--;
            }
        }else{
            while(diff<0){
                headB=headB.next;
                diff++;
            }
        }
        while(headA!=null&&headB!=null&&headA!=headB){
            headA=headA.next;
            headB=headB.next;
        }
        return headA;
    }
    public int getlen(ListNode node){
        int len=0;
        if(node==null) return 0;
        while(node!=null){
            node=node.next;
            len++;
        }
        return len;
    }
}
