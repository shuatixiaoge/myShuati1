/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null || root == null) return null;
            TreeNode res = null;
            TreeNode curr = root;
            while(curr != null) {
                if (curr.val <= p.val) {//if equal go to the right to find the larger node
                    curr = curr.right;
                } else {
                    // if (res == null || res.val - p.val > curr.val - p.val) { //res.val will always be larger than p.val
                    //     // actually don't need this condition, since it's always trying to find the closest one
                    //     res = curr;
                    // }
                    res = curr;
                    curr = curr.left;
                }
            }
            return res;
            // switch the left and right will find the predecessor
    }

    public TreeNode successor(TreeNode root, TreeNode p) {
      if (root == null)
        return null;

      if (root.val <= p.val) {
        return successor(root.right, p);
      } else {
        TreeNode left = successor(root.left, p);
        return (left != null) ? left : root;
      }
    }

    public TreeNode predecessor(TreeNode root, TreeNode p) {
      if (root == null)
        return null;

      if (root.val >= p.val) {
        return predecessor(root.left, p);
      } else {
        TreeNode right = predecessor(root.right, p);
        return (right != null) ? right : root;
      }
    }


    //Followup if there is a parent node
//     2. TreeNode有parent引用：
//
// Pseudocode
//    if n has a right subtree
// 	return leftmost child of right subtree
//    else
// 	while n is a right child of n.parent
//          n = n.parent  // go northwest till end
// 	return n.parent // return 1st northeast node

public TreeNode inOrderSuccessor(TreeNode n) {
	if (n == null)	return null;
	if (n.right != null)
          return leftMostChild(n.right);
	else {
	    TreeNode cur = n, p = n.parent;
	    while (p != null && p.left != cur) {
            cur = p;
            p = p.parent;
	    }
	    return p;
      }
}
private TreeNode leftMostChild(TreeNode node) {
    while (node.left != null)
        node = node.left;
    return node;
}
}
