/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }
    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append('+');
            sb.append(',');
            return;
        }
        sb.append(root.val);
        sb.append(',');
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        Queue<String> queue = new LinkedList<String>();
        queue.addAll(Arrays.asList(arr));
        return buildTree(queue);
    }
    public TreeNode buildTree(Queue<String> queue){
        if(queue.isEmpty()) {
            return null;
        }
        String str = queue.poll();
        if (str.equals("+")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left =  buildTree(queue);
        node.right =  buildTree(queue);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
;
