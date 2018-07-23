//In-order iteration
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    TreeNode next = null;
    void AddNodeToStack(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    // @param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        next = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        if (next != null) {
            AddNodeToStack(next);
            next = null;
        }
        return !stack.isEmpty();
    }

    //@return: return next node
    public TreeNode next() {
        if (!hasNext()) {
            return null;
        }
        TreeNode cur = stack.pop();
        next = cur.right;
        return cur;
    }
}
