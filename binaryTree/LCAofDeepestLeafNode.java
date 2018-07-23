//Lca of the deepest nodes
// Post order can help you with only one pass
class  TreeNode {
    TreeNode left;
    TreeNode right;
    char val;
    public TreeNode(char a) {
        this.left = null;
        this.right = null;
        this.val = a;
    }
}
class LCAofDeepestLeafNode {
    static TreeNode curLCA = null;
    static int maxDepth = 0;
    private static int postOrder(TreeNode root, int depth){
        if(root == null) return 0;
        if(root.left == null && root.right == null){
            if(depth > maxDepth){
                curLCA = root;
                maxDepth = depth;
            }
            return depth;
        }
        int left = postOrder(root.left, depth + 1);
        int right = postOrder(root.right, depth + 1);
        // this equal sign here is important
        if(left == right && left >= maxDepth){
            curLCA = root;
        }
        return Math.max(left, right);
    }
    public static void main(String[] args) {
        // TreeNode nodeA = new TreeNode('A');
        // TreeNode nodeB = new TreeNode('B');
        // TreeNode nodeC = new TreeNode('C');
        // TreeNode nodeE = new TreeNode('E');
        // TreeNode nodeF = new TreeNode('F');
        // TreeNode nodeH = new TreeNode('H');
        // TreeNode nodeG = new TreeNode('G');
        // TreeNode nodeI = new TreeNode('I');
        // TreeNode nodeZ = new TreeNode('Z');
        // nodeA.left = nodeB;
        // nodeA.right = nodeC;
        // nodeB.left = nodeE;
        // nodeB.right = nodeF;
        // nodeF.left = nodeG;
        // nodeF.right = nodeI;
        // nodeC.right = nodeH;
        // nodeH.right = nodeZ;
        TreeNode nodeA = new TreeNode('A');
        TreeNode nodeB = new TreeNode('B');
        TreeNode nodeC = new TreeNode('C');
        TreeNode nodeD = new TreeNode('D');
        TreeNode nodeE = new TreeNode('E');
        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeC.left = nodeD;
        nodeC.right = nodeE;
        postOrder(nodeA, 0);
        System.out.println(curLCA.val);
    }

}
