//O(N2)
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

//O(N)

public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tStr = getPostOrderString(t);
        return validateSubtree(s, tStr).find;
    }

    private String getPostOrderString(TreeNode root) {
        if(root==null) return "#";
        else {
            return getPostOrderString(root.left)+ "," +getPostOrderString(root.right) + "," + root.val;
        }
    }

    private State validateSubtree(TreeNode s, String tStr) {
        if(s==null) {
            return new State("#", find);
        }
        State left = validateSubtree(s.left, tStr);
        State right = validateSubtree(s.right, tStr);
        String str = left.str+","+right.str+","+s.val;
        boolean find = left.find||right.find||tStr.equals(str);
        return new State(str, find);
    }
}

class State {
    String str;
    boolean find;
    public State(String str, boolean find) {
        this.str = str;
        this.find = find;
    }
}
