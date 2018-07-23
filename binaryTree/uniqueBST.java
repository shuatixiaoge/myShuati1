/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // Input: 3
 // Output: 5
 // Explanation:
 // Given n = 3, there are a total of 5 unique BST's:
 //
 //    1         3     3      2      1
 //     \       /     /      / \      \
 //      3     2     1      1   3      2
 //     /     /       \                 \
 //    2     1         2                 3
public class Solution {
    public int numTrees(int n) {
        int[] a=new int[n+1];
        a[0]=1;
        a[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                a[i]+=a[j]*a[i-j-1];//not include the root
            }
        }
        return a[n];

    }
}

//2
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return helper(1,n);
    }
    public List<TreeNode> helper(int start,int end){
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(start>end){
            res.add(null);
            return res;
        }
        for(int i=start;i<=end;i++){
            List<TreeNode> l = helper(start,i-1);
            List<TreeNode> r = helper(i+1, end);
            for(TreeNode left:l){
                for(TreeNode right:r){
                    TreeNode tmp = new TreeNode(i);
                    tmp.left = left;
                    tmp.right = right;
                    res.add(tmp);
                }
            }
        }
        return res;
    }
}
