public class Solution {

    ListNode head;
    Random random;

    public Solution(ListNode h) {
        head = h;
        random = new Random();
    }

    public int getRandom() {

        ListNode c = head;
        int r = c.val;
        for(int i=1;c.next != null;i++){

            c = c.next;
            if(random.nextInt(i + 1) == 0) r = c.val;
        }

        return r;
    }
}
