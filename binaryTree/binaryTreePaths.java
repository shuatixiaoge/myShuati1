/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // Given a binary tree, return all root-to-leaf paths.
 //
 // For example, given the following binary tree:
 //
 //    1
 //  /   \
 // 2     3
 //  \
 //   5
 // All root-to-leaf paths are:
 //
 // ["1->2->5", "1->3"]

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        if (root == null) return res;
        helper(root, res, sb);
        return res;
    }

    private void helper(TreeNode root, List<String> res, StringBuilder sb) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            res.add(sb.toString());
            return;
        }
        int len = sb.length();
        if (root.left != null) {
            sb.append(root.val);
            sb.append("->");
            helper(root.left, res, sb);
        }
        sb.setLength(len);
        if (root.right != null) {
            sb.append(root.val);
            sb.append("->");
            helper(root.right, res, sb);
        }
    }


    //Iterative
    //运用标记法左节点去过标记1，右节点去过标记2，如果标记是2那么返回结果
    //类似于在Graph里面找Loop，dfs过的点标记1，如果遇到1就有loop，走完dfs标记2
    public static void printPaths(TreeNode root){
        Stack<StackFrame> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        if(root != null) stack.push(new StackFrame(0, root));
        while(!stack.isEmpty()){
            StackFrame curFrame = stack.peek();
            TreeNode curNode = curFrame.node;
            if(curNode == null) {
                stack.pop();
            } else if(curFrame.status == 0){
                list.add(curNode);
                curFrame.status = 1;
                stack.push(new StackFrame(0, curNode.left));
            } else if(curFrame.status == 1){
                curFrame.status = 2;
                stack.push(new StackFrame(0, curNode.right));
            } else {
                if(curNode.left == null && curNode.right == null) {
                    for (TreeNode node : list) System.out.print("" + node.val + ",");
                    System.out.println();
                }
                stack.pop();
                list.remove(list.size() - 1);
            }
        }
    }
}
