private static class TreeNode{
    int val;
    TreeNode left,right;
    public TreeNode(int val){
        this.val = val;
    }
}
static TreeNode prev;
static TreeNode head;
// In-order
public static void convert(TreeNode root){
    if(root == null) return;
    convert(root.left);
    if(prev == null){
        head = root;
    } else {
        root.left = prev;
        prev.right = root;
    }
    prev = root;
    convert(root.right);
}

// to circular double linked list
public TreeNode bstToSortedDLL(TreeNode node) {
    if(node == null) return null;
    bstToSortedDLL(node.left);
    node.left = prev;
    if(prev != null) {
        prev.right = node;
    } else {
        head = node;
    }
    prev = node;
    //this is linked to the head <-> tail;
    TreeNode right = node.right;
    head.left = node;
    node.right = head;
    bstToSortedDLL(right);
    return head;
}
//iteratively

public TreeNode bstToDoublyList(TreeNode root) {
    TreeNode head = null, prev = null;
    Stack<TreeNode> stack = new Stack<>();
    while (root != null || !stack.empty()) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        root.left = prev;
        if (prev != null)	prev.right = root;
        else 	head = root;
        TreeNode right = root.right;
        head.left = root;
        root.right = head;//remember to update the prev !!!
        prev = root;
        root = right;//we should root=root.right even if it's null!!!
    }
    return head;
}
