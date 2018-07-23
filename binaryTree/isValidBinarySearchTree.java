// Given a binary tree, determine if it is a valid binary search tree (BST).
//
// Assume a BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
// Example 1:
//     2
//    / \
//   1   3
// Binary tree [2,1,3], return true.
// Example 2:
//     1
//    / \
//   2   3
// Binary tree [1,2,3], return false.
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
  public boolean isValidBST(TreeNode root) {
      return helper(root, null,null);
  }
  // not so good solution
  public boolean helper(TreeNode root, Integer start, Integer end){
      if(root==null) return true;
      boolean left = helper(root.left,start,root.val);
      boolean right = helper(root.right,root.val, end);
      return (start==null || start<root.val) &&
        (end==null || end>root.val) &&
        left &&
        right;
  }

  public boolean isValidBST(TreeNode root) {
     if (root == null) return true;
     Stack<TreeNode> stack = new Stack<>();
     TreeNode pre = null;
     TreeNode p = root;
     while(!stack.isEmpty() || p != null) {
         if(p != null) {
             stack.push(p);
             // preorder do something here
             p = p.left;
         } else {
             p = stack.pop();// better to overwrite p;

             if(pre != null && p.val <= pre.val) return false;
             // find kth smallest is if(--k == 0) break;
             // In-order traversal is doing add here
             pre = p;
             p = p.right;
         }
     }
     return true;
  }
}
