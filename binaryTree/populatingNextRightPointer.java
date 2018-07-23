// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
public class Solution {
    //O(n) space
    //when it's not perfect tree
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode last = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                if (last != null) last.next = node;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                last = node;
            }
        }
    }
    //O(1) space
    public void connect(TreeLinkNode root) {
        if(root==null)  return;
        TreeLinkNode level  = root;
        while(level!=null){
            TreeLinkNode head = new TreeLinkNode(0);
            TreeLinkNode node = head;
            while(level!=null){
                if(level.left!=null){
                    node.next = level.left;
                    node = node.next;
                }
                if(level.right!=null){
                    node.next = level.right;
                    node = node.next;
                }
                level = level.next;
            }
            level = head.next;
        }
    }

}
