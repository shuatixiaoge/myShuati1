// Example 1:
// Given tree s:
//
//      3
//     / \
//    4   5
//   / \
//  1   2
// Given tree t:
//    4
//   / \
//  1   2
// Return true, because t has the same structure and node values with a subtree of s.
// Example 2:
// Given tree s:
//
//      3
//     / \
//    4   5
//   / \
//  1   2
//     /
//    0
// Given tree t:
//    4
//   / \
//  1   2
// Return false.
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        if (s.val != t.val) return false;

        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}


//my solution, a little bit complicated
class Solution {
    private boolean matched;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        matched = false;
        return helper(s, t);
    }
    private boolean helper(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null && t != null || s != null && t == null) return false;
        if (s.val == t.val) {
            matched = true;
            if(helper(s.left, t.left) && helper(s.right, t.right)) return true;
            else {
                // this is the case where [1, 1] and node 1, which 1 is duplicates and can be subtree.
                matched = false;
                return helper(s.left, t) || helper(s.right, t);
            }
        }
        else {
            //matched is need, since only when it matched the first case, then it would do && otherwise, it's ||
            if (matched) return false;
            else return helper(s.left, t) || helper(s.right, t);
        }
    }
}
